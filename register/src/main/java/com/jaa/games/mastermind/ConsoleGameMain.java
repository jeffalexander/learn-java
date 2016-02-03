package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Feature Requests
 * - 4 or 5 pin games
 * - undo? aka 'are you sure?'
 * - auto-generate secret code for single player
 * 
 * - computer played games
 * 		- allow different algorithms to go head to head
 * 			- random start vs "find colors" start
 * 			- brute force?
 * 			- find color then position
 * 			- find position then color (is this possible?)
 * 			- shift left strategy
 * 		- different algorithm goals: fastest time vs less guesses
 * 		- super hard games of 10 or 1000 color codes of long lengths
 * 
 * @author jeff
 */
public class ConsoleGameMain {
	
	public static void main(String[] args) {
		MasterMindGame game = new MasterMindGame();
		game.newGame(GREEN, BLUE, BLACK, ORANGE);

		Response outcome = null;
//		do {
			outcome = game.submitAttempt(PURPLE, BLUE, BLACK, YELLOW);
//		} while (game.codeFound(outcome));
	}
}
