package net.teamws.dchat.web;

import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;

@Component
@ConfigurationProperties("dchat")
public class DchatConfiguration {

	private String chatTitle;

	public String getChatTitle() {
		return chatTitle;
	}

	public void setChatTitle(String chatTitle) {
		this.chatTitle = chatTitle;
	}
}
