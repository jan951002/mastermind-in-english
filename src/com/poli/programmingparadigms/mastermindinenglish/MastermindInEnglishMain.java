package com.poli.programmingparadigms.mastermindinenglish;

import com.poli.programmingparadigms.mastermindinenglish.controller.CardBoardConsoleController;

/**
 * Main class for starting the Mastermind in English console-based memory game.
 * It creates an instance of {@link CardBoardConsoleController} and initiates
 * the game by calling the {@code play()} method.
 */
public class MastermindInEnglishMain {

	/**
	 * The entry point of the application. Creates a
	 * {@code CardBoardConsoleController} and initiates the memory game.
	 *
	 * @param args the command-line arguments (unused in this application).
	 */
	public static void main(String[] args) {
		final CardBoardConsoleController controller = new CardBoardConsoleController();
		controller.play();
	}

}
