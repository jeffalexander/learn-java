package com.jaa.games.mastermind;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum CodePin {
	GREEN, BLACK, PURPLE, YELLOW, ORANGE, TEAL;
	
	private static Random random = new Random();
	private static CodePin[] values = values();
	private static Map<String, CodePin> stubs = new HashMap<String, CodePin>();
	private static String stubHint;
	
	static {
		for (CodePin codePin : values) {
			stubs.put(codePin.name().substring(0, 1), codePin);
		}
	}
	
	public static CodePin randomPin() {
		return values[random.nextInt(values.length)];
	}
	
	public static CodePin fromStub(String stub) {
		return stubs.get(stub);
	}

	public static String getStubHint() {
		if(stubHint == null) {
			stubHint = "";
			for (String stub : stubs.keySet()) {
				stubHint += stubs.get(stub) + "(" + stub + "), ";
			}
		}
		
		return stubHint;
	}
}
