package fr.kaibee.mars.rover.domain;

import fr.kaibee.mars.rover.domain.exceptions.ObstacleEncounteredException;

import java.util.*;

public class Coordinates {
    private Position position;
    private Direction direction;
    private Set<Position> obstacles;

    private Coordinates(Builder builder) {
        position = builder.position;
        direction = builder.direction;
        obstacles = builder.obstacles;
    }

    public void performForwardMovement() {
        Position nextPosition =
        switch (direction) {
            case NORTH -> position.moveForwardPositionY();
            case EAST -> position.moveForwardPositionX();
            case SOUTH -> position.moveBackwardPositionY();
            case WEST -> position.moveBackwardPositionX();
        };

        position = checkAndGetPosition(nextPosition);
    }

    public void performBackwardMovement() {
        Position nextPosition =
        switch (direction) {
            case NORTH -> position.moveBackwardPositionY();
            case EAST -> position.moveBackwardPositionX();
            case SOUTH -> position.moveForwardPositionY();
            case WEST -> position.moveForwardPositionX();
        };

        position = checkAndGetPosition(nextPosition);
    }

    public void performTurnLeftMovement() {
        direction =
        switch (direction) {
            case NORTH -> Direction.WEST;
            case EAST -> Direction.NORTH;
            case SOUTH -> Direction.EAST;
            case WEST -> Direction.SOUTH;
        };
    }

    public void performTurnRightMovement() {
        direction =
        switch (direction) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Set<Position> getObstacles() {
        if (obstacles == null) {
            obstacles = new HashSet<>();
        }

        return obstacles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return position.equals(that.position) && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "position=" + position +
                ", direction=" + direction +
                '}';
    }

    public static final class Builder {
        private Position position;
        private Direction direction;
        private Set<Position> obstacles;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder position(Position val) {
            position = val;
            return this;
        }

        public Builder direction(Direction val) {
            direction = val;
            return this;
        }

        public Builder obstacles(Set<Position> val) {
            obstacles = val;
            return this;
        }

        public Coordinates build() {
            return new Coordinates(this);
        }
    }

    private Position checkAndGetPosition(Position position) {
        if (isPositionIsAnObstacle(position)) {
            throw new ObstacleEncounteredException("obstacle encountered at position" + position.toString());
        }

        return position;
    }

    private boolean isPositionIsAnObstacle(Position position) {
        return getObstacles().contains(position);
    }
}
