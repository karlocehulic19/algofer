"use client"
import { useEffect, useState } from "react";

export function SessionLoader({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const [sessionLoaded, setSessionLoaded] = useState<boolean>(false);

  useEffect(() => {
    setTimeout(() => {
      localStorage.setItem("session", "DUMMYID");
      setSessionLoaded(true);
      console.log("HELLO WORLD!")
    }, 3000)
  }, [])

  return <>
    {sessionLoaded ?
      <h1>Loaded!</h1> : <h1>Loading!</h1>}
    {children}
  </>
}
