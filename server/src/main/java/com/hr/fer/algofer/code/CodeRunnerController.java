package com.hr.fer.algofer.code;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.fer.algofer.DockerRunner;
import com.hr.fer.algofer.code.utils.CompileCode;
import com.hr.fer.algofer.code.utils.CopyCode;

@RestController
public class CodeRunnerController {
  private CompileCode compileCode;
  private DockerRunner dockerRunner;

  CodeRunnerController(CompileCode compileCode, DockerRunner dockerRunner) {
    this.compileCode = compileCode;
    this.dockerRunner = dockerRunner;
  }

  @PostMapping("/api/demo/submit")
  CodeSubmitResult SubmitDemoSolution(@RequestBody CodeSubmit body) {
    List<String> stdouts = null;

    try {
      CopyCode.copy(body.code());
      compileCode.compile();
      String testcase = "[MyList,AddUnique,3,AddUnique,3,Remove,3,Contains,3]";
      stdouts = dockerRunner.run(testcase);
    } catch (Exception e) {
      System.out.println(e);
    }

    CopyCode.delete();
    if (stdouts == null || stdouts.size() == 0) {
      return new CodeSubmitResult(CodeSubmitResultStatus.PASS);
    }

    return new CodeSubmitResult(CodeSubmitResultStatus.FAIL);
  }
}
