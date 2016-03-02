package com.jaa.games.mastermind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	private static boolean cheated;

	public static void main(String[] args) throws IOException {
		MasterMindGame game = new MasterMindGame();
		game.newGame(generateSecretCode(4));
		printWelcome(game);
		Response outcome = null;
		
		do {
			System.out.print("Enter a guess: ");
			String attempt = getAttempt();
			boolean command = filterForCommand(attempt, game);
			if(command) {
				break;
			}
			outcome = processAttempt(game, outcome, attempt);
		} while (codeNotFound(game, outcome));
		
		if(game.youWonGame()) {
			System.out.println("You won in " + game.getHistory().getMoves().size() + " moves !");
			if(cheated) {
				System.out.println("But you cheated...");
			}
		}
	}

	private static void printWelcome(MasterMindGame game) {
		System.out.println("Welcome to Master Mind!");
		System.out.println("New game started with secret code with " + game.getSecretCodeLength() + " pins.");
		System.out.println("Format your guesses with a letter per pin: ");
		System.out.println(CodePin.getStubHint());
	}
	
	private static boolean filterForCommand(String attempt, MasterMindGame game) {
		if("Q".equalsIgnoreCase(attempt)) {
			System.out.println("Quitting");
			return true;
		} else if("7".equals(attempt)) {
			System.out.println(game.getSecretCode());
			cheated = true;
		}
		return false;
	}

	private static Response processAttempt(MasterMindGame game, Response outcome, String attempt) {
		try {
			outcome = game.submitAttempt(convert(attempt));
			System.out.println("Outcome: " + outcome);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid guess. Try again!");
		}
		return outcome;
	}

	private static boolean codeNotFound(MasterMindGame game, Response outcome) {
		return outcome == null || !game.codeFound(outcome);
	}

	private static String getAttempt() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String attempt = in.readLine();
		return attempt;
	}

	private static List<CodePin> convert(String attempt) {
		List<CodePin> convertedAttempt = new ArrayList<CodePin>();
		
		char[] chars = attempt.toCharArray();
		CodePin[] realPins = CodePin.values();
		for (int i = 0; i < chars.length; i++) {
			char shortPin = chars[i];
			for (int j = 0; j < realPins.length; j++) {
				if(realPins[j].name().toUpperCase().startsWith(("" + shortPin).toUpperCase())) {
					convertedAttempt.add(realPins[j]);
				}
			}
		}
		return convertedAttempt;
	}

	private static List<CodePin> generateSecretCode(int length) {
		List<CodePin> secretCode = new ArrayList<CodePin>();
		for (int i = 0; i < length; i++) {
			secretCode.add(CodePin.randomPin());
		}
		return secretCode;
	}
}
