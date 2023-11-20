package com.poli.programmingparadigms.mastermindinenglish.domain.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.poli.programmingparadigms.mastermindinenglish.domain.exception.GameOverException;

public class CardBoardTest {

	private CardBoard cardBoard;

	@Before
	public void setUp() throws Exception {
		cardBoard = new CardBoard(getMockedCards());

	}

	@Test
	public void testIsCompleted() {
		// When
		final boolean result = cardBoard.isCompleted();
		// Then
		assertFalse(result);
	}

	@Test
	public void testTurnCardMatchingPairs() throws GameOverException {
		// Given
		CardBoard cardBoard = new CardBoard(getMatchingPairCards());

		// When
		cardBoard.turnCard(0);
		cardBoard.turnCard(1);
		cardBoard.turnCard(2);
		cardBoard.turnCard(3);
		final boolean result = cardBoard.isCompleted();

		// Then
		assertTrue(result);
	}

	@Test(expected = GameOverException.class)
	public void testTurnCardMaxFailedAttempts() throws GameOverException {
		// Given
		CardBoard cardBoard = new CardBoard(getMatchingPairCards());

		// When
		cardBoard.turnCard(0);
		cardBoard.turnCard(2);
		cardBoard.turnCard(0);
		cardBoard.turnCard(2);
		cardBoard.turnCard(0);
		cardBoard.turnCard(2);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testTurnCardArrayIndexOutOfBounds() throws ArrayIndexOutOfBoundsException, GameOverException {
		// Given
		CardBoard cardBoard = new CardBoard(getMatchingPairCards());

		// When
		cardBoard.turnCard(10);
	}

	private List<Card> getMockedCards() {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			cards.add(mock(Card.class));
		}
		return cards;
	}

	private List<Card> getMatchingPairCards() {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card("dog", null, CardType.TEXT, false, "PDOG-01", false));
		cards.add(new Card(null, "dog-image.jpg", CardType.IMAGE, false, "PDOG-01", false));
		cards.add(new Card("cat", null, CardType.TEXT, false, "PCAT-01", false));
		cards.add(new Card(null, "cat-image.jpg", CardType.IMAGE, false, "PCAT-01", false));
		return cards;
	}

}
