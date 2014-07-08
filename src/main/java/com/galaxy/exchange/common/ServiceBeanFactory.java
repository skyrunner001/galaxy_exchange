package com.galaxy.exchange.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

public class ServiceBeanFactory {
	private static MessageSource messageSource;

	public static MessageSource getMessageSource() {
		return messageSource;
	}

	@Autowired
	@Qualifier("messageSource")
	public void setMessageSource(MessageSource messageSource) {
		ServiceBeanFactory.messageSource = messageSource;
	}
}
