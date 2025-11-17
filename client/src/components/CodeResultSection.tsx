import { Button } from "./Button";

export function CodeResultSection() {
  return (
    <div>
      <div>Ovaj zadatak u ispitu nema danih primjera</div>
      <div className="flex gap-4 justify-end">
        <Button className="bg-gray-500 ">Run</Button>
        <Button className="bg-green-500 ">Submit</Button>
      </div>
    </div>
  );
}
