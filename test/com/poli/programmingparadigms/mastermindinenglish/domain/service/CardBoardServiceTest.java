package com.poli.programmingparadigms.mastermindinenglish.domain.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.poli.programmingparadigms.mastermindinenglish.domain.exception.GameOverException;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.Card;
import com.poli.programmingparadigms.mastermindinenglish.domain.model.CardType;

@RunWith(MockitoJUnitRunner.class)
public class CardBoardServiceTest {

	private CardBoardService cardBoardService;

	@Before
	public void setUp() throws Exception {
		cardBoardService = new CardBoardService();
	}

	@Test
	public void newGame_ShouldInitializeCardBoard() {
		// Given
		final List<Card> cards = createMockCards();
		// When
		cardBoardService.newGame(cards);
		// Then
		assertNotNull(cardBoardService.cardBoard);
	}

	@Test
	public void isCompleted_WhenGameBoardIsNotCompleted_ShouldReturnFalse() {
		// Given
		final List<Card> cards = createMockCards();
		cardBoardService.newGame(cards);
		// When
		final boolean result = cardBoardService.isCompleted();
		// Then
		assertFalse(result);
	}

	@Test
	public void isCompleted_WhenGameBoardIsCompleted_ShouldReturnTrue()
			throws ArrayIndexOutOfBoundsException, GameOverException {
		// Given
		final List<Card> cards = createMockCards();
		cardBoardService.newGame(cards);
		cardBoardService.turnCard(0);
		cardBoardService.turnCard(1);
		// When
		final boolean result = cardBoardService.isCompleted();
		// Then
		assertTrue(result);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void turnCard_WhenIndexIsInvalid_ShouldThrowArrayIndexOutOfBoundsException()
			throws ArrayIndexOutOfBoundsException, GameOverException {
		// Given
		List<Card> cards = createMockCards();
		cardBoardService.newGame(cards);
		// When
		cardBoardService.turnCard(-1);
	}

	private List<Card> createMockCards() {
		final List<Card> cards = new ArrayList<>();

		cards.add(new Card("A", null, CardType.TEXT, false, "A-1", false));
		cards.add(new Card(null, "image.jpg", CardType.IMAGE, false, "A-1", false));

		return cards;
	}
}
