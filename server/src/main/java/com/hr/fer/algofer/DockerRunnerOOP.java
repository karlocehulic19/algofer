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

@Component
public class DockerRunnerOOP {
  private DockerClient dockerClient;

  @Value("${COPIED_OOP_FILENAME}")
  private String copiedFilename;

  DockerRunnerOOP() {
    DockerClientConfig defaultConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

    DockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder().dockerHost(defaultConfig.getDockerHost())
        .sslConfig(defaultConfig.getSSLConfig())
        .build();

    dockerClient = DockerClientImpl.getInstance(defaultConfig, dockerHttpClient);
  }

  public List<String> run() throws InterruptedException {
    File dockerFile = new File("/app/oop-runner" + "/Dockerfile");

    String imageId;
    imageId = dockerClient
        .buildImageCmd().withDockerfile(dockerFile).withTags(Set.of("ooprunner"))
        .exec(new BuildImageResultCallback() {
        }).awaitImageId();

    String containerId = dockerClient.createContainerCmd(imageId)
        .withCmd("java", copiedFilename)
        .exec()
        .getId();

    dockerClient.startContainerCmd(containerId).exec();
    LogContainerCmd logContainer = dockerClient.logContainerCmd(containerId).withStdOut(true)
        .withFollowStream(true);

    List<String> stdouts = new ArrayList<>();
    ResultCallback.Adapter<Frame> callback = new ResultCallback.Adapter<Frame>() {
      @Override
      public void onNext(Frame frame) {
        String log = new String(frame.getPayload());
        stdouts.add(log);
      }
    };

    logContainer.exec(callback);

    dockerClient.waitContainerCmd(containerId).start().awaitCompletion();
    callback.awaitCompletion();
    dockerClient.removeContainerCmd(containerId).exec();

    return stdouts;
  }
}
