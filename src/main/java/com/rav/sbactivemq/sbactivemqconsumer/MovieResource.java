package com.rav.sbactivemq.sbactivemqconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MovieResource {

	@Autowired
	MovieProducer producer;

	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> send(@RequestBody final Movie movie) {
		ObjectMapper objMapper = new ObjectMapper();
		String message;
		try {
			message = objMapper.writeValueAsString(movie);
			producer.send(message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// Return an 202 - Accepted response.
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
