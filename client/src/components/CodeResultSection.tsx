"use client";
import { useState } from "react";
import { Button } from "./Button";
import { ResultBarStatus } from "../lib/enums";
import { submittedCodeOOP } from "../app/actions/submitCodeOOP";

export function CodeResultSection({
  submittedCode,
}: {
  submittedCode: string;
}) {
  const [status, setStatus] = useState<ResultBarStatus>(ResultBarStatus.NotRun);

  async function componentSubmitCode() {
    const returnedStatus = await submittedCodeOOP(submittedCode);

    setStatus(returnedStatus);
  }

  return (
    <div>
      <div>Ovaj zadatak u ispitu nema danih primjera</div>
      {status != ResultBarStatus.NotRun && (
        <div>
          {status == ResultBarStatus.Pass
            ? "Testcase passed"
            : "Testcase failed"}
        </div>
      )}
      <div className="flex gap-4 justify-end">
        <Button onButtonClick={componentSubmitCode} className="bg-gray-500 ">
          Run
        </Button>
        <Button onButtonClick={componentSubmitCode} className="bg-green-500 ">
          Submit
        </Button>
      </div>
    </div>
  );
}
