import Link from "next/link";

export default function Home() {
  return (
    <div className="grid grid-rows-2 pt-[35vh] justify-center gap-5">
      <h1 className="text-5xl font-bold">AlgoFER</h1>
      <Link
        href={"/demo"}
        className="text-2xl bg-blue-100 rounded-sm justify-self-center p-1.5 text-indigo-950"
      >
        Demo
      </Link>
    </div>
  );
}
