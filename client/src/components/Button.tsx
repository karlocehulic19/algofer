export function Button({
  children,
  className,
}: {
  children: React.ReactNode;
  className?: string;
}) {
  return (
    <button
      className={className + " py-1.5 px-3 text-white rounded-lg text-xl"}
    >
      {children}
    </button>
  );
}
