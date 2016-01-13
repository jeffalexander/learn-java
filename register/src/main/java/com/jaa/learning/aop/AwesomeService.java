package com.jaa.learning.aop;

public class AwesomeService implements Awesomeness {
	
	@Havoc
	public String blah() {
		return null;
	}
	
	@Havoc(severity=Severity.HIGH)
	public String doAwesome(int magicNumber) {
		String outcome = String.valueOf(magicNumber + 7);
		System.out.println(outcome);
		return outcome;
	}
}
