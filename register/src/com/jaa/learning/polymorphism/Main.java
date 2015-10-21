package com.jaa.learning.polymorphism;

public class Main {

	public static void main(String[] args) {
		Person player1 = new Person();
		player1.attack();

		Fireable weapon = new Gun();
		player1.setWeapon(weapon);
		player1.attack();
		
		weapon = new RepeaterGun();
		player1.setWeapon(weapon);
		player1.attack();
		
		weapon = new KarateGun();
		player1.setWeapon(weapon);
		player1.attack();

		weapon = new BrokenGun();
		player1.setWeapon(weapon);
		player1.attack();
		
		player1.setWeapon(null);
		player1.attack();
		
		weapon = new SuperGun();
		player1.setWeapon(weapon);
		player1.attack();
	}
}
