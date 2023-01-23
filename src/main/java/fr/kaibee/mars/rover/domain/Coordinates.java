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
        Position nextPosition = position;
        switch (direction) {
            case NORTH: {
                nextPosition = position.moveForwardPositionY();
                break;
            }
            case EAST: {
                nextPosition = position.moveForwardPositionX();
                break;
            }
            case SOUTH: {
                nextPosition = position.moveBackwardPositionY();
                break;
            }
            case WEST: {
                nextPosition = position.moveBackwardPositionX();
                break;
            }
        }

        position = checkAndGetPosition(nextPosition);
    }

    public void performBackwardMovement() {
        Position nextPosition = position;
        switch (direction) {
            case NORTH: {
                nextPosition = position.moveBackwardPositionY();
                break;
            }
            case EAST: {
                nextPosition = position.moveBackwardPositionX();
                break;
            }
            case SOUTH: {
                nextPosition = position.moveForwardPositionY();
                break;
            }
            case WEST: {
                nextPosition = position.moveForwardPositionX();
                break;
            }
        }

        position = checkAndGetPosition(nextPosition);
    }

    public void performTurnLeftMovement() {
        switch (direction) {
            case NORTH: {
                direction = Direction.WEST;
                break;
            }
            case EAST: {
                direction = Direction.NORTH;
                break;
            }
            case SOUTH: {
                direction = Direction.EAST;
                break;
            }
            case WEST: {
                direction = Direction.SOUTH;
                break;
            }
        }
    }

    public void performTurnRightMovement() {
        switch (direction) {
            case NORTH: {
                direction = Direction.EAST;
                break;
            }
            case EAST: {
                direction = Direction.SOUTH;
                break;
            }
            case SOUTH: {
                direction = Direction.WEST;
                break;
            }
            case WEST: {
                direction = Direction.NORTH;
                break;
            }
        }
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
