package com.hr.fer.algofer.code;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.fer.algofer.code.utils.CompileCode;
import com.hr.fer.algofer.code.utils.CopyCode;

@RestController
public class CodeRunnerController {
  private CompileCode compileCode;

  CodeRunnerController(CompileCode compileCode) {
    this.compileCode = compileCode;
  }

  @PostMapping("/api/demo/submit")
  CodeSubmitResult SubmitDemoSolution(@RequestBody CodeSubmit body) {
    try {
      CopyCode.copy(body.code());
      compileCode.compile();
    } catch (Exception e) {
      System.out.println(e);
    }

    CopyCode.delete();
    // HARDCODED UNTIL ACTUAL IMPLEMENTATION IS COMPLETE
    if (body.code().equals("CORRECT SOLUTION")) {
      return new CodeSubmitResult(CodeSubmitResultStatus.PASS);
    }

    return new CodeSubmitResult(CodeSubmitResultStatus.FAIL);
  }
}
