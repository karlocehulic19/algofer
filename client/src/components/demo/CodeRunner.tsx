"use client";
import { useState } from "react";
import { CodeResultSection } from "../CodeResultSection";

const codeBoilerplate = `class Solution {
  public void zaokruziPi() {
    // add your solution here
  }
}`;

export function CodeRunner() {
  const [usersCode, setUsersCode] = useState(codeBoilerplate);

  return (
    <div className="flex-2">
      <textarea
        className="bg-blue-50 text-black p-1.5 w-full h-[60vh] resize-none"
        value={usersCode}
        onChange={(e) => setUsersCode(e.target.value)}
      ></textarea>
      <CodeResultSection submittedCode={usersCode} />
    </div>
  );
}
