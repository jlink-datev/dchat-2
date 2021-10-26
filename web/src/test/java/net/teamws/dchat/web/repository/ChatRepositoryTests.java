package net.teamws.dchat.web.repository;

import java.time.*;

import net.teamws.dchat.domain.*;
import net.teamws.dchat.web.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ChatRepositoryTests {

	@Mock
	DchatConfiguration configuration;

	ChatRepository repository;

	@BeforeEach
	void initRepository() {
		Mockito.when(configuration.getChatTitle()).thenReturn("Just a Chat");
		repository = new InMemoryChatRepository(configuration);
	}

	@Test
	void getTheOneChat() {
		Chat chat = repository.findOne();
		assertThat(chat.getId()).isEqualTo("1");
		assertThat(chat.getTitle()).isEqualTo("Just a Chat");
		assertThat(chat.getMessages()).isEmpty();
	}

	@Test
	void getTheOneChatTwiceWillCreateAFreshOneIfNotSaved() {
		Chat chat1 = repository.findOne();
		chat1.addMessage(Instant.now(), "anyUser", "any text");
		Chat chat2 = repository.findOne();
		assertThat(chat2.getMessages()).isEmpty();
	}

	@Test
	void savingAChatWillSaveMessages() {
		Chat chat1 = repository.findOne();
		Instant now = Instant.now();
		chat1.addMessage(now, "anyUser", "any text");
		repository.save(chat1);

		Chat chat2 = repository.findOne();
		assertThat(chat2.getMessages()).hasSize(1);

		ChatMessage message = chat2.getMessages().get(0);
		assertThat(message.getTime()).isEqualTo(now);
		assertThat(message.getUser()).isEqualTo("anyUser");
		assertThat(message.getText()).isEqualTo("any text");
	}
}
