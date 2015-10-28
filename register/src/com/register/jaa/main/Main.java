package com.register.jaa.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.register.jaa.services.DisplayService;
import com.register.jaa.services.InventoryService;

/**
 * Start the register
 */
public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to My Grocery!");
		
		DisplayService displayService = new DisplayService(DisplayService.LogLevel.ALL);
		
		InventoryService inventoryService = new InventoryService(displayService);
		inventoryService.loadInventor();
		
		System.out.println("Enter the item ID you want to buy:");

		
	}
}
