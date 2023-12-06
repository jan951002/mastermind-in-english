package com.poli.programmingparadigms.mastermindinenglish.view;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.poli.programmingparadigms.mastermindinenglish.controller.CardBoardUIController;
import com.poli.programmingparadigms.mastermindinenglish.domain.exception.GameOverException;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardBoard;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardBoardStatus;

/**
 * Represents the main frame for the Mastermind in English memory game.
 *
 * <p>
 * The {@code MastermindInEnglishFrame} class extends {@code JFrame} and serves
 * as the main user interface for the Mastermind in English memory game. It
 * manages the game board, user interactions, and displays relevant messages
 * during the game.
 * </p>
 */
public class MastermindInEnglishFrame extends JFrame implements CardClickListener.CardClickListenerCallback {

	private static final long serialVersionUID = 6841017245016908978L;

	private static final int BORDER_FRAME = 12;

	private static final String TITLE_FRAME = "Mastermind in english";
	private static final String TITLE_ERROR_LOAD_DATA = "Error on load data";
	private static final String TITLE_GAME_OVER = "Game Over";
	private static final String MESSAGE_GAME_OVER = "Game over! \nDo you want to start a new game?";
	private static final String TITLE_GAME_COMPLETED = "Game Completed";
	private static final String MESSAGE_GAME_COMPLETED = "Congratulations! You completed the game.\nDo you want to start a new game?";
	private static final String TITLE_PAIRED_CARD = "Paired card";
	private static final String MESSAGE_PAIRED_CARD = "Card is already paired";
	private static final String TITLE_TURNED_OVER = "Turned over";
	private static final String MESSAGE_TURNED_OVER = "Card is already turned over";
	private static final String TITLE_INVALID_CARD = "Invalid card";
	private static final String MESSAGE_INVALID_CARD = "Oops! Something went wrong with the card.";
	private static final String OPTION_NEW_GAME = "New gamer";
	private static final String OPTION_EXIT = "Exit";
	private static final String PATH_GAME_ANIMALS = "animals/cards.properties";
	private static final String PATH_ICON_GAME_OVER = "game-over.png";
	private static final String PATH_ICON_GAME_COMPLETED = "congratulation.png";
	private static final String PATH_ICON_WARNING = "warning.png";

	private final CardBoardUIController cardBoardUIController;
	private CardBoardPanel cardBoardPanel;
	private List<Card> cards;
	private boolean enabledToClicked;

	/**
	 * Constructs a new {@code MastermindInEnglishFrame} with the specified
	 * {@code CardBoardUIController}.
	 *
	 * @param cardBoardUIController The controller managing the game board.
	 */
	public MastermindInEnglishFrame(final CardBoardUIController cardBoardUIController) {
		this.cardBoardUIController = cardBoardUIController;
		enabledToClicked = false;

		setupFrame();
		setVisible(true);
		setupCardBoard();
	}

