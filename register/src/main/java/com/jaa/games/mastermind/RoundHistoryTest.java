package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.BLACK;
import static com.jaa.games.mastermind.CodePin.TEAL;
import static com.jaa.games.mastermind.CodePin.GREEN;
import static com.jaa.games.mastermind.CodePin.ORANGE;
import static com.jaa.games.mastermind.CodePin.PURPLE;
import static com.jaa.games.mastermind.CodePin.YELLOW;
import static org.junit.Assert.assertTrue;

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
			.withAttempt(TEAL, GREEN, ORANGE, YELLOW)
			.withRedCount(4)
			.build();
		thenTheRoundIsComplete();
	}

	@Test
	public void testIsCompleteWithLostCompleteGame() {
		history.setMaxMoves(2);
		givenMove()
			.withAttempt(TEAL, GREEN, ORANGE, YELLOW)
			.withRedCount(1)
			.withWhiteCount(2)
			.build();
		
		givenMove()
			.withAttempt(TEAL, GREEN, PURPLE, YELLOW)
			.withRedCount(1)
			.withWhiteCount(2)
			.build();
		
		thenTheRoundIsComplete();
	}
	
	@Test
	public void testIsCompleteWithInCompleteGame() {
		history.setMaxMoves(3);
		givenMove()
			.withAttempt(TEAL, GREEN, BLACK, YELLOW)
			.withRedCount(1)
			.withWhiteCount(2)
			.build();
		
		givenMove()
			.withAttempt(TEAL, GREEN, ORANGE, YELLOW)
			.withRedCount(1)
			.withWhiteCount(2)
			.build();
		
		thenTheRoundIsInComplete();
	}
	
	// test helper methods
	
	private MoveBuilder givenMove() {
		return new MoveBuilder();
	}
	
	private class MoveBuilder {
		private List<CodePin> attempt;
		private int redCount;
		private int whiteCount;

		public MoveBuilder withAttempt(CodePin... attempt) {
			this.attempt = Arrays.asList(attempt);
			return this;
		}
		
		public MoveBuilder withRedCount(int redCount) {
			this.redCount = redCount;
			return this;
		}
		
		public MoveBuilder withWhiteCount(int whiteCount) {
			this.whiteCount = whiteCount;
			return this;
		}
		
		public Move build() {
			Move move = new Move();
			move.setAttempt(attempt);
			move.setResponse(redCount, whiteCount);
			history.addMove(move);
			return move;
		}
	}
	
	private void thenTheRoundIsComplete() {
		assertTrue(history.isComplete());
	}
	
	private void thenTheRoundIsInComplete() {
		assertTrue(!history.isComplete());
	}
}
