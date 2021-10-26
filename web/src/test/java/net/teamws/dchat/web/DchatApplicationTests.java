package net.teamws.dchat.web;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DchatApplicationTests {

	@Autowired
	DchatConfiguration dchatConfiguration;

	@Test
	void initializeContext() {
		assertThat(dchatConfiguration.getChatTitle()).isEqualTo("About Everything");
	}
}
