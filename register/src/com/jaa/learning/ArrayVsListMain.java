package com.jaa.learning;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Start the register
 */
public class ArrayVsListMain {
	
	public static void main(String[] args) {
		
		// quick notation to init arrays
		Fruit[] fruitArray = {
			new Fruit("apple", "red")
			, new Fruit("orange", "orange")
			, new Fruit("pasta", "not a fruit!")
		};
		
		// arrays can be fast but there are indexes that you have to maintain
		fruitArray[2] = new Fruit("banana", "yellow");
		
		ArrayList<Fruit> fruitList = new ArrayList<Fruit>();
		
		fruitList.add(new Fruit("apple", "red"));
		fruitList.add(new Fruit("orange", "orange"));
		fruitList.add(new Fruit("pasta", "not a fruit!"));
		
		// iterating over arrays
		for (Fruit fruit : fruitArray) {
			System.out.println(fruit);
		}
		
		// iterating over lists - manual way
		for (Iterator<Fruit> iterator = fruitList.iterator(); iterator.hasNext();) {
			Fruit fruit = (Fruit) iterator.next();
			System.out.println(fruit);
		}
		
		// iterating over lists - foreach
		for (Fruit fruit : fruitList) {
			System.out.println(fruit);
		}
		
	}
	
	public static class Fruit
	{
		public String name;
		public String color;
		
		public Fruit(String name, String color) {
			this.name = name;
			this.color = color;
		}
		
		public String toString() {
			return name + "'s are " + color;
		}
	}
}
