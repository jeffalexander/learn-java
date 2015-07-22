package com.register.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Start the register
 */
public class Main {
	
	public static ThingWeCanBuy[] inventory = {
		new ThingWeCanBuy(4, "apple")
		, new ThingWeCanBuy(3, "orange")
		, new ThingWeCanBuy(5, "pasta")
	};
	
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Wes' Grocery!");
		System.out.println("Enter the item ID you want to buy:");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int requestedItemId = 0;
		int requestedItemId2 = 0;
		try {
            requestedItemId = Integer.parseInt(br.readLine());
            System.out.println("Adding item " + requestedItemId);
            requestedItemId2 = Integer.parseInt(br.readLine());
            System.out.println("Adding item " + requestedItemId2); 
        } catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
		
		System.out.println("Item " + requestedItemId + ": " 
				+ inventory[requestedItemId - 1]);
		System.out.println("Item " + requestedItemId2 + ": "
				+ inventory[requestedItemId2 - 1]);
		
		float price = inventory[requestedItemId - 1].price;
		float price2 = inventory[requestedItemId2 - 1].price;
		float total = price + price2;
		
		System.out.println("your total for today is $" + total);
	}
	
	public static class ThingWeCanBuy
	{
		public float price;
		public String objectName;
		
		public ThingWeCanBuy(float price, String objectName) 
		{
			this.price = price;
			this.objectName = objectName;
		}
		
		@Override
		public String toString() {
			return objectName + " for $" + price;
		}
	}
}
