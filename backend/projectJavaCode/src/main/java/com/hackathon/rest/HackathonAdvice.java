package com.hackathon.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HackathonAdvice {

	@ExceptionHandler(Exception.class)
	public <T> T handleException(Exception ex) {
		return null;
	}
}
