package com.hr.fer.algofer.code;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hr.fer.algofer.code.utils.CompileCode;

@RestController
public class CodeRunnerController {
  @PostMapping("/api/demo/submit")
  CodeSubmitResult SubmitDemoSolution(@RequestBody CodeSubmit submittedCode) {
    try {
      CompileCode.compile();
    } catch (Exception e) {
      System.out.println(e);
    }

    if (!submittedCode.code().equals("CORRECT SOLUTION")) {
      return new CodeSubmitResult(CodeSubmitResultStatus.FAIL);
    }

    return new CodeSubmitResult(CodeSubmitResultStatus.PASS);
  }
}
