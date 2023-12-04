package com.poli.programmingparadigms.mastermindinenglish.domain.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardType;

/**
 * Service class providing functionality related to the management and loading
 * of Card objects from a resource file.
 */
public class CardService {

	/**
	 * Error message for when the resource file is not found.
	 */
	private final static String MESSAGE_FILE_NOT_FOUND = "Sorry, unable to find %s. Contact to the support";

	/**
	 * Prefix for card-related properties in the resource file.
	 */
	private final static String PREFIX_CARD = "card";

	/**
	 * Prefix for the text property in card-related properties.
	 */
	private final static String PREFIX_TEXT = ".text";

	/**
	 * Prefix for the image path property in card-related properties.
	 */
	private final static String PREFIX_IMAGE_PATH = ".imagePath";

	/**
	 * Prefix for the type property in card-related properties.
	 */
	private final static String PREFIX_TYPE = ".type";

	/**
	 * Prefix for the turned-over property in card-related properties.
	 */
	private final static String PREFIX_TURNED_OVER = ".turnedOver";

	/**
	 * Prefix for the pair identification property in card-related properties.
	 */
	private final static String PREFIX_PAIR_IDENTIFICATION = ".pairIdentification";

	/**
	 * Prefix for the paired property in card-related properties.
	 */
	private final static String PREFIX_PAIRED = ".paired";

	/**
	 * Properties object to store card-related information loaded from the resource
	 * file.
	 */
	private final Properties properties;

	/**
	 * Constructs a CardService with an empty Properties object.
	 */
	public CardService() {
		properties = new Properties();
	}

	/**
	 * Loads a list of cards from the specified resource file.
	 *
	 * @param resourceName The name of the resource file.
	 * @return A list of Card objects loaded from the resource file.
	 * @throws FileNotFoundException If the specified resource file is not found.
	 * @throws IOException           If an I/O error occurs while reading the
	 *                               resource file.
	 */
	public List<Card> loadCardsFromResources(final String resourceName) throws FileNotFoundException, IOException {

		final List<Card> cards = new ArrayList<>();

		try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourceName)) {
			if (input == null) {
				throw new FileNotFoundException(String.format(MESSAGE_FILE_NOT_FOUND, resourceName));
			}

			properties.load(input);
		}

		int cardIndex = 1;
		while (properties.containsKey(PREFIX_CARD + cardIndex + PREFIX_TEXT)) {

			final Card card = buildCardByIndex(cardIndex);
			cards.add(card);

			cardIndex++;
		}
		return cards;

	}

	/**
	 * Retrieves a property value based on the specified property key and index.
	 *
	 * @param property The property to retrieve (e.g., ".text", ".imagePath").
	 * @param index    The index of the card.
	 * @return The property value associated with the specified key and index.
	 */
	private String getPropertyByKey(String property, int index) {
		return properties.getProperty(PREFIX_CARD + index + property);
	}

	/**
	 * Builds a Card object based on the specified index.
	 *
	 * @param index The index of the card to build.
	 * @return A Card object constructed from properties in the resource file.
	 */
	private Card buildCardByIndex(int index) {
		final String text = getPropertyByKey(PREFIX_TEXT, index);
		final String imagePath = getPropertyByKey(PREFIX_IMAGE_PATH, index);
		final CardType type = CardType.valueOf(getPropertyByKey(PREFIX_TYPE, index));
		final boolean turnedOver = Boolean.parseBoolean(getPropertyByKey(PREFIX_TURNED_OVER, index));
		final String pairIdentification = getPropertyByKey(PREFIX_PAIR_IDENTIFICATION, index);
		final boolean paired = Boolean.parseBoolean(getPropertyByKey(PREFIX_PAIRED, index));

		return new Card(text, imagePath, type, turnedOver, pairIdentification, paired);
	}
}
