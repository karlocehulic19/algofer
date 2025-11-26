"use server";

import { SERVER_URL } from "../lib/constants";

export async function submitCodeAction(formData: FormData) {
  if (formData.get("code") == undefined) {
    throw new Error("Invalid from data, missing CODE!");
  }

  fetch(SERVER_URL + "/api/demo/submit", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      code: formData.get("code"),
    }),
  });
}
