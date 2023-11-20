package com.poli.programmingparadigms.mastermindinenglish.domain.exception;

/**
 * Custom exception class for signaling a game over condition in the memory
 * game. This exception is thrown when the maximum number of failed attempts is
 * reached.
 */
public class GameOverException extends Exception {

	private static final long serialVersionUID = 741472390799678433L;

	/**
	 * Constructs a new {@code GameOverException} with the specified detail message.
	 *
	 * @param message the detail message. It is saved for later retrieval by the
	 *                {@link #getMessage()} method.
	 */
	public GameOverException(String message) {
		super(message);
	}
}
