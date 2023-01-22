package fr.kaibee.mars.rover.domain;

public enum MovementType {
    F("FORWARD"), B("BACKWARD");

    private final String label;

    MovementType(String label) {
        this.label = label;
    }



}
