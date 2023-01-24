package fr.kaibee.mars.rover.domain;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction leftDirection() {
        return switch (this) {
            case NORTH -> Direction.WEST;
            case EAST -> Direction.NORTH;
            case SOUTH -> Direction.EAST;
            case WEST -> Direction.SOUTH;
        };
    }

    public Direction rightDirection() {
        return switch (this) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
    }
}
