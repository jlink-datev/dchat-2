package net.teamws.dchat.web.chats;

import java.util.*;
import java.util.stream.*;

import net.teamws.dchat.domain.*;

public class ChatResponse {

	private final String title;

	private final List<ChatMessageResponse> messages;
	private final String id;

	public ChatResponse(Chat chat) {
		this.title = chat.getTitle();
		this.id = chat.getId();
		this.messages = toMessageResponses(chat.getMessages());
	}

	private List<ChatMessageResponse> toMessageResponses(List<ChatMessage> messages) {
		return messages.stream()
				   .map(ChatMessageResponse::new)
				   .collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<ChatMessageResponse> getMessages() {
		return messages;
	}

}
