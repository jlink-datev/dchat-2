'use strict';

export async function getChat(chatId) {
  return getJson("/api/chats/" + chatId)
}

export async function postChatMessage(chatId, user, text) {
  let path = "api/chats/" + chatId + "/messages";
  let data = {
    user: user,
    text: text
  }
  return await postJson(path, data);
}

async function postJson(path, data) {
  const response = await fetch(path, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  });
  return response.json();
}

async function getJson(path) {
  return fetch(path).then(res => res.json())
}

