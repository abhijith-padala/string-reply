package com.beta.replyservice;

public class ReplyMessage {

	private final int statusCode;
	private final String message;


	public ReplyMessage(String message) {
		this.statusCode = 200;
		this.message = message;
	}

	public ReplyMessage(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}
}