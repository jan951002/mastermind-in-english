package com.poli.programmingparadigms.mastermindinenglish.domain.service;

import java.util.List;

import com.poli.programmingparadigms.mastermindinenglish.domain.exception.GameOverException;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardBoard;

/**
 * Service class for managing the memory game board.
 *
 * @param cardBoard The current game board.
 */
public class CardBoardService {

	CardBoard cardBoard;

	/**
	 * Starts a new game with the provided list of cards.
	 *
	 * @param cards The list of cards for the new game.
	 */
	public void newGame(List<Card> cards) {
		cardBoard = new CardBoard(cards);
	}

	/**
	 * Retrieves the list of cards currently in play.
	 *
	 * @return The list of cards in play.
	 */
	public List<Card> getCards() {
		return cardBoard.getCards();
	}

	/**
	 * Checks if the game board is completed.
	 *
	 * @return True if the game board is completed, false otherwise.
	 */
	public boolean isCompleted() {
		return cardBoard.isCompleted();
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
		cardBoard.turnCard(index);
	}

}
