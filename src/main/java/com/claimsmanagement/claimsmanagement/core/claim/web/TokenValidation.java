package com.claimsmanagement.claimsmanagement.core.claim.web;

public class TokenValidation {
    @Override
	public String toString() {
		return "TokenValidation [tokenStatus=" + tokenStatus + "]";
	}

	private boolean tokenStatus;

	public boolean getToken() {
		return tokenStatus;
	}

	public void setToken(boolean token) {
		this.tokenStatus = token;
	}

	public TokenValidation(boolean token) {
		super();
		this.tokenStatus = token;
	}

	public TokenValidation() {
		super();
	}
    
   
}
