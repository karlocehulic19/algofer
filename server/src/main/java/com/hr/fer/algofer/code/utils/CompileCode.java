package com.hr.fer.algofer.code.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompileCode {
  @Value("${EXECUTABLE_DIR}")
  private String executableDirPath;

  @Value("${EXECUTABLE_NAME}")
  private String executableName;

  private String executablePath;

  CompileCode() {
    File executableDir = new File(executableDirPath);
    executablePath = executableDir.getAbsolutePath() + "/" + executableName;
  }

  public void compile() throws IOException, InterruptedException {
    File demoFile = new File("runner/cpp/demo.cc");
    File splitArrayFile = new File("runner/cpp/utils/split_array.cc");
    File joinArrayFile = new File("runner/cpp/utils/join_array.cc");
    String[] cmds = { "g++", demoFile.getAbsolutePath(), splitArrayFile.getAbsolutePath(),
        joinArrayFile.getAbsolutePath(), "-o", executablePath };

    Process p = Runtime.getRuntime().exec(cmds);
    p.waitFor();
    p.destroy();
  }
}
