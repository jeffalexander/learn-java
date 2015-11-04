package com.jaa.games.mastermind;

import java.util.ArrayList;
import java.util.List;

public class RoundHistory {
	
	private int maxMoves = 10;
	
	private List<Move> moves = new ArrayList<Move>();

	public List<Move> getMoves() {
		return moves;
	}

	public void addMove(Move move) {
		moves.add(move);
	}

	public int getMaxMoves() {
		return maxMoves;
	}

	public void setMaxMoves(int maxMoves) {
		this.maxMoves = maxMoves;
	}
	
	public boolean isComplete() {
		Move latestMove = getLatestMove();
		if(latestMove == null) {
			return false;
		}
		List<KeyPin> response = latestMove.getResponse();
		if(codeFound(response)) {
			return true;
		} else {
			return isOutOfMoves();
		}
	}
	
	private boolean isOutOfMoves() {
		return getMaxMoves() == getMoves().size();
	}
	
	private Move getLatestMove() {
		int moveCount = getMoves().size();
		if(moveCount < 1) {
			return null;
		}
		return getMoves().get(moveCount - 1);
	}
	
	public boolean codeFound(List<KeyPin> outcome) {
		int redCount = KeyPin.count(KeyPin.RED, outcome);
		return redCount == getCodeLength();
	}

	/**
	 * assume all attempts are proper length and match 
	 * the secret code length
	 */
	private int getCodeLength() {
		Move move = getMoves().get(0);
		if(move == null) {
			return -1;
		}
		return move.getAttempt().size();
	}
}
