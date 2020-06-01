package de.cidaas.jwt.impl;

public interface PublicClaims {
	 //Header
    static String ALGORITHM = "alg";
    static String CONTENT_TYPE = "cty";
    static String TYPE = "typ";
    static String KEY_ID = "kid";

    //Payload
    static String ISSUER = "iss";
    static String SUBJECT = "sub";
    static String EXPIRES_AT = "exp";
    static String NOT_BEFORE = "nbf";
    static String ISSUED_AT = "iat";
    static String JWT_ID = "jti";
    static String AUDIENCE = "aud";
}
