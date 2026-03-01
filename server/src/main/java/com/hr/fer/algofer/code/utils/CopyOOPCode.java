package com.hr.fer.algofer.code.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CopyOOPCode implements CopyCodeInter {
  public static void copy(String code) throws IOException {
    File solutionFile = new File("/app/oop-runner/build", "Solution.java");

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(solutionFile))) {
      bw.write(code);
    }
  }
}
