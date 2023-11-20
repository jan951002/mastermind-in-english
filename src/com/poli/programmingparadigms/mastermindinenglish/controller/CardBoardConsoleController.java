package com.poli.programmingparadigms.mastermindinenglish.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	 * Initiates and plays the memory game in the console. The player is prompted to
	 * enter card positions to match until the game is completed.
	 */
	public void play() {

		final CardBoardService cardBoardService = new CardBoardService();
		final List<Card> cards = getAnimalCards();

		cardBoardService.newGame(cards);
		printMatrix(cards, 2, 4);

		Scanner scanner;

		while (!cardBoardService.isCompleted()) {
			scanner = new Scanner(System.in);
			System.out.println("Enter a card position to match. You can enter a value from 0 to " + (cards.size() - 1));
			int cardIndex = scanner.nextInt();
			try {
				if (cards.get(cardIndex).isPaired()) {
					System.out.println("Card is already paired");
				} else if (cards.get(cardIndex).isTurnedOver()) {
					System.out.println("Card is already turned over");
				} else {
					cardBoardService.turnCard(cardIndex);
				}
			} catch (ArrayIndexOutOfBoundsException | GameOverException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}

		System.out.println("Game completed");
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

		Collections.shuffle(cards);

		return cards;
	}

	/**
	 * Prints a matrix representation of the cards to the console.
	 *
	 * @param cards   The list of cards to be printed.
	 * @param rows    The number of rows in the matrix.
	 * @param columns The number of columns in the matrix.
	 */
	private void printMatrix(List<Card> cards, int rows, int columns) {
		int index = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (index < cards.size()) {
					Card card = cards.get(index++);
					System.out.printf("%-20s", card.toString());
				} else {
					System.out.printf("%-20s", "EMPTY");
				}
			}
			System.out.println();
		}
	}
}
