package com.jaa.games.mastermind.player;

import java.util.ArrayList;
import java.util.List;

import com.jaa.games.mastermind.CodePin;
import com.jaa.games.mastermind.KeyPin;
import com.jaa.games.mastermind.MasterMindGame;
import com.jaa.games.mastermind.Move;

/**
 * 
 * 1. identify colors quickly
 * 2. shift right to find position
 * 3. 
 * 4. assume no duplicates
 * 
 * @author jeff
 */
public class CopyOfShiftStrategy {
	
	public boolean play(MasterMindGame game) {
		List<KeyPin> finalOutcome = new ArrayList<KeyPin>();
		while(!game.isGameOver()) {
			learn();
			finalOutcome = submitAttempt(game);
		}
		return game.codeFound(finalOutcome);
	}

	private List<KeyPin> submitAttempt(MasterMindGame game) {
		CodePin attemptArgs = createAttempt();
		return game.submitAttempt(attemptArgs);
	}
	
	/**
	 * recursive move comparison
	 */
	private void learn() {
		
		// foreach move
			// compare current move to remaining moves
			// exclude current move
			// recurse
	}
	
	private void compare(Move a, Move b) {
		// facts about attempts
//		numberColorsChanged();
//		was it a shift
		
		// facts about responses
//		keyPin count up, down, same
//		white key pin up, down same
//		red keyPin up, down same
	}
	
	private void numberColorsChanged() {
		// TODO Auto-generated method stub
		
	}
	
	private CodePin createAttempt() {
		/*
		goals:
			eliminate a color
			eliminate a color
		 */
		
//		random4
//		twoNewColors()
		
//		shift
//		swap
		
		//createRandomColorFirstAttempt();
		return null;
	}
	
//	markColorMaybe(Color)
//	markColorForSure
//	markPosition(Color, Position, value)
	
}
