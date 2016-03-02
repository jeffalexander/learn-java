package com.jaa.games.mastermind.player;

import static com.jaa.games.mastermind.CodePin.BLACK;
import static com.jaa.games.mastermind.CodePin.TEAL;
import static com.jaa.games.mastermind.CodePin.GREEN;
import static com.jaa.games.mastermind.CodePin.ORANGE;
import static com.jaa.games.mastermind.CodePin.PURPLE;
import static com.jaa.games.mastermind.CodePin.YELLOW;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jaa.games.mastermind.CodePin;
import com.jaa.games.mastermind.MasterMindGame;
import com.jaa.games.mastermind.Move;
import com.jaa.games.mastermind.RoundHistory;

public class ShiftStrategyTest {

	private ShiftStrategy shiftStrategy;
	private MasterMindGame game;

	@Before
	public void setup() {
		shiftStrategy = new ShiftStrategy();
		game = new MasterMindGame();
	}
	
	@Test
	public void testCorrectAttempt() {
		game.newGame(GREEN, GREEN, GREEN, GREEN);
		boolean gameWon = shiftStrategy.play(game);
		RoundHistory history = game.getHistory();
		assertEquals(Arrays.asList(GREEN, BLACK, PURPLE, YELLOW), history.getMoves().get(0).getAttempt());
		assertEquals(Arrays.asList(BLACK, PURPLE, YELLOW, ORANGE), history.getMoves().get(1).getAttempt());
		assertEquals(Arrays.asList(PURPLE, YELLOW, ORANGE, TEAL), history.getMoves().get(2).getAttempt());
		assertTrue(!gameWon);
	}
}
