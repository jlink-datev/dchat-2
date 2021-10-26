'use strict';

export function getUsername() {
    return localStorage.getItem("dchat.username") || "";
}

export function setUsername(newUserName) {
    return localStorage.setItem("dchat.username", newUserName);
}