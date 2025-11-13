export default function page() {
  return (
    <div>
      <div>
        <h1>Implementiraj Linearnu Listu</h1>
        <div>
          <span>MI-24/25</span>
          <span>Zadatak 2</span>
          <span>Linearna Lista</span>
        </div>
        <p>
          Potrebno je implementirati metodu AddUnique, Contains i Remove
          jednostruko povezane linearne liste implementirane pokazivačima. Lista
          treba biti ostvarena korištenjem predložaka (eng. templates). Metoda
          AddUnique dodaje novi element na kraj liste samo ako element već ne
          postoji, metoda Remove uklanja prvi element liste sa zadanom
          vrijednosti, a metoda Contains provjerava nalazi li se određeni
          element u listi. Atom liste ListElement i prototipovi funkcija zadani
          su odsječcima koda u nastavku.
        </p>
      </div>
      <div>
        <textarea
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
