package fr.kaibee.mars.rover.domain.exceptions;

public class ObstacleEncounteredException extends RuntimeException {
    public ObstacleEncounteredException(String message) {
        super(message);
    }
}
