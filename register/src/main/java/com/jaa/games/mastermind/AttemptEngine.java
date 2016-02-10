package com.jaa.games.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jeff
 */
public class AttemptEngine {

	public Response validate(List<CodePin> secretCode, CodePin... attemptArgs) {
		List<CodePin> attempt = Arrays.asList(attemptArgs);
		
		if(secretCode.size() != attempt.size()) {
			throw new IllegalArgumentException("Attempt must have same number of pins as secret code.");
		}
		
		List<Integer> excludedSecretCodePositions = new ArrayList<Integer>();
		int redCount = 0;
		int whiteCount = 0;
		
		for (int attemptPosition = 0; attemptPosition < attempt.size(); attemptPosition++) {
			CodePin attemptPin = attempt.get(attemptPosition);

			for (int secretCodePosition = 0; secretCodePosition < secretCode.size(); secretCodePosition++) {
				if(excludedSecretCodePositions.contains(secretCodePosition)) {
					continue;
				}
				
				CodePin secretCodePin = secretCode.get(secretCodePosition);
				
				boolean positionMatches = attemptPosition == secretCodePosition;
				boolean colorMatches = attemptPin.equals(secretCodePin);
				
				if(positionMatches && colorMatches) {
					redCount++;
					excludedSecretCodePositions.add(secretCodePosition);
					break;
				} else if(colorMatches) {
					// FIXME this peekAhead() seems odd. Perhaps redo algorithm to find reds, then whites
					if(attempt.get(secretCodePosition).equals(secretCodePin)) {
						redCount++;
						excludedSecretCodePositions.add(secretCodePosition);
					} else {
						whiteCount++;
						excludedSecretCodePositions.add(secretCodePosition);
					}
					break;
				}
			}
		}
		
		return new Response(redCount, whiteCount);
	}
}
