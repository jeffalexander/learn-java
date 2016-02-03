package com.jaa.games.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Move {
	
	private List<CodePin> attempt = new ArrayList<CodePin>();
	private Response response;
	// TODO add time of guess
	
	public List<CodePin> getAttempt() {
		return attempt;
	}

	public void setAttempt(List<CodePin> attempt) {
		this.attempt = attempt;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public void setResponse(int redCount, int whiteCount) {
		this.response = new Response(redCount, whiteCount);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attempt == null) ? 0 : attempt.hashCode());
		result = prime * result + ((response == null) ? 0 : response.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (attempt == null) {
			if (other.attempt != null)
				return false;
		} else if (!attempt.equals(other.attempt))
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Move [attempt=%s, response=%s]", attempt, response);
	}
}
