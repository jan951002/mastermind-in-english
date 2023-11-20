package com.poli.programmingparadigms.mastermindinenglish.domain.model;

import java.util.List;

import com.poli.programmingparadigms.mastermindinenglish.domain.exception.GameOverException;

/**
 * Represents the game board for the memory game.
 *
 * @param cards           The list of cards on the board.
 * @param failedAttempts  The number of failed attempts by the player.
 * @param cardTurnedIndex The index of the currently turned-over card.
 * @param pairCounter     The count of pairs successfully matched.
 * @param completed       Indicates if the game board is completed.
 */
public class CardBoard {

	private final static int MAXIMUM_FAILED_ATTEMPS = 3;
	private final static int VALUE_DEFAULT_TURNED_INDEX = -1;

	private List<Card> cards;
	private int failedAttempts;
	private int cardTurnedIndex;
	private int pairCounter;
	private boolean completed;

	public CardBoard(List<Card> cards) {
		this.cards = cards;
		this.failedAttempts = 0;
		this.cardTurnedIndex = VALUE_DEFAULT_TURNED_INDEX;
		this.pairCounter = 0;
		this.completed = false;
	}

	/**
	 * Checks if the game board is completed.
	 *
	 * @return True if the game board is completed, false otherwise.
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * Turns over a card on the game board.
	 *
	 * @param index The index of the card to be turned over.
	 * @throws GameOverException              If the maximum failed attempts are
	 *                                        reached.
	 * @throws ArrayIndexOutOfBoundsException If the index is out of bounds.
	 */
	public void turnCard(int index) throws GameOverException, ArrayIndexOutOfBoundsException {
		if (index >= 0 && index < cards.size()) {
			final Card cardSelected = cards.get(index);
			if (cardTurnedIndex == VALUE_DEFAULT_TURNED_INDEX) {
				cardTurnedIndex = index;
				cardSelected.turn();
			} else {

				final Card previouslyTurnedCard = cards.get(cardTurnedIndex);

				if (cardSelected.getPairIdentification().equals(previouslyTurnedCard.getPairIdentification())) {
					cardSelected.setPaired(true);
					previouslyTurnedCard.setPaired(true);
					cardSelected.turn();
					pairCounter++;
					validateGameStatus();
					cardTurnedIndex = VALUE_DEFAULT_TURNED_INDEX;
				} else {
					previouslyTurnedCard.turn();
					countFailedAttemp();
				}

			}
		} else {
			throw new ArrayIndexOutOfBoundsException("Invalid index");
		}
	}

	/**
	 * Validates the game status based on the number of pairs successfully matched.
	 * If all pairs are matched, sets the 'completed' flag to true.
	 */
	private void validateGameStatus() {
		if (pairCounter == getTotalPairs()) {
			completed = true;
		}
	}

	/**
	 * Retrieves the total number of pairs on the game board.
	 *
	 * @return The total number of pairs.
	 */
	private int getTotalPairs() {
		return cards.size() / 2;
	}

	/**
	 * Increments the count of failed attempts and throws a GameOverException if the
	 * maximum failed attempts are reached.
	 *
	 * @throws GameOverException If the maximum failed attempts are reached.
	 */
	private void countFailedAttemp() throws GameOverException {
		failedAttempts++;
		if (failedAttempts >= MAXIMUM_FAILED_ATTEMPS) {
			completed = true;
			throw new GameOverException("Game over");
		}
	}
}
