package com.vinegrad.dosecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@ImportResource({ "classpath:security.xml" })
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurity {

	public WebSecurity() {
		super();
	}
}
