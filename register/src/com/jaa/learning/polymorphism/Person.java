package com.jaa.learning.polymorphism;

public class Person {
	
	private Fireable weapon;

	public void attack() {
		if(weapon == null) {
			System.out.println("Punch");
		} else {
			weapon.fire();
		}
	}
	
	public void setWeapon(Fireable weapon) {
		if(weapon == null) {
			System.out.println("Dropped my " + this.weapon.getClass().getSimpleName());
		} else {
			System.out.println("Picked up a " + weapon.getClass().getSimpleName());
		}
		this.weapon = weapon;
	}
}
