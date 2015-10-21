package com.jaa.learning.polymorphism;

public class BrokenGun implements Fireable {

	@Override
	public void fire() {
		System.out.println("Click");
	}
}
