package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.BLUE;
import static com.jaa.games.mastermind.CodePin.GREEN;
import static com.jaa.games.mastermind.CodePin.ORANGE;
import static com.jaa.games.mastermind.CodePin.WHITE;
import static com.jaa.games.mastermind.CodePin.YELLOW;
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
		givenSecretCode(GREEN, BLUE, WHITE, ORANGE);
		keys = givenGuess(GREEN, BLUE, WHITE, ORANGE);
		thenTheCodeHasBeenFound();
		thenExpect(0).whitePins();
		thenExpect(4).redPins();
	}
	
	@Test
	public void testCompletelyWrongGuess() {
		givenSecretCode(GREEN, BLUE, WHITE, ORANGE);
		keys = givenGuess(YELLOW, YELLOW, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(0).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithOneCorrectColor() {
		givenSecretCode(GREEN, BLUE, WHITE, ORANGE);
		keys = givenGuess(YELLOW, GREEN, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(1).whitePin();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithTwoCorrectColor() {
		givenSecretCode(GREEN, BLUE, WHITE, ORANGE);
		keys = givenGuess(YELLOW, GREEN, BLUE, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(2).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithThreeCorrectColor() {
		givenSecretCode(GREEN, BLUE, WHITE, ORANGE);
		keys = givenGuess(YELLOW, GREEN, BLUE, WHITE);
		thenTheCodeIsNotFoundYet();
		thenExpect(3).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithFourCorrectColor() {
		givenSecretCode(GREEN, BLUE, WHITE, ORANGE);
		keys = givenGuess(ORANGE, GREEN, BLUE, WHITE);
		thenTheCodeIsNotFoundYet();
		thenExpect(4).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testGuessWithOneCorrectColorAndPosition() {
		givenSecretCode(GREEN, BLUE, WHITE, ORANGE);
		keys = givenGuess(GREEN, YELLOW, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(0).whitePins();
		thenExpect(1).redPin();
	}
	
	@Test
	public void testGuessWithTwoCorrectColorAndPosition() {
		givenSecretCode(GREEN, BLUE, WHITE, ORANGE);
		keys = givenGuess(GREEN, BLUE, YELLOW, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(0).whitePins();
		thenExpect(2).redPins();
	}
	
	@Test
	public void testGuessWithThreeCorrectColorAndPosition() {
		givenSecretCode(GREEN, BLUE, WHITE, ORANGE);
		keys = givenGuess(GREEN, BLUE, WHITE, YELLOW);
		thenTheCodeIsNotFoundYet();
		thenExpect(0).whitePins();
		thenExpect(3).redPin();
	}
	
	@Test
	public void testGuessForCodeWithDupes() {
		givenSecretCode(WHITE, WHITE, GREEN, ORANGE);
		keys = givenGuess(BLUE, YELLOW, GREEN, WHITE);
		thenTheCodeIsNotFoundYet();
		thenExpect(1).whitePins();
		thenExpect(1).redPin();
	}
	
	@Test
	public void testGuessWithDupesForSimpleCode() {
		givenSecretCode(WHITE, BLUE, GREEN, ORANGE);
		keys = givenGuess(BLUE, WHITE, GREEN, WHITE);
		thenTheCodeIsNotFoundYet();
		thenExpect(2).whitePins();
		thenExpect(1).redPin();
	}
	
	@Test
	public void testJeffGuess() {
		givenSecretCode(GREEN, GREEN, GREEN, GREEN);
		keys = givenGuess(GREEN, GREEN, GREEN, GREEN);
		thenTheCodeHasBeenFound();
		thenExpect(0).whitePins();
		thenExpect(4).redPins();
	}
	
	private List<KeyPin> givenGuess(CodePin... guess) {
		return game.guess(guess);
	}
	
	private void givenSecretCode(CodePin... pins) {
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
