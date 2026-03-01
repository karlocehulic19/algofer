package com.hr.fer.algofer.code.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CopyOOPCode implements CopyCodeInter {
  @Value("${COPIED_OOP_DIR}")
  private String copiedDir;

  @Value("${COPIED_OOP_FILENAME}")
  private String copiedFilename;

  public void copy(String code) throws IOException {
    File solutionFile = new File(copiedDir, copiedFilename + ".java");

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(solutionFile))) {
      bw.write(code);
    }
  }
}
