package com.poli.programmingparadigms.mastermindinenglish.view;

import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.poli.programmingparadigms.mastermindinenglish.controller.CardBoardUIController;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;

public class MastermindInEnglishFrame extends JFrame {

	private static final long serialVersionUID = 6841017245016908978L;

	private static final int BORDER_FRAME = 12;

	private static final String TITLE = "Mastermind in english";
	private static final String TITLE_ERROR_LOAD_DATA = "Error on load data";
	private static final String PATH_GAME_ANIMALS = "animals/cards.properties";

	private final CardBoardUIController cardBoardUIController;

	public MastermindInEnglishFrame(CardBoardUIController cardBoardUIController) {
		this.cardBoardUIController = cardBoardUIController;

		setupFrame();
		setVisible(true);
		setupCardBoard();
	}

	private void setupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle(TITLE);
		setLocationRelativeTo(null);
	}

	private void setupCardBoard() {
		try {
			cardBoardUIController.newGame(PATH_GAME_ANIMALS);
			final List<Card> cards = cardBoardUIController.getCards();
			final CardBoardPanel cardBoardPanel = new CardBoardPanel(cards);
			cardBoardPanel
					.setBorder(BorderFactory.createEmptyBorder(BORDER_FRAME, BORDER_FRAME, BORDER_FRAME, BORDER_FRAME));

			add(cardBoardPanel);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), TITLE_ERROR_LOAD_DATA, JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}

	}
}
