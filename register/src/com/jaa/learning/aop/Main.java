package com.jaa.learning.aop;

public class Main {
	public static void main(String[] args) {
		Awesomeness service = new AwesomeService();
		System.out.println("passing 7");
		String result = service.doAwesome(7);
		System.out.println("Result: " + result);
		
		Awesomeness serviceProxy = (Awesomeness) HavocAopFactory.instanceOf(AwesomeService.class);
		System.out.println("passing 7");
		String proxyResult = serviceProxy.doAwesome(7);
		System.out.println("Result: " + proxyResult);
	}
}
