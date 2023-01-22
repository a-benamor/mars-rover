package fr.kaibee.mars.rover.domain;

import java.util.Objects;

public class Position {
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
        return new Position(positionX + 1, positionY);
    }

    public Position moveBackwardPositionX() {
        return new Position(positionX - 1, positionY);
    }

    public Position moveForwardPositionY() {
        return new Position(positionX, positionY + 1);
    }

    public Position moveBackwardPositionY() {
        return new Position(positionX, positionY - 1);
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
}
