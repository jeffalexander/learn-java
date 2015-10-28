package com.jaa.games.mastermind;

import static com.jaa.games.mastermind.CodePin.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Feature Requests
 * - 4 or 5 pin games
 * - undo?
 * - auto-generate secret code for single player
 * 
 * - computer played games
 * 		- allow different algorithms to go head to head
 * 			- random start vs "find colors" start
 * 			- brute force?
 * 			- find color then position
 * 			- find position then color (is this possible?)
 * 		- different algorithm goals: fastest time vs less guesses
 * 		- super hard games of 10 or 1000 color codes of long lengths
 * 
 * @author jeff
 */
public class ConsoleGameMain {
	
	public static void main(String[] args) {
		MasterMindGame game = new MasterMindGame();
		game.newGame(GREEN, BLUE, WHITE, ORANGE);

		List<KeyPin> outcome = null;
//		do {
			outcome = game.guess(PURPLE, BLUE, WHITE, YELLOW);
//		} while (game.codeFound(outcome));
	}
}
