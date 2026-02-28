
package com.hr.fer.algofer.code.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class CompileOOP {
  public void compile() throws IOException, InterruptedException {
    String tmpDir = System.getProperty("java.io.tmpdir");
    File solutionFile = new File(tmpDir, "Solution.java");

    String[] cmds = { "javac", solutionFile.getAbsolutePath() };

    Process p = Runtime.getRuntime().exec(cmds);
    p.waitFor();
    p.destroy();
  }
}
