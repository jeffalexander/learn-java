package com.jaa.learning.staticexample;
public class BallMath {
	public static void displayVolume(Ball ball) {
		double radius = 4/3 * Math.PI * Math.pow(ball.getRadius(), 3);
		System.out.println("The volume of a ball with radius " + ball.getRadius() + " is " + radius);
	}
}