import { Category } from "@/src/components/Category";

export default function page() {
  return (
    <div className="flex p-[5vw] gap-3.5">
      <div className="flex flex-3 bg-blue-100 text-black p-3 gap-2 flex-col">
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
      </div>
      <div className="flex-2">
        <textarea
          className="bg-blue-50 text-black p-1.5 w-[100%] h-[60vh] resize-none"
          defaultValue={`
// Definicija elementa liste
// template<typename T>
// class ListElement{
//  public:
//  T i;
//  ListElement<T>* next;
// };



template<typename T>
class MyList{
 private:
 ListElement<T> *head;
 public:
 void AddUnique(T val){ //vaš kod za AddUnique }
 void Remove(T val){ // vaš kod za Remove }
 bool Contains(T val){ // vaš kod za Contains }
};

`}
        ></textarea>

        <div>This is demo page!</div>
      </div>
    </div>
  );
}
