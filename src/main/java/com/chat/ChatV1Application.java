package com.chat;
import org.slf4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatV1Application {
	
    public static Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ChatV1Application.class, args);
		logger.error("Messege logget at error level");
		logger.warn("Messege logget at warn level");
		logger.info("Messege logget at info level");
		logger.debug("Messege logget at debug level");
	}
}
