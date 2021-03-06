package com.rav.sbactivemq.sbactivemqconsumer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MovieProducer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private Queue queue;
	
	public void send(String message) {
		jmsMessagingTemplate.convertAndSend(queue, message);
	}
}
