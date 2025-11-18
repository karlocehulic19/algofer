package com.hr.fer.algofer.code;

enum CodeSubmitResultStatus {
  PASS,
  FAIL,
  COMPILE_ERROR,
  TIMEOUT
}

public record CodeSubmitResult(CodeSubmitResultStatus result) {
}
