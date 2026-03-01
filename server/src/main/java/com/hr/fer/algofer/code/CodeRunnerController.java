package com.hr.fer.algofer.code;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.fer.algofer.DockerRunner;
import com.hr.fer.algofer.DockerRunnerOOP;
import com.hr.fer.algofer.code.utils.CompileCode;
import com.hr.fer.algofer.code.utils.CompileOOP;
import com.hr.fer.algofer.code.utils.CopyCode;
import com.hr.fer.algofer.code.utils.CopyOOPCode;

@RestController
public class CodeRunnerController {
  private CompileCode compileCode;
  private DockerRunner dockerRunner;
  private DockerRunnerOOP dockerRunnerOOP;
  private CompileOOP compileOOP;
  private CopyOOPCode copyOOPCode;

  CodeRunnerController(CompileCode compileCode, DockerRunner dockerRunner, CompileOOP compileOOP,
      DockerRunnerOOP dockerRunnerOOP, CopyOOPCode copyOOPCode) {
    this.compileCode = compileCode;
    this.dockerRunner = dockerRunner;
    this.compileOOP = compileOOP;
    this.dockerRunnerOOP = dockerRunnerOOP;
    this.copyOOPCode = copyOOPCode;
  }

  @PostMapping("/api/asp/demo/submit")
  CodeSubmitResult SubmitASPDemoSolution(@RequestBody CodeSubmit body) {
    List<String> stdouts = null;

    try {
      CopyCode.copy(body.code());
      compileCode.compile();
      String testcase = "[MyList,AddUnique,3,AddUnique,3,Remove,3,Contains,3]";
      stdouts = dockerRunner.run(testcase);
    } catch (Exception e) {
      e.printStackTrace();
    }

    CopyCode.delete();
    if (stdouts == null || stdouts.size() == 0) {
      return new CodeSubmitResult(CodeSubmitResultStatus.PASS);
    }

    return new CodeSubmitResult(CodeSubmitResultStatus.FAIL);
  }

  @PostMapping("/api/oop/demo/submit")
  CodeSubmitResult SubmitOOPDemoSolution(@RequestBody CodeSubmit body) {
    List<String> stdouts = null;

    try {
      copyOOPCode.copy(body.code());
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
