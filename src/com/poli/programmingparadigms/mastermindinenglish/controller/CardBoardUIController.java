package com.poli.programmingparadigms.mastermindinenglish.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.service.CardBoardService;
import com.poli.programmingparadigms.mastermindinenglish.domain.service.CardService;

/**
 * Controller class for managing the user interface of the memory game. Handles
 * interactions between the CardService (for loading cards) and CardBoardService
 * (for managing the game logic).
 */
public class CardBoardUIController {

	private CardService cardService;

	private CardBoardService cardBoardService;

	/**
	 * Constructs a new CardBoardUIController with the specified CardService and
	 * CardBoardService.
	 *
	 * @param cardService      The service responsible for loading cards from a
	 *                         resource file.
	 * @param cardBoardService The service responsible for managing the memory game
	 *                         logic.
	 */
	public CardBoardUIController(CardService cardService, CardBoardService cardBoardService) {
		this.cardService = cardService;
		this.cardBoardService = cardBoardService;
	}

	/**
	 * Initiates a new game by loading cards from the specified resource file,
	 * shuffling them, and initializing the CardBoardService for the game.
	 *
	 * @param resourceName The name of the resource file containing card
	 *                     information.
	 * @throws FileNotFoundException If the specified resource file is not found.
	 * @throws IOException           If an I/O error occurs while loading cards.
	 */
	public void newGame(final String resourceName) throws FileNotFoundException, IOException {
		final List<Card> cards = cardService.loadCardsFromResources(resourceName);
		Collections.shuffle(cards);
		cardBoardService.newGame(cards);
	}

	/**
	 * Retrieves the list of cards currently in play.
	 *
	 * @return The list of cards in play.
	 */
	public List<Card> getCards() {
		return cardBoardService.getCards();
	}
}
