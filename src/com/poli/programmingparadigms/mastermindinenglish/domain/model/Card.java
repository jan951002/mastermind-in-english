package com.poli.programmingparadigms.mastermindinenglish.domain.model;

/**
 * Represents a card in the memory game.
 *
 * @param text               The text associated with the card.
 * @param imagePath          The path of the image associated with the card.
 * @param type               The type of the card (IMAGE or TEXT).
 * @param turnedOver         Indicates if the card is turned over.
 * @param pairIdentification The identification associated with the card's pair.
 * @param paired             Indicates if the card is paired.
 */
public class Card {

	private String text;
	private String imagePath;
	private CardType type;
	private boolean turnedOver;
	private String pairIdentification;
	private boolean paired;

	public Card(String text, String imagePath, CardType type, boolean turnedOver, String partnerIdentification,
			boolean paired) {

		this.text = text;
		this.imagePath = imagePath;
		this.type = type;
		this.turnedOver = turnedOver;
		this.pairIdentification = partnerIdentification;
		this.paired = paired;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public boolean isTurnedOver() {
		return turnedOver;
	}

	public void turn() {
		this.turnedOver = !turnedOver;
	}

	public String getPairIdentification() {
		return pairIdentification;
	}

	public void setPairIdentification(String partnerIdentification) {
		this.pairIdentification = partnerIdentification;
	}

	public boolean isPaired() {
		return paired;
	}

	public void setPaired(boolean paired) {
		this.paired = paired;
	}

	@Override
	public String toString() {
		String result;
		if (type == CardType.TEXT) {
			result = text;
		} else {
			result = imagePath;
		}
		return result;
	}

}
