package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.BLACK;
import static com.jaa.games.mastermind.CodePin.TEAL;
import static com.jaa.games.mastermind.CodePin.GREEN;
import static com.jaa.games.mastermind.CodePin.ORANGE;
import static com.jaa.games.mastermind.CodePin.PURPLE;
import static com.jaa.games.mastermind.CodePin.YELLOW;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MasterMindGameTest {
	
	private MasterMindGame game;
	private Response response;

	@Before
	public void setup() {
		game = new MasterMindGame();
		response = null;
	}
	
	@Test
	public void testCorrectGuess() {
		givenSecretCodeOf(GREEN, TEAL, BLACK, ORANGE);
		response = givenGuess(GREEN, TEAL, BLACK, ORANGE);
		thenTheCodeHasBeenFound();
	}
	
	@Test
	public void testCompletelyWrongGuess() {
		givenSecretCodeOf(GREEN, TEAL, BLACK, ORANGE);
		response = givenGuess(YELLOW, YELLOW, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
	}
	
	@Test
	public void testGuessWithFourCorrectColor() {
		givenSecretCodeOf(GREEN, TEAL, BLACK, ORANGE);
		response = givenGuess(ORANGE, GREEN, TEAL, BLACK);
		thenTheCodeIsNotFoundYet();
	}
	
	@Test
	public void testJeffGuess() {
		givenSecretCodeOf(GREEN, GREEN, GREEN, GREEN);
		response = givenGuess(GREEN, GREEN, GREEN, GREEN);
		thenTheCodeHasBeenFound();
	}

	@Test
	public void testSimplestCodeEver() {
		givenSecretCodeOf(GREEN);
		response = givenGuess(BLACK);
		thenTheCodeIsNotFoundYet();
	}
	
	@Test
	public void testCodeWithLength7() {
		givenSecretCodeOf(BLACK, TEAL, GREEN, ORANGE, TEAL, PURPLE, YELLOW);
		response = givenGuess(TEAL, BLACK, GREEN, BLACK, TEAL, PURPLE, YELLOW);
		thenTheCodeIsNotFoundYet();
	}
	
	@Test
	public void testGameNotOver() {
		givenSecretCodeOf(BLACK, TEAL, GREEN, ORANGE);
		response = givenGuess(TEAL, BLACK, GREEN, BLACK);
		assertTrue(!game.isGameOver());
	}
	
	private Response givenGuess(CodePin... guess) {
		return game.submitAttempt(guess);
	}
	
	private void givenSecretCodeOf(CodePin... pins) {
		game.newGame(pins);
	}
	
	private void thenTheCodeHasBeenFound() {
		boolean codeFound = game.codeFound(response);
		assertTrue(codeFound);
	}
	
	private void thenTheCodeIsNotFoundYet() {
		boolean codeFound = game.codeFound(response);
		assertTrue(!codeFound);
	}
	
	class PinCountAssert {
		int expectedCount;
		PinCountAssert(int expectedCount) {
			this.expectedCount = expectedCount;
		}
		int whitePins() {
			return whitePin();
		}
		int whitePin() {
			assertEquals(expectedCount, response.getWhiteCount());
			return expectedCount;
		}
		int redPins() {
			return redPin();
		}
		int redPin() {
			assertEquals(expectedCount, response.getRedCount());
			return expectedCount;
		}
	}
}
