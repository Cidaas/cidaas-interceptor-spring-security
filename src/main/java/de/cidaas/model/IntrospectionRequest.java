package de.cidaas.model;

import java.io.Serializable;

public class IntrospectionRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8285171726713034902L;
	
	private String token;
    private String token_type_hint;
    private String client_id;
    private String client_secret;

    public IntrospectionRequest(String token, String tokenType) {
    	this.token = token;
    	this.token_type_hint = tokenType;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_type_hint() {
        return token_type_hint;
    }

    public void setToken_type_hint(String token_type_hint) {
        this.token_type_hint = token_type_hint;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
}
