package com.jaa.learning.polymorphism;

public class Gun implements Fireable {

	@Override
	public void fire() {
		System.out.println("Bang");
	}
}
