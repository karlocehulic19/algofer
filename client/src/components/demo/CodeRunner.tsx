import { Button } from "../Button";

export function CodeRunner() {
  return (
    <div className="flex-2">
      <textarea
        className="bg-blue-50 text-black p-1.5 w-full h-[60vh] resize-none"
        defaultValue={`template<typename T>
class MyList{
  private:
    ListElement<T> *head;
  public:
    void AddUnique(T val){ //vaš kod za AddUnique }
    void Remove(T val){ // vaš kod za Remove }
    bool Contains(T val){ // vaš kod za Contains }
};`}
      ></textarea>
      <div>Ovaj zadatak u ispitu nema danih primjera</div>
      <div className="flex gap-4 justify-end">
        <Button className="bg-gray-500 ">Run</Button>
        <Button className="bg-green-500 ">Submit</Button>
      </div>
    </div>
  );
}
