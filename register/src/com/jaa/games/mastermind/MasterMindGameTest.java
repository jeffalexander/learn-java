package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.BLUE;
import static com.jaa.games.mastermind.CodePin.GREEN;
import static com.jaa.games.mastermind.CodePin.ORANGE;
import static com.jaa.games.mastermind.CodePin.BLACK;
import static com.jaa.games.mastermind.CodePin.YELLOW;
import static com.jaa.games.mastermind.CodePin.PURPLE;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MasterMindGameTest {
	
	private MasterMindGame game;
	private List<KeyPin> keys;

	@Before
	public void setup() {
		game = new MasterMindGame();
		keys = null;
	}
	
	@Test
	public void testCorrectGuess() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenGuess(GREEN, BLUE, BLACK, ORANGE);
		thenTheCodeHasBeenFound();
		thenExpect(0).whitePins();
		thenExpect(4).redPins();
	}
	
	@Test
	public void testCompletelyWrongGuess() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenGuess(YELLOW, YELLOW, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(0).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithOneCorrectColor() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenGuess(YELLOW, GREEN, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(1).whitePin();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithTwoCorrectColor() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenGuess(YELLOW, GREEN, BLUE, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(2).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithThreeCorrectColor() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenGuess(YELLOW, GREEN, BLUE, BLACK);
		thenTheCodeIsNotFoundYet();
		thenExpect(3).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithFourCorrectColor() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenGuess(ORANGE, GREEN, BLUE, BLACK);
		thenTheCodeIsNotFoundYet();
		thenExpect(4).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithOneCorrectColorAndPosition() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenGuess(GREEN, YELLOW, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(0).whitePins();
		thenExpect(1).redPin();
	}
	
	@Test
	public void testGuessWithTwoCorrectColorAndPosition() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenGuess(GREEN, BLUE, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(0).whitePins();
		thenExpect(2).redPins();
	}
	
	@Test
	public void testGuessWithThreeCorrectColorAndPosition() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenGuess(GREEN, BLUE, BLACK, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(0).whitePins();
		thenExpect(3).redPin();
	}
	
	@Test
	public void testGuessForCodeWithDupes() {
		givenSecretCodeOf(BLACK, BLACK, GREEN, ORANGE);
		keys = givenGuess(BLUE, YELLOW, GREEN, BLACK);
		thenTheCodeIsNotFoundYet();
		thenExpect(1).whitePins();
		thenExpect(1).redPin();
	}
	
	@Test
	public void testGuessWithDupesForSimpleCode() {
		givenSecretCodeOf(BLACK, BLUE, GREEN, ORANGE);
		keys = givenGuess(BLUE, BLACK, GREEN, BLACK);
		thenTheCodeIsNotFoundYet();
		thenExpect(2).whitePins();
		thenExpect(1).redPin();
	}
	
	@Test
	public void testJeffGuess() {
		givenSecretCodeOf(GREEN, GREEN, GREEN, GREEN);
		keys = givenGuess(GREEN, GREEN, GREEN, GREEN);
		thenTheCodeHasBeenFound();
		thenExpect(0).whitePins();
		thenExpect(4).redPins();
	}

	@Test
	public void testSimplestCodeEver() {
		givenSecretCodeOf(GREEN);
		keys = givenGuess(BLACK);
		thenTheCodeIsNotFoundYet();
		thenExpect(0).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testCodeWithLength7() {
		givenSecretCodeOf(BLACK, BLUE, GREEN, ORANGE, BLUE, PURPLE, YELLOW);
		keys = givenGuess(BLUE, BLACK, GREEN, BLACK, BLUE, PURPLE, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(2).whitePins();
		thenExpect(4).redPin();
	}
	
	@Test
	public void testGameNotOver() {
		givenSecretCodeOf(BLACK, BLUE, GREEN, ORANGE);
		keys = givenGuess(BLUE, BLACK, GREEN, BLACK);
		assertTrue(!game.isGameOver());
	}
	
	private List<KeyPin> givenGuess(CodePin... guess) {
		return game.guess(guess);
	}
	
	private void givenSecretCodeOf(CodePin... pins) {
		game.newGame(pins);
	}
	
	private void thenTheCodeHasBeenFound() {
		boolean codeFound = game.codeFound(keys);
		assertTrue(codeFound);
	}
	
	private void thenTheCodeIsNotFoundYet() {
		boolean codeFound = game.codeFound(keys);
		assertTrue(!codeFound);
	}
	
	private PinCountAssert thenExpect(int expectedCount) {
		return new PinCountAssert(expectedCount);
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
			assertEquals(expectedCount, KeyPin.count(KeyPin.WHITE, keys));
			return expectedCount;
		}
		int redPins() {
			return redPin();
		}
		int redPin() {
			assertEquals(expectedCount, KeyPin.count(KeyPin.RED, keys));
			return expectedCount;
		}
	}
}
