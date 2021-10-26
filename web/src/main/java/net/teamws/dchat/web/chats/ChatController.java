package net.teamws.dchat.web.chats;

import java.time.*;
import java.time.temporal.*;

import net.teamws.dchat.domain.*;
import net.teamws.dchat.web.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

@RestController
@RequestMapping(path = ChatController.CHATS_PATH)
public class ChatController {

	public static final String CHATS_PATH = "/api/chats/";

	private ChatController(ChatRepository repository) {
		this.repository = repository;
	}

	private ChatRepository repository;

	@GetMapping(path = "{id}")
	public ChatResponse getChat(@PathVariable String id) {
		ensureIdIs1(id);
		Chat chat = repository.findOne();
		return new ChatResponse(chat);
	}

	@PostMapping(path = "{id}/messages")
	public ChatMessageResponse postMessageToChat(
		@PathVariable String id,
		@RequestBody ChatMessageRequest messageRequest
	) {
		ensureIdIs1(id);
		Chat chat = repository.findOne();
		ChatMessage newMessage = addMessageToChat(messageRequest, chat);
		repository.save(chat);
		return new ChatMessageResponse(newMessage);
	}

	private ChatMessage addMessageToChat(ChatMessageRequest messageRequest, Chat chat) {
		return chat.addMessage(Instant.now().truncatedTo(ChronoUnit.SECONDS), messageRequest.getUser(), messageRequest.getText());
	}

	private void ensureIdIs1(@PathVariable String id) {
		if (!id.equals("1")) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, String.format("no chat with id [%s]", id)
			);
		}
	}
}
