package com.jaa.games.mastermind;

import static org.junit.Assert.*;

import org.junit.Test;

public class CodePinTest {

	@Test
	public void stub_returns_proper_pin() {
		assertEquals(CodePin.BLACK, CodePin.fromStub("B"));
		assertEquals(CodePin.TEAL, CodePin.fromStub("T"));
	}

}
