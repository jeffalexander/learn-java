package com.jaa.learning.polymorphism;

public class RepeaterGun extends Gun {

	@Override
	public void fire() {
		super.fire();
		super.fire();
		super.fire();
	}
}
