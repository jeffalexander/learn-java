package com.jaa.games.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jeff
 */
public class AttemptEngine {

	public List<KeyPin> validate(List<CodePin> secretCode, CodePin... attemptArgs) {
		List<CodePin> attempt = Arrays.asList(attemptArgs);
		
		if(secretCode.size() != attempt.size()) {
			throw new IllegalArgumentException("Attempt must have same number of pins as secret code.");
		}
		
		// todo ja; this concept of an outcome/response should not be a list
		// it should be a Response object with a count of whites and red key pins.
		List<KeyPin> outcome = new ArrayList<KeyPin>();
		List<Integer> excludedSecretCodePositions = new ArrayList<Integer>();
		
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
		return outcome;
	}
}
