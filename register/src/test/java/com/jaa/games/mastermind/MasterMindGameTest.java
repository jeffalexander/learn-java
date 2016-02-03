package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.BLACK;
import static com.jaa.games.mastermind.CodePin.BLUE;
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
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		response = givenGuess(GREEN, BLUE, BLACK, ORANGE);
		thenTheCodeHasBeenFound();
	}
	
	@Test
	public void testCompletelyWrongGuess() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		response = givenGuess(YELLOW, YELLOW, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
	}
	
	@Test
	public void testGuessWithFourCorrectColor() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		response = givenGuess(ORANGE, GREEN, BLUE, BLACK);
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
		givenSecretCodeOf(BLACK, BLUE, GREEN, ORANGE, BLUE, PURPLE, YELLOW);
		response = givenGuess(BLUE, BLACK, GREEN, BLACK, BLUE, PURPLE, YELLOW);
		thenTheCodeIsNotFoundYet();
	}
	
	@Test
	public void testGameNotOver() {
		givenSecretCodeOf(BLACK, BLUE, GREEN, ORANGE);
		response = givenGuess(BLUE, BLACK, GREEN, BLACK);
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
