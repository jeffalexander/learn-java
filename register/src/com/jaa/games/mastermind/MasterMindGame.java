package com.jaa.games.mastermind;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MasterMindGame {

	private List<CodePin> secretCode;
	private RoundHistory roundHistory;
	private AttemptEngine attemptEngine = new AttemptEngine();
	
	public void newGame(CodePin... secretCodeArgs) {
		secretCode = Arrays.asList(secretCodeArgs);
		roundHistory = new RoundHistory();
		roundHistory.setMaxMoves(10);
	}
	
	public List<KeyPin> submitAttempt(CodePin... attemptArgs) {
		List<KeyPin> response = attemptEngine.validate(secretCode, attemptArgs);
		Collections.shuffle(response);
		Move move = new Move();
		move.setAttempt(attemptArgs);
		move.setResponse(response);
		roundHistory.addMove(move);
		return response;
	}
	
	// TODO this is part of RoundHistory now... clean up
	public boolean codeFound(List<KeyPin> outcome) {
		int redCount = KeyPin.count(KeyPin.RED, outcome);
		return redCount == secretCode.size();
	}
	
	public boolean isGameOver() {
		return roundHistory.isComplete();
	}
}
