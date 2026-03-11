package com.hr.fer.algofer.code.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:code/utils/copy.test.properties")
class AlgoferApplicationTests {
  @Value("${COMPARE_COPIED_FILENAME}")
  String compareFilename;
  @Value("${COPIED_OOP_DIR}")
  String copiedDir;
  @Value("${COPIED_OOP_FILENAME}")
  String copiedFilename;

  @Autowired
  CopyOOPCode copyOOPCode;

  @Test
  void testCopying() throws IOException {
    File copied = new ClassPathResource(copiedDir + "/" + copiedFilename).getFile();

    copyOOPCode.copy("Hello World!", copied.getAbsolutePath());

    File expected = new ClassPathResource(copiedDir + "/" + compareFilename).getFile();
    assertEquals(FileUtils.readLines(expected, Charset.defaultCharset()),
        FileUtils.readLines(copied, Charset.defaultCharset()));
  }

  @Test
  void throwsIfFileToCopyToIsMissing() throws IOException {
    File folder = new ClassPathResource(copiedDir).getFile();

    Exception thrownException = assertThrows(IOException.class,
        () -> {
          copyOOPCode.copy("Hello World!", folder.getAbsolutePath() + "/foo.txt");
        });

    assertEquals("File to copy clients solution doesn't exist.", thrownException.getMessage());
  }
}
