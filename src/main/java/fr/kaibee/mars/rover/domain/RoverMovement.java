package fr.kaibee.mars.rover.domain;

public enum RoverMovement {
    F("FORWARD"), B("BACKWARD"), L("TURN_LEFT"), R("TURN_RIGHT");

    private final String label;

    RoverMovement(String label) {
        this.label = label;
    }





}
