package com.hr.fer.algofer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

class ResCallback extends BuildImageResultCallback {

}

@Component
public class DockerRunner {
  @Value("${EXECUTABLE_DIR}")
  private String executableDirPath;

  @Value("${EXECUTABLE_NAME}")
  private String executableName;

  private DockerClient dockerClient;

  DockerRunner() {
    DockerClientConfig defaultConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

    DockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder().dockerHost(defaultConfig.getDockerHost())
        .sslConfig(defaultConfig.getSSLConfig())
        .build();

    dockerClient = DockerClientImpl.getInstance(defaultConfig, dockerHttpClient);
  }

  public List<String> run(String testcase) throws InterruptedException {
    // Dockerfiles managment model might be needed for multiple langauge feature
    File dockerFile = new File(executableDirPath + "/Dockerfile");

    String imageId;
    imageId = dockerClient
        .buildImageCmd().withDockerfile(dockerFile).withTags(Set.of("cpprunner"))
        .withBuildArg("EXECUTABLE_NAME", executableName)
        .exec(new BuildImageResultCallback() {
        }).awaitImageId();

    String containerId = dockerClient.createContainerCmd(imageId)
        .withCmd("./run.out",
            testcase)
        .exec()
        .getId();
    dockerClient.startContainerCmd(containerId).exec();
    LogContainerCmd logContainer = dockerClient.logContainerCmd(containerId).withStdOut(true)
        .withTail(100);

    List<String> stdouts = new ArrayList<>();
    logContainer.exec(
        new ResultCallback.Adapter<Frame>() {
          @Override
          public void onNext(Frame frame) {
            stdouts.add(new String(frame.getPayload()));
          }
        });

    dockerClient.waitContainerCmd(containerId).start().awaitCompletion();
    dockerClient.removeContainerCmd(containerId).exec();

    return stdouts;
  }
}
