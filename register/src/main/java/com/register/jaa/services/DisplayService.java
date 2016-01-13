package com.register.jaa.services;

public class DisplayService {
	
	public class LogLevel {
		public static final int ALL = 5;
	}
	
	private int logLevel = 3;

	public DisplayService(int logLevel) {
		this.logLevel = logLevel;
	}
	
	public void info(String message) {
		if(logLevel > 2) {
			System.out.println(message);
		}
	}

}
