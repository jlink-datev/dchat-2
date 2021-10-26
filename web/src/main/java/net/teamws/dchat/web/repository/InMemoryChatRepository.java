package net.teamws.dchat.web.repository;

import net.teamws.dchat.domain.*;
import net.teamws.dchat.web.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class InMemoryChatRepository implements ChatRepository {

	private final DchatConfiguration configuration;
	private Chat theChat;

	@Autowired
	public InMemoryChatRepository(DchatConfiguration configuration) {
		this.configuration = configuration;
		this.theChat = createChat();
	}

	@Override
	public synchronized Chat findOne() {
		return copyChat();
	}

	private Chat copyChat() {
		Chat copy = new Chat(theChat.getTitle());
		for (ChatMessage message : theChat.getMessages()) {
			copy.addMessage(message.getTime(), message.getUser(), message.getText());
		}
		return copy;
	}

	private Chat createChat() {
		return new Chat(configuration.getChatTitle());
	}

	@Override
	public void save(Chat chat) {
		theChat = chat;
	}
}
