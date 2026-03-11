
package com.hr.fer.algofer.code.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompileOOP {
  @Value("${COPIED_OOP_DIR}")
  private String copiedDir;

  @Value("${COPIED_OOP_FILENAME}")
  private String copiedFilename;

  public void compile() throws IOException, InterruptedException {
    File solutionFile = new File(copiedDir, copiedFilename + ".java");
    if (!solutionFile.exists()) {
      throw new IOException("Solutions file doesn't exist.");
    }

    String[] cmds = { "javac", solutionFile.getAbsolutePath() };

    Process p = Runtime.getRuntime().exec(cmds);
    p.waitFor();
    p.destroy();
  }
}
