package com.hr.fer.algofer.code.utils;

import java.io.File;
import java.io.IOException;

public class CompileCode {
  public static void compile() throws IOException, InterruptedException {
    File compilationDir = new File("runner");
    File demoFile = new File("runner/cpp/demo.cc");
    File splitArrayFile = new File("runner/cpp/utils/split_array.cc");
    File joinArrayFile = new File("runner/cpp/utils/join_array.cc");
    String runExecutableAbsolutePath = compilationDir.getAbsolutePath() + "/" + "run.out";

    String[] cmds = { "g++", demoFile.getAbsolutePath(), splitArrayFile.getAbsolutePath(),
        joinArrayFile.getAbsolutePath(), "-o", runExecutableAbsolutePath };
    Process p = Runtime.getRuntime().exec(cmds);
    p.waitFor();
    p.destroy();
  }
}