	/**
	 * Initializes the main frame properties.
	 */
	private void setupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setTitle(TITLE_FRAME);
		setLocationRelativeTo(null);
	}

	/**
	 * Sets up the initial configuration of the game board.
	 */
	private void setupCardBoard() {
		try {
			cardBoardUIController.newGame(PATH_GAME_ANIMALS);
			cards = cardBoardUIController.getCards();
			cardBoardPanel = new CardBoardPanel(cards, this);
			cardBoardPanel
					.setBorder(BorderFactory.createEmptyBorder(BORDER_FRAME, BORDER_FRAME, BORDER_FRAME, BORDER_FRAME));

			add(cardBoardPanel);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), TITLE_ERROR_LOAD_DATA, JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	/**
	 * Handles the event when a card is clicked on the game board.
	 *
	 * @param cardIndex The index of the clicked card.
	 */
	@Override
	public void onCardClicked(final int cardIndex) {
		if (enabledToClicked) {
			try {
				final Card selectedCard = cards.get(cardIndex);

				if (selectedCard.isPaired()) {
					showMessageDialog(MESSAGE_PAIRED_CARD, TITLE_PAIRED_CARD, loadIconByPath(PATH_ICON_WARNING));
				} else if (selectedCard.isTurnedOver()) {
					showMessageDialog(MESSAGE_TURNED_OVER, TITLE_TURNED_OVER, loadIconByPath(PATH_ICON_WARNING));
				} else {
					processValidCardClick(cardIndex);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				handleException(e);
			}
		}
	}

	/**
	 * Enables the ability to click on cards.
	 */
	@Override
	public void enableCardClicked() {
		enabledToClicked = true;
	}

	private void showMessageDialog(final String message, final String title, final ImageIcon icon) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE, icon);
	}

	private void processValidCardClick(int cardIndex) {
		enabledToClicked = false;
		final int previouslyCardIndex = cardBoardUIController.getFirstCardTurnedIndex();
		cardBoardUIController.turnCard(cardIndex);

		if (previouslyCardIndex == CardBoard.VALUE_DEFAULT_TURNED_INDEX) {
			enabledToClicked = true;
		} else {
			scheduleSecondaryCardHandling(previouslyCardIndex, cardIndex);
		}

		cardBoardPanel.updateCardLabel(cardIndex);

		if (cardBoardUIController.getGameStatus() == CardBoardStatus.COMPLETED) {
			launchMessageGameFinished();
		}
	}

	private void scheduleSecondaryCardHandling(int previouslyCardIndex, int currentCardIndex) {
		final Timer timer = new Timer(500, e -> handleSecondaryCardBehaviour(previouslyCardIndex, currentCardIndex));
		timer.setRepeats(false);
		timer.start();
	}

	private void handleException(Exception e) {
		e.printStackTrace();
	}

	private void handleSecondaryCardBehaviour(final int previouslyCardIndex, final int currentCardIndex) {
		try {
			if (previouslyCardIndex != CardBoard.VALUE_DEFAULT_TURNED_INDEX) {
				if (!cards.get(currentCardIndex).getPairIdentification()
						.equals(cards.get(previouslyCardIndex).getPairIdentification())) {
					cardBoardUIController.turnSecondWrongCard();
					cardBoardPanel.updateCardLabel(previouslyCardIndex);
					cardBoardPanel.updateCardLabel(currentCardIndex);
				}
			}
			enabledToClicked = true;
		} catch (GameOverException e) {
			launchMessageGameFinished();
		}
	}

	private void launchMessageGameFinished() {
		final String message;
		final String title;
		final int messageType;
		final String imagePath;

		switch (cardBoardUIController.getGameStatus()) {
		case GAME_OVER:
			title = TITLE_GAME_OVER;
			message = MESSAGE_GAME_OVER;
			messageType = JOptionPane.ERROR_MESSAGE;
			imagePath = PATH_ICON_GAME_OVER;
			break;
		case COMPLETED:
			title = TITLE_GAME_COMPLETED;
			message = MESSAGE_GAME_COMPLETED;
			messageType = JOptionPane.INFORMATION_MESSAGE;
			imagePath = PATH_ICON_GAME_COMPLETED;
			break;
		default:
			title = TITLE_INVALID_CARD;
			message = MESSAGE_INVALID_CARD;
			messageType = JOptionPane.WARNING_MESSAGE;
			imagePath = PATH_ICON_WARNING;
			break;
		}

		final ImageIcon icon = loadIconByPath(imagePath);
		final String[] options = { OPTION_NEW_GAME, OPTION_EXIT };
		final int choice = showFinishedOptionDialog(message, title, messageType, icon, options);

		handleOptionChoice(choice);
	}

	private ImageIcon loadIconByPath(final String imagePath) {
		final URL imageURL = getClass().getClassLoader().getResource(imagePath);

		if (imageURL != null) {
			ImageIcon originalIcon = new ImageIcon(imageURL);
			Image originalImage = originalIcon.getImage();
			Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			return new ImageIcon(scaledImage);
		} else {
			return new ImageIcon();
		}
	}

	private static int showFinishedOptionDialog(final String message, final String title, final int messageType,
			final ImageIcon icon, final String[] options) {

		return JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_OPTION, messageType, icon, options,
				options[0]);
	}

	private void handleOptionChoice(int choice) {
		switch (choice) {
		case JOptionPane.YES_OPTION:
			resetFrame();
			break;
		case JOptionPane.NO_OPTION:
			this.dispose();
			break;
		default:
			break;
		}
	}

	private void resetFrame() {
		getContentPane().removeAll();
		setupCardBoard();
		validate();
		repaint();
	}
}
