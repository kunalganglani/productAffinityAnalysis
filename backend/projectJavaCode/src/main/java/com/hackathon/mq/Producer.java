package com.hackathon.mq;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

//@Component
public class Producer {
	
	private static final String EXCHANGE_NAME = "products";
	
	@Autowired
	Channel mqChannel;
	
	public void publish(String message) throws IOException {
		mqChannel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
	}

}
