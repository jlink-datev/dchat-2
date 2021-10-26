package net.teamws.dchat.web.chats;

import java.time.*;
import java.time.format.*;

import net.teamws.dchat.domain.*;

public class ChatMessageResponse {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of("UTC"));
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME.withZone(ZoneId.of("UTC"));

	private final String date;
	private final String time;
	private final String user;
	private final String text;

	public ChatMessageResponse(ChatMessage message) {
		this.date = DATE_FORMATTER.format(message.getTime());
		this.time = TIME_FORMATTER.format(message.getTime());
		this.user = message.getUser();
		this.text = message.getText();
	}

	public String getTime() {
		return time;
	}

	public String getUser() {
		return user;
	}

	public String getText() {
		return text;
	}
}
