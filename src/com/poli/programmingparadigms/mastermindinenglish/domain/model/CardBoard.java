package com.poli.programmingparadigms.mastermindinenglish.domain.model;

import java.util.List;

import com.poli.programmingparadigms.mastermindinenglish.domain.exception.GameOverException;

/**
 * Represents the game board for the memory game.
 *
 * @param cards                 The list of cards on the board.
 * @param failedAttempts        The number of failed attempts by the player.
 * @param firstCardTurnedIndex  The index of the first turned-over card.
 * @param secondCardTurnedIndex The index of the second turned-over card.
 * @param pairCounter           The count of pairs successfully matched.
 * @param completed             Indicates if the game board is completed.
 */
public class CardBoard {

	private final static int MAXIMUM_FAILED_ATTEMPS = 3;
	public final static int VALUE_DEFAULT_TURNED_INDEX = -1;

	private List<Card> cards;
	private int failedAttempts;
	private int firstCardTurnedIndex;
	private int secondCardTurnedIndex;
	private int pairCounter;
	private CardBoardStatus status;

	public CardBoard(List<Card> cards) {
		this.cards = cards;
		this.failedAttempts = 0;
		this.firstCardTurnedIndex = VALUE_DEFAULT_TURNED_INDEX;
		this.secondCardTurnedIndex = VALUE_DEFAULT_TURNED_INDEX;
		this.pairCounter = 0;
		this.status = CardBoardStatus.IN_PROGRESS;
	}

	/**
	 * Retrieves the index of the first card turned over.
	 *
	 * @return The index of the first turned-over card.
	 */
	public int getFirstCardTurnedIndex() {
		return firstCardTurnedIndex;
	}

	/**
	 * Retrieves the list of cards currently in play.
	 *
	 * @return The list of cards in play.
	 */
	public List<Card> getCards() {
		return cards;
	}

	public CardBoardStatus getStaus() {
		return status;
	}

	/**
	 * Turns over a card on the game board.
	 *
	 * @param index The index of the card to be turned over.
	 * @throws ArrayIndexOutOfBoundsException If the index is out of bounds.
	 */
	public void turnCard(int index) throws ArrayIndexOutOfBoundsException {
		if (index >= 0 && index < cards.size()) {
			final Card cardSelected = cards.get(index);
			if (firstCardTurnedIndex == VALUE_DEFAULT_TURNED_INDEX) {
				firstCardTurnedIndex = index;
				cardSelected.turn();
			} else {

				final Card previouslyTurnedCard = cards.get(firstCardTurnedIndex);

				if (cardSelected.getPairIdentification().equals(previouslyTurnedCard.getPairIdentification())) {
					cardSelected.setPaired(true);
					previouslyTurnedCard.setPaired(true);
					pairCounter++;
					setDefaultPositions();
					validateGameStatus();
				}
				secondCardTurnedIndex = index;
				cardSelected.turn();
			}
		} else {
			throw new ArrayIndexOutOfBoundsException("Invalid index");
		}
	}

	/**
	 * Turns over the second wrong card and handles the consequences.
	 *
	 * @throws GameOverException If the maximum failed attempts are reached.
	 */
	public void turnSecondWrongCard() throws GameOverException {
		cards.get(firstCardTurnedIndex).turn();
		cards.get(secondCardTurnedIndex).turn();

		setDefaultPositions();
		countFailedAttemp();
	}

	private void setDefaultPositions() {
		firstCardTurnedIndex = VALUE_DEFAULT_TURNED_INDEX;
		secondCardTurnedIndex = VALUE_DEFAULT_TURNED_INDEX;
	}

	/**
	 * Validates the game status based on the number of pairs successfully matched.
	 * If all pairs are matched, sets the 'completed' flag to true.
	 */
	private void validateGameStatus() {
		if (pairCounter == getTotalPairs()) {
			status = CardBoardStatus.COMPLETED;
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
			status = CardBoardStatus.GAME_OVER;
			throw new GameOverException("Game over");
		}
	}
}
