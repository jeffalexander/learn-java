package com.jaa.games.mastermind;

import java.util.List;

public enum KeyPin {
	NONE, WHITE, RED;
	
	public static int count(KeyPin color, List<KeyPin> keys) {
		int whiteKeyPinCount = 0;
		for (KeyPin key : keys) {
			if(color.equals(key)) {
				whiteKeyPinCount++;
			}
		}
		return whiteKeyPinCount;
	}
}
