package com.rav.sbactivemq.sbactivemqconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MovieConsumer {

	private final Logger logger = LoggerFactory.getLogger(MovieConsumer.class);
	
	@JmsListener(destination = "queue")
	public void listen(String mensagem) {
		try {
			Movie movie = new ObjectMapper().readValue(mensagem, Movie.class);
			logger.info("Got a movie: {} - {}", movie.year, movie.title);
		} catch (Exception e) {
            logger.error(e.getMessage());
		}
	}
}
