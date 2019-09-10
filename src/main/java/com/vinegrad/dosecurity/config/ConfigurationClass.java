package com.vinegrad.dosecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ImportResource({ "classpath:config.xml" })
public class ConfigurationClass implements WebMvcConfigurer {

	public ConfigurationClass() {
		super();
	}
}
