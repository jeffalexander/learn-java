package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.BLUE;
import static com.jaa.games.mastermind.CodePin.GREEN;
import static com.jaa.games.mastermind.CodePin.ORANGE;
import static com.jaa.games.mastermind.CodePin.BLACK;
import static com.jaa.games.mastermind.CodePin.YELLOW;
import static com.jaa.games.mastermind.CodePin.PURPLE;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AttemptEngineTest {
	
	private AttemptEngine attempEngine = new AttemptEngine();
	private List<KeyPin> keys;
	private List<CodePin> secretCode;

	@Before
	public void setup() {
		secretCode = null;
		keys = null;
	}
	
	@Test
	public void testCorrectAttempt() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenAttempt(GREEN, BLUE, BLACK, ORANGE);
		thenExpect(0).whitePins();
		thenExpect(4).redPins();
	}
	
	@Test
	public void testCompletelyWrongAttempt() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenAttempt(YELLOW, YELLOW, YELLOW, YELLOW);
		thenExpect(0).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testAttemptWithOneCorrectColor() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenAttempt(YELLOW, GREEN, YELLOW, YELLOW);
		thenExpect(1).whitePin();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testAttemptWithTwoCorrectColor() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenAttempt(YELLOW, GREEN, BLUE, YELLOW);
		thenExpect(2).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testAttemptWithThreeCorrectColor() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenAttempt(YELLOW, GREEN, BLUE, BLACK);
		thenExpect(3).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testAttemptWithFourCorrectColor() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenAttempt(ORANGE, GREEN, BLUE, BLACK);
		thenExpect(4).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testAttemptWithOneCorrectColorAndPosition() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenAttempt(GREEN, YELLOW, YELLOW, YELLOW);
		thenExpect(0).whitePins();
		thenExpect(1).redPin();
	}
	
	@Test
	public void testAttemptWithTwoCorrectColorAndPosition() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenAttempt(GREEN, BLUE, YELLOW, YELLOW);
		thenExpect(0).whitePins();
		thenExpect(2).redPins();
	}
	
	@Test
	public void testAttemptWithThreeCorrectColorAndPosition() {
		givenSecretCodeOf(GREEN, BLUE, BLACK, ORANGE);
		keys = givenAttempt(GREEN, BLUE, BLACK, YELLOW);
		thenExpect(0).whitePins();
		thenExpect(3).redPin();
	}
	
	@Test
	public void testAttemptForCodeWithDupes() {
		givenSecretCodeOf(BLACK, BLACK, GREEN, ORANGE);
		keys = givenAttempt(BLUE, YELLOW, GREEN, BLACK);
		thenExpect(1).whitePins();
		thenExpect(1).redPin();
	}
	
	@Test
	public void testAttemptWithDupesForSimpleCode() {
		givenSecretCodeOf(BLACK, BLUE, GREEN, ORANGE);
		keys = givenAttempt(BLUE, BLACK, GREEN, BLACK);
		thenExpect(2).whitePins();
		thenExpect(1).redPin();
	}
	
	@Test
	public void testJeffAttempt() {
		givenSecretCodeOf(GREEN, GREEN, GREEN, GREEN);
		keys = givenAttempt(GREEN, GREEN, GREEN, GREEN);
		thenExpect(0).whitePins();
		thenExpect(4).redPins();
	}

	@Test
	public void testSimplestCodeEver() {
		givenSecretCodeOf(GREEN);
		keys = givenAttempt(BLACK);
		thenExpect(0).whitePins();
		thenExpect(0).redPins();
	}
	
	@Test
	public void testCodeWithLength7() {
		givenSecretCodeOf(BLACK, BLUE, GREEN, ORANGE, BLUE, PURPLE, YELLOW);
		keys = givenAttempt(BLUE, BLACK, GREEN, BLACK, BLUE, PURPLE, YELLOW);
		thenExpect(2).whitePins();
		thenExpect(4).redPin();
	}
	
	private List<KeyPin> givenAttempt(CodePin... attempt) {
		return attempEngine.validate(secretCode, attempt);
	}
	
	private void givenSecretCodeOf(CodePin... pins) {
		secretCode = Arrays.asList(pins);
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
