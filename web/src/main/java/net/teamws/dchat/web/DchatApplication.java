package net.teamws.dchat.web;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.servlet.error.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class DchatApplication implements ErrorController {

	public static void main(String[] args) {
		SpringApplication.run(DchatApplication.class, args);
	}
}
