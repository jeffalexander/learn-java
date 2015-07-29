package com.register.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Start the register
 */
public class Main {
	
	public static ThingWeCanBuy[] inventory = {
		new ThingWeCanBuy(4, "apple")
		, new ThingWeCanBuy(3, "orange")
		, new ThingWeCanBuy(5, "pasta")
	};
	
	public static List<ThingWeCanBuy> shoppingCart = new ArrayList<ThingWeCanBuy>();
	
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Wes' Grocery!");
		System.out.println("Enter the item ID you want to buy:");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int requestedItemId = 0;
		int requestedItemId2 = 0;
		try {
			
			
			// TODO add a loop near here
            String requestedItem = br.readLine();
            while (!requestedItem.equals("done")) {
	            requestedItemId = Integer.parseInt(br.readLine());
	            System.out.println("Adding item " + requestedItemId);
	            shoppingCart.add(getItem(requestedItemId));
	            requestedItem = br.readLine();
            }
            
            requestedItemId2 = Integer.parseInt(br.readLine());
            System.out.println("Adding item " + requestedItemId2);
            
            
            
            
        } catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
		
		System.out.println("Item " + requestedItemId + ": " 
				+ getItem(requestedItemId));
		System.out.println("Item " + requestedItemId2 + ": "
				+ getItem(requestedItemId2));
		
		float price = getItem(requestedItemId).price;
		float price2 = getItem(requestedItemId2).price;
		float total = price + price2;
		
		System.out.println("your total for today is $" + total);
	}
	
	// attributes returnType methodName(paramType paramName)
	private static ThingWeCanBuy getItem(int requestedItemId) {
		return inventory[requestedItemId -1];
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
