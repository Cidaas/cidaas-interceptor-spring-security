package de.cidaas.jwt.impl;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.cidaas.jwt.exceptions.JWTDecodeException;
import de.cidaas.jwt.interfaces.Claim;

/**
 * The {@link NullClaim} class is a Claim implementation that returns null when any of it's methods it's called.
 */
public class NullClaim implements Claim {
	
    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public Boolean asBoolean() {
        return null;
    }

    @Override
    public Integer asInt() {
        return null;
    }

    @Override
    public Long asLong() {
        return null;
    }

    @Override
    public Double asDouble() {
        return null;
    }

    @Override
    public String asString() {
        return null;
    }

    @Override
    public Date asDate() {
        return null;
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> T[] asArray(Class<T> tClazz) throws JWTDecodeException {
    	return (T[]) Array.newInstance(tClazz, 0);
    }

    @Override
    public <T> List<T> asList(Class<T> tClazz) throws JWTDecodeException {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> asMap() throws JWTDecodeException {
        return null;
    }

    @Override
    public <T> T as(Class<T> tClazz) throws JWTDecodeException {
        return null;
    }
}
