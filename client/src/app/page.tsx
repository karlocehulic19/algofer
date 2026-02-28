import Link from "next/link";

export default function Home() {
  return (
    <div className="grid pt-[35vh] justify-center gap-5">
      <h1 className="text-5xl font-bold pb-10 text-center">AlgoFER</h1>
      <div className="flex gap-10 justify-center flex-col">
        <Link
          href={"/oop/demo"}
          className="text-2xl bg-blue-100 rounded-sm justify-self-center p-1.5 text-indigo-950 text-center w-[20vw]"
        >
          OOP
        </Link>
        <Link
          href={"/asp/demo"}
          className="text-2xl bg-blue-100 rounded-sm justify-self-center p-1.5 text-indigo-950 text-center w-[20vw]"
        >
          ASP
        </Link>
      </div>
    </div>
  );
}
