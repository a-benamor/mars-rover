package fr.kaibee.mars.rover.domain;

public record Coordinates(Position position, Direction direction) {

    public Coordinates moveForward(Grid grid) {
        Position nextPosition =
                switch (direction) {
                    case NORTH -> position.moveForwardPositionY(grid);
                    case EAST -> position.moveForwardPositionX(grid);
                    case SOUTH -> position.moveBackwardPositionY(grid);
                    case WEST -> position.moveBackwardPositionX(grid);
                };
        return new Coordinates(nextPosition, direction);
    }

    public Coordinates moveBackward(Grid grid) {
        Position nextPosition =
                switch (direction) {
                    case NORTH -> position.moveBackwardPositionY(grid);
                    case EAST -> position.moveBackwardPositionX(grid);
                    case SOUTH -> position.moveForwardPositionY(grid);
                    case WEST -> position.moveForwardPositionX(grid);
                };
        return new Coordinates(nextPosition, direction);
    }

    public Coordinates turnLeft() {
        Direction nextDirection = direction.leftDirection();
        return new Coordinates(position, nextDirection);
    }

    public Coordinates turnRight() {
        Direction nextDirection = direction.rightDirection();
        return new Coordinates(position, nextDirection);
    }
}
