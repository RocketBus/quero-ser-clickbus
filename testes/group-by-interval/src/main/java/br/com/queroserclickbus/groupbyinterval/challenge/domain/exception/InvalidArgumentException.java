package br.com.queroserclickbus.groupbyinterval.challenge.domain.exception;

public class InvalidArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidArgumentException(String message) {
		super(message);
	}
}

