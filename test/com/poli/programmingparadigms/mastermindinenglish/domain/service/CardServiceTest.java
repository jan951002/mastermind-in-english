package com.poli.programmingparadigms.mastermindinenglish.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardType;

public class CardServiceTest {

	private CardService cardService;

	@Before
	public void setUp() {
		cardService = new CardService();
	}

	@Test
	public void loadCardsFromResources_ValidResourceFile_ShouldLoadCards() throws IOException {
		// Given
		final String resourceName = "animals/cards.properties";

		// When
		final List<Card> cards = cardService.loadCardsFromResources(resourceName);

		// Then
		assertNotNull(cards);
		assertFalse(cards.isEmpty());
		final Card firstCard = cards.get(0);
		assertEquals("Bee", firstCard.getText());
		assertEquals("", firstCard.getImagePath());
		assertEquals(CardType.TEXT, firstCard.getType());
		assertFalse(firstCard.isTurnedOver());
		assertEquals("PBEE-01", firstCard.getPairIdentification());
		assertFalse(firstCard.isPaired());
	}

	@Test(expected = FileNotFoundException.class)
	public void loadCardsFromResources_InvalidResourceFile_ShouldThrowFileNotFoundException() throws IOException {
		// Given
		final String invalidResourceName = "path/to/nonexistent/cards.properties";

		// When
		cardService.loadCardsFromResources(invalidResourceName);

		// Then
		// Expecting FileNotFoundException
	}
}
