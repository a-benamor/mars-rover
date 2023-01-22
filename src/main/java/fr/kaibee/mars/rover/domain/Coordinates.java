package fr.kaibee.mars.rover.domain;

import java.util.Objects;

public class Coordinates {
    private Position position;
    private Direction direction;

    private Coordinates(Builder builder) {
        position = builder.position;
        direction = builder.direction;
    }

    public void performForwardMovement(){
        switch (direction){
            case NORTH:{
                position = position.moveForwardPositionY();
                break;
            } case EAST:{
                position = position.moveForwardPositionX();
                break;
            } case SOUTH: {
                position = position.moveBackwardPositionY();
                break;
            } case WEST: {
                position = position.moveBackwardPositionX();
                break;
            }
        }
    }

    public void performBackwardMovement() {
        switch (direction){
            case NORTH:{
                position = position.moveBackwardPositionY();
                break;
            } case EAST:{
                position = position.moveBackwardPositionX();
                break;
            } case SOUTH: {
                position = position.moveForwardPositionY();
                break;
            } case WEST: {
                position = position.moveForwardPositionX();
                break;
            }
        }
    }

    public void performTurnLeftMovement() {
        switch (direction){
            case NORTH: {
                direction = Direction.WEST;
                break;
            } case EAST: {
                direction = Direction.NORTH;
                break;
            }case SOUTH: {
                direction = Direction.EAST;
                break;
            }case WEST: {
                direction = Direction.SOUTH;
                break;
            }
        }
    }

    public void performTurnRightMovement() {
        switch (direction){
            case NORTH: {
                direction = Direction.EAST;
                break;
            } case EAST: {
                direction = Direction.SOUTH;
                break;
            }case SOUTH: {
                direction = Direction.WEST;
                break;
            }case WEST: {
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

    public static final class Builder {
        private Position position;
        private Direction direction;

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

        public Coordinates build() {
            return new Coordinates(this);
        }
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
}
