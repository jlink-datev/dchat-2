'use strict';

import {getChat, postChatMessage} from "./dchat-api.js";
import {setUsername, getUsername} from "./dchat-username.js";

export function onLoad() {
  route(location.pathname)
}

export async function onSendMessage() {
  let userName = document.getElementById("userName").value;
  let messageText = document.getElementById("messageText").value;
  let newMessage = await postChatMessage("1", userName, messageText);
  document.getElementById("messageText").value = "";
  let messagesElement = document.getElementById("messages");
  addMessageTo(newMessage, messagesElement);
  return false;
}

export function onUserNameChange(event) {
  let newUserName = event.target.value;
  setUsername(newUserName);
}

function initializeUserName() {
  let userName = getUsername();
  document.getElementById("userName").value = userName;
}

function route(pathname) {
  if (pathname === "" || pathname === "/" || pathname === "/index.html") {
    location.pathname = "/chat/1";
  }
  if (pathname.startsWith("/chat/")) {
    let chatId = pathname.substring(6)
    loadChat(chatId);
    initializeUserName();
    pollChat(chatId)
  } else {
    setErrorMessage("Unknown route: " + pathname)
  }
}

async function loadChat(chatId) {
  let chatResponse = await getChat(chatId);
  fillInChat(chatResponse);
}

function pollChat(chatId) {
  setInterval(async () => {
        let chatResponse = await getChat(chatId);
        fillInChat(chatResponse);
      },
      5000
  );
}

function fillInChat(chatResponse) {
  setChatTitle(chatResponse.title);
  setChatMessages(chatResponse.messages)
}

function setChatTitle(title) {
  let titleElement = document.getElementById("chatTitle");
  titleElement.textContent = title;
}

function setChatMessages(messages) {
  let messagesElement = document.getElementById("messages");
  messagesElement.replaceChildren();
  messages.forEach(message => addMessageTo(message, messagesElement))
}

function addMessageTo(message, parent) {
  let messageElements = document.createDocumentFragment();
  let userElement = createUserElement(message.user);
  let textElement = createTextElement(message.time, message.text);

  messageElements.append(userElement, textElement);
  parent.append(messageElements);
  scrollToBottom(parent);
}

function createUserElement(user) {
  let userElement = document.createElement("div");
  userElement.classList.add("user");
  userElement.append(document.createTextNode(user))
  return userElement;
}

function createTextElement(time, text) {
  let textElement = document.createElement("div");
  textElement.classList.add("message");
  let timeElement = document.createElement("span");
  timeElement.classList.add("messageTime");
  timeElement.append(document.createTextNode(time));
  textElement.append(timeElement);
  textElement.append(document.createTextNode(text));
  return textElement;
}

function scrollToBottom(div) {
  div.scrollTop = div.scrollHeight - div.clientHeight;
}

function setErrorMessage(message) {
  let errorElement = document.getElementById("error");
  errorElement.textContent = message;
}


