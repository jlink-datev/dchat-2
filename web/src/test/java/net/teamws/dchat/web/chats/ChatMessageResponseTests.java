package net.teamws.dchat.web.chats;

import java.time.*;

import net.teamws.dchat.domain.*;
import net.teamws.dchat.web.chats.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class ChatMessageResponseTests {
	@Test
	void mapChatMessage() {
		LocalDateTime time = LocalDateTime.of(2021, Month.APRIL, 27, 7, 1, 9);
		ChatMessage message = new ChatMessage(time.toInstant(ZoneOffset.UTC), "aUser", "a message text");

		ChatMessageResponse response = new ChatMessageResponse(message);
		assertThat(response.getTime()).isEqualTo("07:01:09");
		assertThat(response.getUser()).isEqualTo("aUser");
		assertThat(response.getText()).isEqualTo("a message text");
	}
}
