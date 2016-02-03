package com.jaa.games.mastermind;

import java.util.ArrayList;
import java.util.List;

public class RoundHistory {
	
	private int maxMoves = 10;
	
	private List<Move> moves = new ArrayList<Move>();

	//TODO add constructor for free cucumber conversion
	
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
		if(codeFound(latestMove.getResponse())) {
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
	
	public boolean codeFound(Response response) {
		return response.getRedCount() == getCodeLength();
	}

	/**
	 * FIXME assume all attempts are proper length and match 
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
