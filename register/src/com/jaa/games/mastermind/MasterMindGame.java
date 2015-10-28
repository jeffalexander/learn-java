package com.jaa.games.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MasterMindGame {

	private List<CodePin> secretCode;
	
	public void newGame(CodePin... secretCode) {
		this.secretCode = Arrays.asList(secretCode);
	}
	
	public void newGame(List<CodePin> secretCode) {
		this.secretCode = secretCode;
	}
	
	/**
	 * TODO rather than "find reds, then find whites"
	 * try for each pin, determine key color
	 */
	public List<KeyPin> guess(CodePin... guessArgs) {
		List<CodePin> guess = Arrays.asList(guessArgs);
		
		if(secretCode.size() != guess.size()) {
			throw new IllegalArgumentException("Guess must have same number of pins as secret code.");
		}
		
		// red = correct position and color
		// white = correct color
		List<KeyPin> outcome = new ArrayList<KeyPin>();
		List<Integer> positions = new ArrayList<Integer>();
		List<Integer> redPositions = new ArrayList<Integer>();
		List<Integer> nonRedPositions = new ArrayList<Integer>();
		for (int i = 0; i < secretCode.size(); i++) {
			positions.add(i);
			nonRedPositions.add(i);
		}
		
		// check position for color match
		// remove position if match found
		for (Integer position : positions) {
			if(secretCode.get(position).equals(guess.get(position))) {
				outcome.add(KeyPin.RED);
				redPositions.add(position);
				nonRedPositions.remove(position);
			}
		}
		
		List<Integer> whitePositions = new ArrayList<Integer>();
		
		// for remaining positions, look for matching color, removing match
		for (Integer guessPosition : nonRedPositions) {
			for (Integer secretCodePosition : nonRedPositions) {
				if(secretCode.get(secretCodePosition).equals(guess.get(guessPosition))) {
					// exclude red positions
					if(redPositions.contains(secretCodePosition)) {
						continue;
					}
					// exclude white positions
					if(whitePositions.contains(secretCodePosition)) {
						continue;
					}
					outcome.add(KeyPin.WHITE);
					whitePositions.add(secretCodePosition);
				}
			}
		}
		
		// TODO randomize outcome;
//		outcome.sort(); -> using custom sorter
		
		return outcome;
	}

	public boolean codeFound(List<KeyPin> outcome) {
		int redCount = KeyPin.count(KeyPin.RED, outcome);
		return redCount == secretCode.size();
	}
}
