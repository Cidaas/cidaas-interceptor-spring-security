package de.cidaas.model;

public class IntrospectionResponse {
	private boolean active = false;
	private String token_type;
	private String aud;
	private long exp;
	private long iat;
	private String iss;
	private String jti;
	private String sub;
	
	private String[] roles;
	private String[] scopes;
	private String scope;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public long getIat() {
		return iat;
	}

	public void setIat(long iat) {
		this.iat = iat;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getAud() {
		return aud;
	}

	public void setAud(String aud) {
		this.aud = aud;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String[] getScopes() {
		return scopes;
	}

	public void setScopes(String[] scopes) {
		this.scopes = scopes;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}
