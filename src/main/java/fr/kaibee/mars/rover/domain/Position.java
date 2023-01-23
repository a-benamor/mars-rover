package fr.kaibee.mars.rover.domain;

import java.util.Objects;

public class Position {
    public static final int MARS_GRID_MAX_X = 20;
    public static final int MARS_GRID_MAX_Y = 10;
    private final int positionX;
    private final int positionY;

    public Position(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Position moveForwardPositionX() {
        if (isAtTheRightEdge()) {
            return new Position(-MARS_GRID_MAX_X, positionY);
        } else {
            return new Position(positionX + 1, positionY);
        }
    }

    public Position moveBackwardPositionX() {
        if (isAtTheLeftEdge()) {
            return new Position(MARS_GRID_MAX_X, positionY);
        } else {
            return new Position(positionX - 1, positionY);
        }
    }

    public Position moveForwardPositionY() {
        if (isAtTheTopEdge()) {
            return new Position(positionX, -MARS_GRID_MAX_Y);
        } else {
            return new Position(positionX, positionY + 1);
        }
    }

    public Position moveBackwardPositionY() {
        if (isAtTheBottomEdge()) {
            return new Position(positionX, MARS_GRID_MAX_Y);
        } else {
            return new Position(positionX, positionY - 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return positionX == position.positionX && positionY == position.positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }

    private boolean isAtTheRightEdge() {
        return MARS_GRID_MAX_X == positionX;
    }

    private boolean isAtTheLeftEdge() {
        return -MARS_GRID_MAX_X == positionX;
    }

    private boolean isAtTheTopEdge() {
        return MARS_GRID_MAX_Y == positionY;
    }

    private boolean isAtTheBottomEdge() {
        return -MARS_GRID_MAX_Y == positionY;
    }
}
