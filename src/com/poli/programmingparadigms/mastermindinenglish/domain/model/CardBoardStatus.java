package com.poli.programmingparadigms.mastermindinenglish.domain.model;

/**
 * Represents the possible statuses of a game board in the memory game.
 *
 * <p>
 * The {@code CardBoardStatus} enumeration defines three possible states for a
 * game board: IN_PROGRESS, GAME_OVER, and COMPLETED.
 * </p>
 */
public enum CardBoardStatus {

	/**
	 * Represents the state of a game board that is still in progress.
	 */
	IN_PROGRESS,

	/**
	 * Represents the state of a game board that has reached a game over condition.
	 */
	GAME_OVER,

	/**
	 * Represents the state of a game board that has been successfully completed.
	 */
	COMPLETED
}
