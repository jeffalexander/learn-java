package com.jaa.games.mastermind.player;

public class Positions {
	
	private enum PositionKnowledge {
		UNKNOWN, MAYBE, NOT_IT, FOR_SURE;
	}
	
	private PositionKnowledge[][] positions;
	
	public Positions(int codeSize, int colorCount) {
		positions = new PositionKnowledge[codeSize][colorCount];
	}
	
	public void learn(int position, int color, PositionKnowledge info) {
		positions[position][color] = info;
	}
	
	public boolean goodGuess(int guess) {
		// TODO implement
		return false;
	}
}
