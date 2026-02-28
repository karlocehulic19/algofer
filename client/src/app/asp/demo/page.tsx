import { CodeRunner } from "@/src/components/demo/CodeRunner";
import { Content } from "@/src/components/demo/Content";

export default function page() {
  return (
    <div className="flex p-[5vw] gap-3.5">
      <Content />
      <CodeRunner />
    </div>
  );
}
