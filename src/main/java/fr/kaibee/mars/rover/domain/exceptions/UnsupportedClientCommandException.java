package fr.kaibee.mars.rover.domain.exceptions;

public class UnsupportedClientCommandException extends RuntimeException{
    public UnsupportedClientCommandException(String message) {
        super(message);
    }
}
