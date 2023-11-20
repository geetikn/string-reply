package com.beta.replyservice;

import java.util.Objects;

public class ReplyMessage {

	private final String message;

	public ReplyMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ReplyMessage replyMessage = (ReplyMessage) o;
		return message.equals(replyMessage.message);
	}
}