package com.jaa.games.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jeff
 */
public class AttemptEngine {
	
	private static final boolean NO_MATCH = false;
	private static final boolean FOUND_MATCH = true;
	private List<Integer> excludedSecretCodePositions = new ArrayList<Integer>();
	private int redCount = 0;
	private int whiteCount = 0;
	private CodePin secretCodePin;
	private boolean positionMatches;
	private boolean colorMatches;
	private boolean foundPin;
	private List<CodePin> attempt;

	public Response validate(List<CodePin> secretCode, CodePin... attemptArgs) {
		attempt = Arrays.asList(attemptArgs);
		
		if(secretCode.size() != attempt.size()) {
			throw new IllegalArgumentException("Attempt must have same number of pins as secret code.");
		}
		
		init();
		checkEachAttemptPin(secretCode);
		return new Response(redCount, whiteCount);
	}
	
	private void init() {
		excludedSecretCodePositions = new ArrayList<Integer>();
		redCount = 0;
		whiteCount = 0;
	}

	private void checkEachAttemptPin(List<CodePin> secretCode) {
		for (int attemptPosition = 0; attemptPosition < attempt.size(); attemptPosition++) {
			CodePin attemptPin = attempt.get(attemptPosition);
			checkEachSecretCodePin(secretCode, attemptPosition, attemptPin);
		}
	}
	
	private void checkEachSecretCodePin(List<CodePin> secretCode, int attemptPosition, CodePin attemptPin) {
		for (int secretCodePosition = 0; secretCodePosition < secretCode.size(); secretCodePosition++) {
			if(alreadyConsumedThisPosition(secretCodePosition)) {
				continue;
			}
			
			determineMatch(secretCode, attemptPosition, attemptPin, secretCodePosition);
			recordMatch(secretCodePosition);
			if(foundPin) {
				break;
			}
		}
		foundPin = false;
	}
	
	private boolean alreadyConsumedThisPosition(int secretCodePosition) {
		return excludedSecretCodePositions.contains(secretCodePosition);
	}
	
	private void determineMatch(List<CodePin> secretCode, int attemptPosition, CodePin attemptPin,
			int secretCodePosition) {
		secretCodePin = secretCode.get(secretCodePosition);
		positionMatches = attemptPosition == secretCodePosition;
		colorMatches = attemptPin.equals(secretCodePin);
	}
	
	private void recordMatch(int secretCodePosition) {
		if(positionMatches && colorMatches) {
			recordRedPin(secretCodePosition);
			foundPin = FOUND_MATCH;
		} else if(colorMatches) {
			// FIXME this peekAhead() seems odd. Perhaps redo algorithm to find reds, then whites
			if(attempt.get(secretCodePosition).equals(secretCodePin)) {
				recordRedPin(secretCodePosition);
			} else {
				whiteCount++;
				excludedSecretCodePositions.add(secretCodePosition);
			}
			foundPin = FOUND_MATCH;
		} else {
			foundPin = NO_MATCH;
		}
	}

	private void recordRedPin(int secretCodePosition) {
		redCount++;
		excludedSecretCodePositions.add(secretCodePosition);
	}
}
