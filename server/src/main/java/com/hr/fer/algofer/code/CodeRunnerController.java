package com.hr.fer.algofer.code;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.fer.algofer.DockerRunnerOOP;
import com.hr.fer.algofer.code.utils.CompileOOP;
import com.hr.fer.algofer.code.utils.CopyOOPCode;

@RestController
public class CodeRunnerController {
  @Value("OOP_COPIED_DIR")
  String oopDir;

  @Value("OOP_COPIED_FILENAME")
  String oopFilename;

  private DockerRunnerOOP dockerRunnerOOP;
  private CompileOOP compileOOP;
  private CopyOOPCode copyOOPCode;

  CodeRunnerController(CompileOOP compileOOP,
      DockerRunnerOOP dockerRunnerOOP, CopyOOPCode copyOOPCode) {
    this.compileOOP = compileOOP;
    this.dockerRunnerOOP = dockerRunnerOOP;
    this.copyOOPCode = copyOOPCode;
  }

  @PostMapping("/api/oop/demo/submit")
  CodeSubmitResult SubmitOOPDemoSolution(@RequestBody CodeSubmit body) {
    List<String> stdouts = null;

    try {
      File solutionFile = new File(oopDir, oopFilename);

      copyOOPCode.copy(body.code(), solutionFile.getAbsolutePath());
      compileOOP.compile();
      stdouts = dockerRunnerOOP.run();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (stdouts == null || stdouts.size() == 0) {
      return new CodeSubmitResult(CodeSubmitResultStatus.PASS);
    }

    return new CodeSubmitResult(CodeSubmitResultStatus.FAIL);
  }
}
