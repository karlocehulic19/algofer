import { Category } from "../Category";

export function Content() {
  return (
    <div className="flex flex-3 bg-blue-100 text-black p-7 gap-2 flex-col">
      <h1 className="text-3xl underline">Zaokruzi PI</h1>
      <div className="flex gap-2.5">
        <Category>VJ-1-2</Category>
        <Category>Zad 1</Category>
        <Category>System Out</Category>
      </div>
      <pre className="whitespace-break-spaces">
        {`
Broj π može se izračunati preko sljedeće formule: 𝜋 4 = ∑ (−1) 𝑖 2𝑖+1 ∞ 𝑖=0
Ispisati izračunatu vrijednost zaokruženo na 10 decimala nakon 10, 100, 1000, … i 1 000 000 sumanada.
`}
      </pre>
    </div>
  );
}
