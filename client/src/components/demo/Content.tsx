import { Category } from "../Category";

export function Content() {
  return (
    <div className="flex flex-3 bg-blue-100 text-black p-7 gap-2 flex-col">
      <h1 className="text-3xl underline">Implementiraj Linearnu Listu</h1>
      <div className="flex gap-2.5">
        <Category>MI-24/25</Category>
        <Category>Zad 2</Category>
        <Category>Linearna Lista</Category>
      </div>
      <pre className="whitespace-break-spaces">
        {`
Potrebno je implementirati metodu AddUnique, Contains i Remove jednostruko povezane linearne liste implementirane pokazivačima.

Lista treba biti ostvarena korištenjem predložaka (eng. templates). Metoda AddUnique dodaje novi element na kraj liste samo ako element već ne postoji, metoda Remove uklanja prvi element liste sa zadanom vrijednosti, a metoda Contains provjerava nalazi li se određeni element u listi.

Atom liste ListElement i prototipovi funkcija zadani
su odsječcima koda u nastavku.

`}
      </pre>

      <div className="flex gap-3 flex-col">
        <h2 className="text-xl">Prototipovi:</h2>
        <div className="flex">
          <pre className="p-1 bg-blue-50">
            {`template<typename T>
class ListElement{
  public:
    T i;
    ListElement<T>* next;
};`}
          </pre>
        </div>
      </div>
    </div>
  );
}
