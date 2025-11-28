package com.hr.fer.algofer.code.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class CopyCode {
  public static void copy(String code) throws IOException {
    File TemplateMyList = new File("runner/cpp/_temp_mylist.h");
    File HeaderMyList = new File("runner/cpp/mylist.h");

    Files.copy(TemplateMyList.toPath(), HeaderMyList.toPath());
    BufferedWriter bw = new BufferedWriter(new FileWriter(HeaderMyList, true));
    bw.write(code);
    bw.close();
  }

  public static void delete() {
    File HeaderMyList = new File("runner/cpp/mylist.h");
    HeaderMyList.delete();
  }
}
