package fr.kaibee.mars.rover.domain.exceptions;

public class WrongClientCommandException extends RuntimeException{
    public WrongClientCommandException(String message) {
        super(message);
    }
}
