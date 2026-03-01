
package com.hr.fer.algofer.code.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class CompileOOP {
  public void compile() throws IOException, InterruptedException {
    File solutionFile = new File("/app/oop-runner/build", "Solution.java");

    String[] cmds = { "javac", solutionFile.getAbsolutePath() };

    Process p = Runtime.getRuntime().exec(cmds);
    p.waitFor();
    p.destroy();
  }
}
