package com.poli.programmingparadigms.mastermindinenglish;

import java.awt.EventQueue;

import com.poli.programmingparadigms.mastermindinenglish.controller.CardBoardUIController;
import com.poli.programmingparadigms.mastermindinenglish.domain.service.CardService;
import com.poli.programmingparadigms.mastermindinenglish.view.MastermindInEnglishFrame;

/**
 * The main class to launch the Mastermind in English game.
 *
 * <p>
 * The {@code MastermindInEnglishMain} class contains the main method to start
 * the Mastermind in English game. It initializes the necessary components, such
 * as the {@code CardService}, {@code CardBoardUIController}, and
 * {@code MastermindInEnglishFrame}, to begin the game.
 * </p>
 */
public class MastermindInEnglishMain {

	/**
	 * The main entry point for the Mastermind in English game.
	 *
	 * <p>
	 * This method is responsible for initializing the game components, including
	 * the {@code CardService}, {@code CardBoardUIController}, and
	 * {@code MastermindInEnglishFrame}. It is invoked by the Java Virtual Machine
	 * (JVM) when the program is started.
	 * </p>
	 *
	 * @param args The command-line arguments (not used in this application).
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				final CardService cardService = new CardService();
				final CardBoardUIController cardBoardUIController = new CardBoardUIController(cardService);

				new MastermindInEnglishFrame(cardBoardUIController);
			}
		});

	}

}
