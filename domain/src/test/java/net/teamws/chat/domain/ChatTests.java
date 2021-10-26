package net.teamws.chat.domain;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import net.teamws.dchat.domain.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class ChatTests {

	@Nested
	class Title {
		@Test
		void createChatWithTitle() {
			Chat chat = new Chat("My little chat");
			assertThat(chat.getId()).isEqualTo("1");
			assertThat(chat.getTitle()).isEqualTo("My little chat");
		}

		@Test
		void changeChatTitle() {
			Chat chat = new Chat("My little chat");
			chat.setTitle("My other chat");
			assertThat(chat.getTitle()).isEqualTo("My other chat");
		}
	}

	@Nested
	class Messages {

		Chat chat = new Chat("My little chat");

		@Test
		void noMessagesInitially() {
			assertThat(chat.getMessages()).isEmpty();
		}

		@Test
		void addingSingleMessage() {
			Instant now = Instant.now();
			ChatMessage newMessage = chat.addMessage(now, "frank", "Hi.");
			assertThat(newMessage.getTime()).isEqualTo(now);
			assertThat(newMessage.getUser()).isEqualTo("frank");
			assertThat(newMessage.getText()).isEqualTo("Hi.");

			assertThat(chat.getMessages()).hasSize(1);
			ChatMessage message = chat.getMessages().get(0);
			assertThat(message).isEqualTo(newMessage);
		}

		@Test
		void addingSeveralMessages() {
			Instant now = Instant.now();
			chat.addMessage(now, "frank", "Hi.");
			chat.addMessage(now.plusSeconds(10), "emmy", "Hi too");
			chat.addMessage(now.plusSeconds(20), "frank", "Nice to see you");

			assertThat(chat.getMessages()).hasSize(3);
		}

		@Test
		void messagesAreOrderedByTime() {
			Instant firstTime = Instant.now();
			Instant secondTime = firstTime.plusSeconds(10);
			chat.addMessage(secondTime, "emmy", "Hi too");
			chat.addMessage(firstTime, "frank", "Hi.");

			List<Instant> times = chat.getMessages().stream().map(ChatMessage::getTime).collect(Collectors.toList());
			assertThat(times).containsExactly(
				firstTime, secondTime
			);
		}
	}
}
