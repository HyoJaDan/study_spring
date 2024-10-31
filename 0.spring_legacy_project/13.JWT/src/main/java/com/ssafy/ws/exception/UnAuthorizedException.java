package com.ssafy.ws.exception;

public class UnAuthorizedException extends Exception {

	public UnAuthorizedException() {
		super("유효하지 않은 사용자입니다.");
	}

	public UnAuthorizedException(String message) {
		super(message);
	}

}
