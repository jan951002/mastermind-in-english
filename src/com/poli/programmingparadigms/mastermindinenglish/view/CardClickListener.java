package com.poli.programmingparadigms.mastermindinenglish.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listens for mouse clicks on a card in the memory game.
 *
 * <p>
 * The {@code CardClickListener} class extends {@code MouseAdapter} and is used
 * to listen for mouse clicks on a card. It provides a callback interface
 * {@code CardClickListenerCallback} to notify the application when a card is
 * clicked.
 * </p>
 */
public class CardClickListener extends MouseAdapter {

	private final int cardIndex;
	private final CardClickListenerCallback callback;

	/**
	 * Constructs a new {@code CardClickListener} for a specific card.
	 *
	 * @param cardIndex The index of the card associated with this listener.
	 * @param callback  The callback interface to notify when a card is clicked.
	 */
	public CardClickListener(int cardIndex, CardClickListenerCallback callback) {
		this.cardIndex = cardIndex;
		this.callback = callback;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		callback.onCardClicked(cardIndex);
	}

	/**
	 * Callback interface to notify when a card is clicked and enable card clicks.
	 */
	public interface CardClickListenerCallback {

		/**
		 * Handles the event when a card is clicked on the game board.
		 *
		 * @param cardIndex The index of the clicked card.
		 */
		void onCardClicked(final int cardIndex);

		/**
		 * Enables the ability to click on cards.
		 */
		void enableCardClicked();
	}
}
