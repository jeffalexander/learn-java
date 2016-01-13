package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.BLUE;
import static com.jaa.games.mastermind.CodePin.GREEN;
import static com.jaa.games.mastermind.CodePin.ORANGE;
import static com.jaa.games.mastermind.CodePin.BLACK;
import static com.jaa.games.mastermind.CodePin.YELLOW;
import static com.jaa.games.mastermind.CodePin.PURPLE;
import static com.jaa.games.mastermind.KeyPin.WHITE;
import static com.jaa.games.mastermind.KeyPin.RED;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RoundHistoryTest {

	private RoundHistory history;

	@Before
	public void setup() {
		history = new RoundHistory();
	}
	
	@Test
	public void testIsCompleteWithWinningCompleteGame() {
		givenMove()
			.withAttempt(BLUE, GREEN, ORANGE, YELLOW)
			.andResponse(RED, RED, RED, RED);
		thenTheRoundIsComplete();
	}

	@Test
	public void testIsCompleteWithLostCompleteGame() {
		history.setMaxMoves(2);
		givenMove()
			.withAttempt(BLUE, GREEN, ORANGE, YELLOW)
			.andResponse(WHITE, WHITE, RED);
		
		givenMove()
			.withAttempt(BLUE, GREEN, PURPLE, YELLOW)
			.andResponse(WHITE, WHITE, RED);
		
		thenTheRoundIsComplete();
	}
	
	@Test
	public void testIsCompleteWithInCompleteGame() {
		history.setMaxMoves(3);
		givenMove()
			.withAttempt(BLUE, GREEN, BLACK, YELLOW)
			.andResponse(WHITE, WHITE, RED);
		
		givenMove()
			.withAttempt(BLUE, GREEN, ORANGE, YELLOW)
			.andResponse(WHITE, WHITE, RED);
		
		thenTheRoundIsInComplete();
	}
	
	// test helper methods
	
	private MoveBuilder givenMove() {
		return new MoveBuilder();
	}
	
	private class MoveBuilder {
		private List<CodePin> attempt;

		public MoveBuilder withAttempt(CodePin... attempt) {
			this.attempt = Arrays.asList(attempt);
			return this;
		}
		
		public MoveBuilder andResponse(KeyPin... response) {
			Move move = new Move();
			move.setAttempt(attempt);
			move.setResponse(response);
			history.addMove(move);
			return this;
		}
	}
	
	private void thenTheRoundIsComplete() {
		assertTrue(history.isComplete());
	}
	
	private void thenTheRoundIsInComplete() {
		assertTrue(!history.isComplete());
	}
}
