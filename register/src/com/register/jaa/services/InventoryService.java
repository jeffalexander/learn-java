package com.register.jaa.services;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.register.jaa.domain.Item;

public class InventoryService {
	
	private static final String INVENTORY_FILE_NAME = "inventory.txt";
	
	private Map<Integer, Item> inventoryByItemId = new HashMap<Integer, Item>();
	
	private DisplayService displayService;
	
	public InventoryService(DisplayService displayService) {
		this.displayService = displayService;
	}
	
	public void loadInventor() {
		displayService.info("Loading inventory: " + INVENTORY_FILE_NAME);
		InputStream stream = InventoryService.class.getResourceAsStream("inventory.txt");
		Scanner scanner = new Scanner(stream);
		
		while(scanner.hasNextLine()) {
			
			String nextLine = scanner.nextLine();
		}
		
	}

}
