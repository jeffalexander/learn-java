package com.jaa.games.mastermind;

import java.util.Arrays;
import java.util.List;

public class MasterMindGame {

	private List<CodePin> secretCode;
	private RoundHistory roundHistory;
	private AttemptEngine attemptEngine = new AttemptEngine();

	public void newGame(List<CodePin> secretCode) {
		this.secretCode = secretCode;
		roundHistory = new RoundHistory();
		roundHistory.setMaxMoves(10);
	}
	
	public void newGame(CodePin... secretCodeArgs) {
		newGame(Arrays.asList(secretCodeArgs));
	}

	public Response submitAttempt(List<CodePin> attempt) {
		CodePin[] array = attempt.toArray(new CodePin[attempt.size()]);
		return submitAttempt(array);
	}
	
	public Response submitAttempt(CodePin... attemptArgs) {
		Response response = attemptEngine.validate(secretCode, attemptArgs);
		Move move = new Move();
		move.setAttempt(Arrays.asList(attemptArgs));
		move.setResponse(response);
		roundHistory.addMove(move);
		return response;
	}
	
	public boolean codeFound(Response response) {
		return roundHistory.codeFound(response);
	}
	
	public boolean youWonGame() {
		return roundHistory.codeFound(roundHistory.getLatestMove().getResponse());
	}
	
	public boolean isGameOver() {
		return roundHistory.isComplete();
	}
	
	public int getSecretCodeLength(){
		return secretCode.size();
	}

	public RoundHistory getHistory() {
		return roundHistory;
	}

	public List<CodePin> getSecretCode() {
		return secretCode;
	}
}
