package com.poli.programmingparadigms.mastermindinenglish.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.poli.programmingparadigms.mastermindinenglish.domain.exception.GameOverException;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardType;
import com.poli.programmingparadigms.mastermindinenglish.domain.service.CardBoardService;

/**
 * Controller class for managing the memory game in a console-based environment.
 * Allows a player to interactively play the memory game by entering card
 * positions.
 */
public class CardBoardConsoleController {

	/**
	 * Number of columns to show in the matrix.
	 */

	private static final int NUMBER_COLUMS_TO_SHOW = 2;

	/**
	 * Number of rows to show in the matrix.
	 */
	private static final int NUMBER_ROWS_TO_SHOW = 4;

	/**
	 * Service for managing the memory game board.
	 */
	private CardBoardService cardBoardService;

	public CardBoardConsoleController() {
		this.cardBoardService = new CardBoardService();
	}

	/**
	 * Initiates and plays the memory game in the console. The player is prompted to
	 * enter card positions to match until the game is completed.
	 */
	public void play() {
		showWelcomeMessage();

		final List<Card> cards = getAnimalCards();

		Collections.shuffle(cards);

		cardBoardService.newGame(cards);

		try (Scanner scanner = new Scanner(System.in)) {
			while (!cardBoardService.isCompleted()) {
				printMatrix(cards);
				handleUserInput(cards, scanner);
			}
			System.out.println("Game completed");
		} catch (NoSuchElementException e) {
			System.out.println("Invalid input. Please enter a number.");
		}
	}

	/**
	 * Displays a welcome message to the player.
	 */
	private void showWelcomeMessage() {
		System.out.println("Welcome to Mastermind in English!");
		System.out.println("In this memory game, your goal is to match pairs of cards.");
		System.out.println("Each card has a text representation and an image representation.");
		System.out.println("Pay attention to the cards and try to find all the matching pairs.");
		System.out.println("Let the game begin! Have fun!");
		System.out.println();
	}

	/**
	 * Handles user input for selecting a card position.
	 *
	 * @param cards   The list of cards.
	 * @param scanner Scanner for user input.
	 */
	private void handleUserInput(final List<Card> cards, final Scanner scanner) {

		System.out.println("Enter a card position to match. You can enter a value from 0 to " + (cards.size() - 1));
		int cardIndex = -1;

		while (cardIndex < 0 || cardIndex >= cards.size()) {
			if (scanner.hasNextInt()) {
				cardIndex = scanner.nextInt();
				if (cardIndex < 0 || cardIndex >= cards.size()) {
					System.out.println("Enter a valid card position");
				}
			} else {
				System.out.println("Invalid input. Please enter a number.");
				scanner.next();
			}
		}

		processCardSelection(cards, cardIndex);

	}

	/**
	 * Processes the selected card.
	 *
	 * @param cards     The list of cards.
	 * @param cardIndex The index of the selected card.
	 */
	private void processCardSelection(final List<Card> cards, final int cardIndex) {
		try {
			Card selectedCard = cards.get(cardIndex);
			if (selectedCard.isPaired()) {
				System.out.println("Card is already paired");
			} else if (selectedCard.isTurnedOver()) {
				System.out.println("Card is already turned over");
			} else {
				cardBoardService.turnCard(cardIndex);
			}
		} catch (ArrayIndexOutOfBoundsException | GameOverException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Generates a list of animal-themed cards for the memory game.
	 *
	 * @return A list of animal cards with shuffled pairs.
	 */
	private List<Card> getAnimalCards() {
		final List<Card> cards = new ArrayList<Card>();

		cards.add(new Card("dog", null, CardType.TEXT, false, "PDOG-01", false));
		cards.add(new Card(null, "dog-image.jpg", CardType.IMAGE, false, "PDOG-01", false));

		cards.add(new Card("cat", null, CardType.TEXT, false, "PCAT-01", false));
		cards.add(new Card(null, "cat-image.jpg", CardType.IMAGE, false, "PCAT-01", false));

		cards.add(new Card("bee", null, CardType.TEXT, false, "PBEE-01", false));
		cards.add(new Card(null, "bee-image.jpg", CardType.IMAGE, false, "PBEE-01", false));

		cards.add(new Card("spider", null, CardType.TEXT, false, "PSPIDER-01", false));
		cards.add(new Card(null, "spider-image.jpg", CardType.IMAGE, false, "PSPIDER-01", false));

		return cards;
	}

	/**
	 * Prints a matrix representation of the cards to the console.
	 *
	 * @param cards The list of cards to be printed.
	 */
	private void printMatrix(final List<Card> cards) {
		int index = 0;

		for (int i = 0; i < NUMBER_ROWS_TO_SHOW; i++) {
			for (int j = 0; j < NUMBER_COLUMS_TO_SHOW; j++) {
				if (index < cards.size()) {
					final Card card = cards.get(index++);
					final String complementText;
					if (card.isPaired()) {
						complementText = " (OK)";
					} else if (card.isTurnedOver()) {
						complementText = " (Turned)";
					} else {
						complementText = "";
					}
					System.out.printf("%-40s", "(" + (index - 1) + ") " + card.toString() + complementText);
				} else {
					System.out.printf("%-40s", "EMPTY");
				}
			}
			System.out.println();
		}
	}
}
