package com.jaa.learning.staticexample;

public class StaticVsInstanceMain {

	public static void main(String[] args) {
		Ball ball4 = new Ball(4);
		BallMath.displayVolume(ball4);
		Ball ball7 = new Ball(7);
		BallMath.displayVolume(ball7);

		BallMath.displayVolume(ball4);

		Ball ball12 = new Ball(12);
		BallMath.displayVolume(ball12);
		
		BallMath.displayVolume(ball4);
		BallMath.displayVolume(ball7);
		
		
		ball4.bounce(2);
		ball7.bounce(8);
	}
}

