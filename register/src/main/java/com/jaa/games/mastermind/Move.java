package com.jaa.games.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Move {
	
	private List<CodePin> attempt = new ArrayList<CodePin>();
	private List<KeyPin> response = new ArrayList<KeyPin>();
	// TODO add time of guess
	
	public List<CodePin> getAttempt() {
		return attempt;
	}

	public void setAttempt(List<CodePin> attempt) {
		this.attempt = attempt;
	}

	public void setAttempt(CodePin... attempt) {
		this.attempt = Arrays.asList(attempt);		
	}

	public List<KeyPin> getResponse() {
		return response;
	}

	public void setResponse(List<KeyPin> response) {
		this.response = response;
	}

	public void setResponse(KeyPin... response) {
		this.response = Arrays.asList(response);
	}
}
