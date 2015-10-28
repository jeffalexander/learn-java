package com.jaa.learning.statements;


/**
 * Start the register
 */
public class StatementsMain {
	
	public static void main(String[] args) {
		
		// statements are the basics of programming
		
		// add 8 to 4 and store it in a variable called 'fun'
		int fun = 8 + 4;
		
		// define things
		// syntax: type name = initial value;
		int favoriteNumber = 7;
		double decimalPointsAreFun = 3.141519;
		boolean statementsAreFun = false;
		
		// add numbers
		int total = 1 + 7;
		
		// you can reference other variables in statements
		int totalAgain = 1 + favoriteNumber;
		
		// you can also update existing variables
		total = 5 + 10;
		
		// combine Strings
		String noun = "tree";
		String fullSentence = "The cat went up the " + noun + ".";
		
		// statements can also call methods
		String dogs = fullSentence.replaceAll("cat", "dog");
		
		// you can also use statements inside of if statements
		if(total > 20) {
			dogs = fullSentence.substring(2, 7);
		} else {
			dogs = fullSentence.replaceAll("cat", "mouse");
		}
		
		// to repeat statements, use a loop
		int count = 0;
		while (count < total) {
			System.out.println(dogs);
			count = count + 1;
		}
		
		int funner = 7;
	}
}
