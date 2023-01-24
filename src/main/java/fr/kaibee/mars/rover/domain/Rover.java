package fr.kaibee.mars.rover.domain;

import java.util.List;

public class Rover {
    private Coordinates coordinates;
    private final Grid grid;

    public Rover(Coordinates coordinates, Grid grid) {
        this.coordinates = coordinates;
        this.grid = grid;
    }

    public void performMovements(List<RoverMovement> roverMovements) {
        roverMovements.forEach(this::performAMovement);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    private void performAMovement(RoverMovement roverMovement) {
        coordinates = switch (roverMovement) {
            case F -> {
                Coordinates nextCoordinate = coordinates.moveForward(grid);
                grid.checkAndGetPosition(nextCoordinate.position());
                yield nextCoordinate;
            }
            case B -> {
                Coordinates nextCoordinate = coordinates.moveBackward(grid);
                grid.checkAndGetPosition(nextCoordinate.position());
                yield nextCoordinate;
            }
            case L -> coordinates.turnLeft();
            case R -> coordinates.turnRight();
        };
    }
}
