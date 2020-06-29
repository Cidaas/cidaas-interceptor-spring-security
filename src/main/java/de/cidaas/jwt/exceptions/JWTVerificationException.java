package de.cidaas.jwt.exceptions;

public class JWTVerificationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7082415765974002717L;

	public JWTVerificationException(String message) {
        this(message, null);
    }

    public JWTVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
