package net.teamws.dchat.web.repository;

import net.teamws.dchat.domain.*;

public interface ChatRepository {

	Chat findOne();

	void save(Chat chat);
}
