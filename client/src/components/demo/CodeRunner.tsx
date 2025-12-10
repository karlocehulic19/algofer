"use client";
import { useState } from "react";
import { CodeResultSection } from "../CodeResultSection";

const codeBoilerplate = `template<typename T>
class MyList{
  private:
    ListElement<T> *head;
  public:
    void AddUnique(T val){
    //vaš kod za AddUnique
    } 
    void Remove(T val){
    // vaš kod za Remove 
    } 
    bool Contains(T val){
    // vaš kod za Contains
    } 
};`;

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
