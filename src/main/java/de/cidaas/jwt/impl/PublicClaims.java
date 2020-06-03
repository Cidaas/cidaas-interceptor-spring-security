/**
 * 
 */
package de.cidaas.jwt.impl;

/**
 * @author Widas Sumit
 *
 */
public class PublicClaims {
	

	 //Header
   public static final String ALGORITHM = "alg";
   public static final String CONTENT_TYPE = "cty";
   public static final String TYPE = "typ";
   public static final String KEY_ID = "kid";

   //Payload
   public static final String ISSUER = "iss";
   public static final String SUBJECT = "sub";
   public static final String EXPIRES_AT = "exp";
   public static final String NOT_BEFORE = "nbf";
   public static final String ISSUED_AT = "iat";
   public static final String JWT_ID = "jti";
   public static final String AUDIENCE = "aud";


}
