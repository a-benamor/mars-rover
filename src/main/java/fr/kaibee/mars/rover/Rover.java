package fr.kaibee.mars.rover;

public class Rover {
    private Position position;
    private Direction direction;

    public Rover(Position startPosition, Direction startDirection) {
        this.position = startPosition;
        this.direction = startDirection;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }
}
