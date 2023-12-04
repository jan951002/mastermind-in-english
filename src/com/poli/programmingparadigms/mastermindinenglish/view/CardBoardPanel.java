package com.poli.programmingparadigms.mastermindinenglish.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import java.net.URL;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.List;

import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardType;

public class CardBoardPanel extends JPanel {

	private static final long serialVersionUID = 7059943914809733151L;

	private static final int LABEL_SIZE = 100;
	private static final int ROWS = 4;
	private static final int COLUMNS = 4;
	private static final int SPACE_ITEMS = 8;

	private static final String MESSAGE_ERROR_LOAD_IMAGE = "Error on load image %s. Contact to support";
	private static final String TITLE_ERROR_LOAD_IMAGE = "Error on load image";

	public CardBoardPanel(List<Card> cards) {
		setLayout(new GridLayout(ROWS, COLUMNS, SPACE_ITEMS, SPACE_ITEMS));

		for (Card card : cards) {
			if (card.getType() == CardType.TEXT) {
				add(createTextLabel(card.getText()));
			} else if (card.getType() == CardType.IMAGE) {
				add(createImageLabel(card.getImagePath()));
			}
		}
	}

	private JLabel createTextLabel(String text) {
		final JLabel label = new JLabel(text);
		customizeLabel(label);
		return label;
	}

	private JLabel createImageLabel(String imagePath) {
		final JLabel label = new JLabel();

		final URL imageURL = getClass().getClassLoader().getResource(imagePath);

		if (imageURL != null) {
			final ImageIcon originalIcon = new ImageIcon(imageURL);
			final Image originalImage = originalIcon.getImage();

			final Image scaledImage = originalImage.getScaledInstance(LABEL_SIZE, LABEL_SIZE, Image.SCALE_SMOOTH);

			final ImageIcon scaledIcon = new ImageIcon(scaledImage);
			label.setIcon(scaledIcon);
		} else {
			JOptionPane.showMessageDialog(null, String.format(MESSAGE_ERROR_LOAD_IMAGE, imagePath),
					TITLE_ERROR_LOAD_IMAGE, JOptionPane.ERROR_MESSAGE);
		}

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

}
