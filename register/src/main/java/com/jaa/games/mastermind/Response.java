package com.jaa.games.mastermind;

public class Response {
	
	private int redCount;
	private int whiteCount;
	
	public Response(int redCount, int whiteCount) {
		this.redCount = redCount;
		this.whiteCount = whiteCount;
	}

	public int getRedCount() {
		return redCount;
	}

	public void setRedCount(int redCount) {
		this.redCount = redCount;
	}

	public int getWhiteCount() {
		return whiteCount;
	}

	public void setWhiteCount(int whiteCount) {
		this.whiteCount = whiteCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + redCount;
		result = prime * result + whiteCount;
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
		Response other = (Response) obj;
		if (redCount != other.redCount)
			return false;
		if (whiteCount != other.whiteCount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Response [redCount=%s, whiteCount=%s]", redCount, whiteCount);
	}
}
