package com.register.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Start the register
 */
public class Main {
	
	public static Object[] inventory = {
		new ThingWeCanBuy(4, "apple")
		, new ThingWeCanBuy(3, "orange")
		, new ThingWeCanBuy(5, "pasta")
	};
	
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Wes's Grocery!");
		System.out.println("Enter the item ID you want to buy:");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int requestedItemId = 0;
		try {
            requestedItemId = Integer.parseInt(br.readLine());
        } catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
		
		System.out.println(inventory[requestedItemId - 1]);
        
        System.out.print("Adding item " + requestedItemId);
        System.out.println();
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
			return price + " " + objectName;
		}
	}
}
