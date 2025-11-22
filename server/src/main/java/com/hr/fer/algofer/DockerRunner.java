package com.hr.fer.algofer;

import java.io.File;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.exception.DockerClientException;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

class ResCallback extends BuildImageResultCallback {

}

public class DockerRunner {
  public static void run() {
    DockerClientConfig defaultConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

    DockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder().dockerHost(defaultConfig.getDockerHost())
        .sslConfig(defaultConfig.getSSLConfig())
        .build();

    DockerClient dockerClient = DockerClientImpl.getInstance(defaultConfig, dockerHttpClient);

    File dockerFile = new File("runner/cpp/Dockerfile");

    String imageId;
    try {
      imageId = dockerClient
          .buildImageCmd().withDockerfile(dockerFile)
          .exec(new BuildImageResultCallback() {
            @Override
            public void onNext(BuildResponseItem item) {
              if (item.getStream() != null) {
                System.out.print(item.getStream()); // These include build errors
              }
              super.onNext(item);
            }
          }).awaitImageId();

      String testcase = "[MyList,AddUnique,3,Remove,3,Contains,3]";
      String containerId = dockerClient.createContainerCmd(imageId).withCmd("./run.out", testcase).exec()
          .getId();
      dockerClient.startContainerCmd(containerId).exec();
      LogContainerCmd logContainer = dockerClient.logContainerCmd(containerId).withStdOut(true).withTail(100);

      logContainer.exec(
          new ResultCallback.Adapter<Frame>() {
            @Override
            public void onNext(Frame frame) {
              System.out.println(new String(frame.getPayload()));
            }
          });
    } catch (DockerClientException e) {
      System.out.println(e);
    }

  }
}
