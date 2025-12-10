"use server";

import { ResultBarStatus } from "@/src/lib/enums";
import { SERVER_URL } from "@/src/lib/constants";

export async function submitCode(submittedCode: string) {
  const res = await fetch(SERVER_URL + "/api/demo/submit", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      code: submittedCode,
    }),
  });

  const resultJson = await res.json();
  return resultJson["result"] == "PASS"
    ? ResultBarStatus.Pass
    : ResultBarStatus.Fail;
}
