"use clinet";

import { MouseEventHandler } from "react";

export function Button({
  children,
  className,
  onButtonClick,
}: {
  children: React.ReactNode;
  className?: string;
  onButtonClick?: MouseEventHandler<HTMLButtonElement> | undefined;
}) {
  return (
    <button
      type="submit"
      className={className + " py-1.5 px-3 text-white rounded-lg text-xl"}
      onClick={onButtonClick}
    >
      {children}
    </button>
  );
}
