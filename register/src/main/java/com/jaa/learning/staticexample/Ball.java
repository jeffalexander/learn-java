package com.jaa.learning.staticexample;
public class Ball {
	public int radius;
	public static int count = 0;
	
	public Ball(int radius) {
		this.radius = radius;
		count++;
		System.out.println("Created ball #" + count);
	}
	public int getRadius() {
		return radius;
	}
	
	public void bounce(int count) {
		for (int i = 0; i < count; i++) {
			System.out.println("Bounce ball with radius " + getRadius());
		}
	}
}
