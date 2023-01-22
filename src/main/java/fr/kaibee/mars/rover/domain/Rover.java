package fr.kaibee.mars.rover.domain;

import java.util.List;

public class Rover {
    private Coordinates coordinates;

    public Rover(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void performMovements(List<RoverMovement> roverMovements){
        roverMovements.stream().forEach(this::performAMovement);
    }

    private void performAMovement(RoverMovement roverMovement) {
        switch (roverMovement){
            case F: {
                moveForward();
                break;
            } case B: {
                moveBackward();
                break;
            } case L:{
                turnLeft();
                break;
            } case R: {
                turnRight();
                break;
            }
        }
    }

    private void moveForward() {
        this.coordinates.performForwardMovement();
    }

    private void moveBackward() {
        this.coordinates.performBackwardMovement();
    }
    private void turnLeft() {
        this.coordinates.performTurnLeftMovement();
    }
    private void turnRight() {
        this.coordinates.performTurnRightMovement();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
