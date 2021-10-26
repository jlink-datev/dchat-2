package net.teamws.dchat.domain;

import java.time.*;

public class ChatMessage implements Comparable<ChatMessage> {
	private final Instant time;
	private final String user;
	private final String text;

	public ChatMessage(Instant time, String user, String text) {
		this.time = time;
		this.user = user;
		this.text = text;
	}

	public Instant getTime() {
		return time;
	}

	public String getUser() {
		return user;
	}

	public String getText() {
		return text;
	}

	@Override
	public int compareTo(ChatMessage other) {
		return getTime().compareTo(other.getTime());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ChatMessage that = (ChatMessage) o;

		if (!time.equals(that.time)) return false;
		if (!user.equals(that.user)) return false;
		return text.equals(that.text);
	}

	@Override
	public int hashCode() {
		int result = time.hashCode();
		result = 31 * result + user.hashCode();
		result = 31 * result + text.hashCode();
		return result;
	}
}
