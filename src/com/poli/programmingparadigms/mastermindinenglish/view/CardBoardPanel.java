package com.poli.programmingparadigms.mastermindinenglish.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.List;

import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardType;

/**
 * Represents the panel displaying the game board in the memory game.
 *
 * <p>
 * The {@code CardBoardPanel} class extends {@code JPanel} and provides the
 * graphical representation of the game board. It manages the layout, labels,
 * and initial card configurations for the memory game.
 * </p>
 */
public class CardBoardPanel extends JPanel {

	private static final long serialVersionUID = 7059943914809733151L;

	private static final int LABEL_SIZE = 100;
	private static final int ROWS = 4;
	private static final int COLUMNS = 4;
	private static final int SPACE_ITEMS = 8;
	private static final int TIME_TO_SHOW_INITIAL_CARDS = 5000;

	private static final String PATH_IMAGE_QUESTION = "question.png";

	private final List<JLabel> cardLabels;
	private final List<Card> cards;
	private CardClickListener.CardClickListenerCallback cardClickListener;

	/**
	 * Constructs a new {@code CardBoardPanel} with the specified list of cards and
	 * card click listener callback.
	 *
	 * @param cards             The list of cards to be displayed on the panel.
	 * @param cardClickListener The callback for card click events.
	 */
	public CardBoardPanel(List<Card> cards, CardClickListener.CardClickListenerCallback cardClickListener) {
		setLayout(new GridLayout(ROWS, COLUMNS, SPACE_ITEMS, SPACE_ITEMS));
		cardLabels = new ArrayList<>();
		this.cards = cards;
		this.cardClickListener = cardClickListener;

		configInitialCard();
		configInitialTimer();
	}

	/**
	 * Updates the graphical representation of a card label on the panel.
	 *
	 * <p>
	 * This method is called to update the display of a card label based on its
	 * state (turned over or not) and type (text or image).
	 * </p>
	 *
	 * @param cardIndex The index of the card to be updated.
	 */
	public void updateCardLabel(int cardIndex) {

		final JLabel label = cardLabels.get(cardIndex);
		final Card card = cards.get(cardIndex);

		if (card.isTurnedOver()) {
			if (card.getType() == CardType.TEXT) {
				label.setIcon(null);
				label.setText(card.getText());
			} else if (card.getType() == CardType.IMAGE) {
				ImageIcon scaledIcon = loadImageIcon(card.getImagePath());
				label.setIcon(scaledIcon);
			}
		} else {
			ImageIcon scaledIcon = loadImageIcon(PATH_IMAGE_QUESTION);
			label.setText("");
			label.setIcon(scaledIcon);
		}

		customizeLabel(label);
		validate();
	}

	private void configInitialCard() {
		for (int i = 0; i < cards.size(); i++) {
			final Card card = cards.get(i);
			JLabel label;
			if (card.getType() == CardType.TEXT) {
				label = createTextLabel(card.getText());
			} else if (card.getType() == CardType.IMAGE) {
				label = createImageLabel(card.getImagePath());
			} else {
				continue;
			}

			cardLabels.add(label);
			add(label);

			label.addMouseListener(new CardClickListener(i, cardClickListener));
		}
	}

	private void configInitialTimer() {
		final Timer timer = new Timer(TIME_TO_SHOW_INITIAL_CARDS, e -> turnCardsDown());
		timer.setRepeats(false);
		timer.start();
	}

	private JLabel createTextLabel(String text) {
		final JLabel label = new JLabel(text);
		final Font customFont = new Font("Arial", Font.BOLD, 16);
		label.setFont(customFont);
		customizeLabel(label);
		return label;
	}

	private JLabel createImageLabel(String imagePath) {
		final JLabel label = new JLabel();

		ImageIcon scaledIcon = loadImageIcon(imagePath);
		label.setIcon(scaledIcon);
		label.setForeground(Color.BLUE);
		customizeLabel(label);

		return label;
	}

	private void customizeLabel(JLabel label) {
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setPreferredSize(new Dimension(LABEL_SIZE, LABEL_SIZE));

		final Border border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(label.getBackground().darker(), 2),
				BorderFactory.createLineBorder(label.getBackground().brighter(), 2));
		label.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	}

	private void turnCardsDown() {
		ImageIcon questionIcon = loadImageIcon(PATH_IMAGE_QUESTION);
		for (JLabel label : cardLabels) {
			final FlipAnimationTarget animationTarget = new FlipAnimationTarget(label);
			animationTarget.startAnimation();
			label.setIcon(questionIcon);
			label.setText("");
			customizeLabel(label);
			cardClickListener.enableCardClicked();
		}
	}

	private ImageIcon loadImageIcon(String imagePath) {
		final URL imageURL = getClass().getClassLoader().getResource(imagePath);

		if (imageURL != null) {
			ImageIcon originalIcon = new ImageIcon(imageURL);
			Image originalImage = originalIcon.getImage();
			Image scaledImage = originalImage.getScaledInstance(LABEL_SIZE, LABEL_SIZE, Image.SCALE_SMOOTH);
			return new ImageIcon(scaledImage);
		} else {
			return new ImageIcon();
		}
	}
}
