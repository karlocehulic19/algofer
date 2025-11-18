package com.hr.fer.algofer.code;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeRunnerController {
  @PostMapping("/api/demo/submit")
  CodeSubmitResult SubmitDemoSolution(@RequestBody CodeSubmit submittedCode) {
    if (!submittedCode.code().equals("CORRECT SOLUTION")) {
      return new CodeSubmitResult(CodeSubmitResultStatus.FAIL);
    }

    return new CodeSubmitResult(CodeSubmitResultStatus.PASS);
  }
}
