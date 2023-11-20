package com.poli.programmingparadigms.mastermindinenglish.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CardTest {

	private static final String CARD_TEXT = "card_text";
	private static final String CARD_IMAGE_PATH = "image_path.jgp";
	private static final CardType CARD_TYPE = CardType.TEXT;
	private static final boolean CARD_TURNED_OVER = false;
	private static final String CARD_PAIR_IDENTIFICATION = "PI01";
	private static final boolean CARD_PAIRED = false;

	private Card card;

	@Before
	public void setUp() throws Exception {
		card = new Card(CARD_TEXT, CARD_IMAGE_PATH, CARD_TYPE, CARD_TURNED_OVER, CARD_PAIR_IDENTIFICATION, CARD_PAIRED);
	}

	@Test
	public void testGetText() {
		// When
		final String result = card.getText();
		// Then
		assertEquals(CARD_TEXT, result);
	}

	@Test
	public void testSetText() {
		// Given
		final String newText = "new_text";
		// When
		card.setText(newText);
		// Then
		assertEquals(newText, card.getText());
	}

	@Test
	public void testGetImagePath() {
		// When
		final String result = card.getImagePath();
		// Then
		assertEquals(CARD_IMAGE_PATH, result);
	}

	@Test
	public void testSetImagePath() {
		// Given
		final String newImagePath = "new_image_path";
		// When
		card.setImagePath(newImagePath);
		// Then
		assertEquals(newImagePath, card.getImagePath());
	}

	@Test
	public void testGetType() {
		// When
		final CardType result = card.getType();
		// Then
		assertEquals(CARD_TYPE, result);
	}

	@Test
	public void testSetType() {
		// Given
		final CardType newType = CardType.IMAGE;
		// When
		card.setType(newType);
		// Then
		assertEquals(newType, card.getType());
	}

	@Test
	public void testIsTurnedOver() {
		// When
		final boolean result = card.isTurnedOver();
		// Then
		assertFalse(result);
	}

	@Test
	public void testTurn() {
		// When
		card.turn();
		// Then
		assertTrue(card.isTurnedOver());
	}

	@Test
	public void testGetPairIdentification() {
		// When
		final String result = card.getPairIdentification();
		// Then
		assertEquals(CARD_PAIR_IDENTIFICATION, result);
	}

	@Test
	public void testSetPairIdentification() {
		// Given
		final String newPairIdentification = "new_pair_identification";
		// When
		card.setPairIdentification(newPairIdentification);
		// Then
		assertEquals(newPairIdentification, card.getPairIdentification());
	}

	@Test
	public void testIsPaired() {
		// When
		final boolean result = card.isPaired();
		// Then
		assertFalse(result);
	}

	@Test
	public void testSetPaired() {
		// When
		card.setPaired(true);
		// Then
		assertTrue(card.isPaired());
	}
}
