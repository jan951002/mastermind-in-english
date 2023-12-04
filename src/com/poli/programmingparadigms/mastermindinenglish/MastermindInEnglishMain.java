package com.poli.programmingparadigms.mastermindinenglish;

import java.awt.EventQueue;

import com.poli.programmingparadigms.mastermindinenglish.controller.CardBoardConsoleController;
import com.poli.programmingparadigms.mastermindinenglish.controller.CardBoardUIController;
import com.poli.programmingparadigms.mastermindinenglish.domain.service.CardBoardService;
import com.poli.programmingparadigms.mastermindinenglish.domain.service.CardService;
import com.poli.programmingparadigms.mastermindinenglish.view.MastermindInEnglishFrame;

/**
 * Main class for starting the Mastermind in English UI-based memory game. It
 * creates an instance of {@link CardBoardConsoleController} and initiates the
 * game by calling the {@code play()} method.
 */
public class MastermindInEnglishMain {

	/**
	 * The entry point of the application. Creates a
	 * {@code CardBoardConsoleController} and initiates the memory game.
	 *
	 * @param args the command-line arguments (unused in this application).
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				final CardService cardService = new CardService();
				final CardBoardService cardBoardService = new CardBoardService();
				final CardBoardUIController cardBoardUIController = new CardBoardUIController(cardService,
						cardBoardService);

				new MastermindInEnglishFrame(cardBoardUIController);
			}
		});

	}

}
