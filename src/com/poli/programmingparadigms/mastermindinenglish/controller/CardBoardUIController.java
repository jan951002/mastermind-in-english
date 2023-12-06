package com.poli.programmingparadigms.mastermindinenglish.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.poli.programmingparadigms.mastermindinenglish.domain.exception.GameOverException;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardBoard;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardBoardStatus;
import com.poli.programmingparadigms.mastermindinenglish.domain.service.CardService;

/**
 * Controls the user interface interactions for the memory game.
 *
 * <p>
 * The {@code CardBoardUIController} class manages the user interface
 * interactions for the memory game. It interacts with the {@code CardService}
 * to load cards, shuffle them, and handle user actions on the game board.
 * </p>
 */
public class CardBoardUIController {

	private CardService cardService;

	private CardBoard cardBoard;

	/**
	 * Constructs a new {@code CardBoardUIController} with the specified
	 * {@code CardService}.
	 *
	 * @param cardService The card service to load and manage cards.
	 */
	public CardBoardUIController(CardService cardService) {
		this.cardService = cardService;
	}

	/**
	 * Starts a new game with the specified resource name.
	 *
	 * <p>
	 * This method loads cards from the specified resource, shuffles them, and
	 * initializes a new game board.
	 * </p>
	 *
	 * @param resourceName The name of the resource containing card information.
	 * @throws FileNotFoundException If the specified resource file is not found.
	 * @throws IOException           If an I/O error occurs while loading the cards.
	 */
	public void newGame(final String resourceName) throws FileNotFoundException, IOException {
		final List<Card> cards = cardService.loadCardsFromResources(resourceName);
		Collections.shuffle(cards);
		cardBoard = new CardBoard(cards);
	}

	/**
	 * Retrieves the index of the first card turned over on the game board.
	 *
	 * @return The index of the first turned-over card.
	 */
	public int getFirstCardTurnedIndex() {
		return cardBoard.getFirstCardTurnedIndex();
	}

	/**
	 * Retrieves the list of cards currently in play on the game board.
	 *
	 * @return The list of cards in play.
	 */
	public List<Card> getCards() {
		return cardBoard.getCards();
	}

	/**
	 * Retrieves the current status of the game board.
	 *
	 * @return The status of the game board.
	 */
	public CardBoardStatus getGameStatus() {
		return cardBoard.getStaus();
	}

	/**
	 * Turns over a card on the game board.
	 *
	 * @param cardIndex The index of the card to be turned over.
	 * @throws ArrayIndexOutOfBoundsException If the index is out of bounds.
	 */
	public void turnCard(final int cardIndex) throws ArrayIndexOutOfBoundsException {
		cardBoard.turnCard(cardIndex);
	}

	/**
	 * Turns over the second wrong card and handles the consequences.
	 *
	 * @throws GameOverException If the maximum failed attempts are reached.
	 */
	public void turnSecondWrongCard() throws GameOverException {
		cardBoard.turnSecondWrongCard();
	}
}
