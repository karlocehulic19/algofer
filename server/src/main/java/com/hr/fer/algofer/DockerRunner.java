package com.hr.fer.algofer;

import java.io.File;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.exception.DockerClientException;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.hr.fer.algofer.code.utils.CompileCode;

class ResCallback extends BuildImageResultCallback {

}

@Component
public class DockerRunner {
  private CompileCode compileCode;

  DockerRunner(CompileCode compileCode) {
    this.compileCode = compileCode;
  }

  public void run() {
    DockerClientConfig defaultConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

    DockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder().dockerHost(defaultConfig.getDockerHost())
        .sslConfig(defaultConfig.getSSLConfig())
        .build();

    DockerClient dockerClient = DockerClientImpl.getInstance(defaultConfig, dockerHttpClient);

    File dockerFile = new File("runner/cpp/Dockerfile");

    String imageId;
    try {
      // building image
      imageId = dockerClient
          .buildImageCmd().withDockerfile(dockerFile).withTags(Set.of("cpprunner"))
          .withBuildArg("EXECUTABLE_PATH", compileCode.getExecutablePath())
          .exec(new BuildImageResultCallback() {
          }).awaitImageId();

      String testcase = "[MyList,AddUnique,3,AddUnique,3,Remove,3,Contains,3]";
      // creating container
      String containerId = dockerClient.createContainerCmd(imageId)
          .withCmd("./run.out",
              testcase)
          .exec()
          .getId();

      // starting container
      dockerClient.startContainerCmd(containerId).exec();
      LogContainerCmd logContainer = dockerClient.logContainerCmd(containerId).withStdErr(true).withStdOut(true)
          .withTail(100);

      logContainer.exec(
          new ResultCallback.Adapter<Frame>() {
            @Override
            public void onNext(Frame frame) {
              System.out.println(new String(frame.getPayload()));
            }

            @Override
            public void onError(Throwable throwable) {
              super.onError(throwable);

              System.out.println("ERROR OCCURED!");
            }
          });

      // removing container
      dockerClient.removeContainerCmd(containerId).exec();
    } catch (DockerClientException e) {
      System.out.println(e);
    }

  }
}
