package com.jaa.games.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MasterMindGame {

	private List<CodePin> secretCode;
	private RoundHistory roundHistory;
	
	public void newGame(CodePin... secretCodeArgs) {
		secretCode = Arrays.asList(secretCodeArgs);
		roundHistory = new RoundHistory();
		roundHistory.setMaxMoves(10);
	}
	
	public List<KeyPin> guess(CodePin... guessArgs) {
		List<CodePin> guess = Arrays.asList(guessArgs);
		
		if(secretCode.size() != guess.size()) {
			throw new IllegalArgumentException("Guess must have same number of pins as secret code.");
		}
		
		List<KeyPin> outcome = new ArrayList<KeyPin>();
		List<Integer> excludedSecretCodePositions = new ArrayList<Integer>();
		
		for (int guessPosition = 0; guessPosition < guess.size(); guessPosition++) {
			CodePin guessPin = guess.get(guessPosition);

			for (int secretCodePosition = 0; secretCodePosition < secretCode.size(); secretCodePosition++) {
				if(excludedSecretCodePositions.contains(secretCodePosition)) {
					continue;
				}
				
				CodePin secretCodePin = secretCode.get(secretCodePosition);
				
				boolean positionMatches = guessPosition == secretCodePosition;
				boolean colorMatches = guessPin.equals(secretCodePin);
				
				if(positionMatches && colorMatches) {
					outcome.add(KeyPin.RED);
					excludedSecretCodePositions.add(secretCodePosition);
					break;
				} else if(colorMatches) {
					outcome.add(KeyPin.WHITE);
					excludedSecretCodePositions.add(secretCodePosition);
					break;
				}
			}
		}
		
		Collections.shuffle(outcome);
		
		Move move = new Move();
		move.setAttempt(guess);
		move.setResponse(outcome);
		roundHistory.addMove(move);
		return outcome;
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
