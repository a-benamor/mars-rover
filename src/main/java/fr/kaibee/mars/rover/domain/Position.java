package fr.kaibee.mars.rover.domain;

public record Position(int positionX, int positionY) {
    public static final int MARS_GRID_MAX_X = 20;
    public static final int MARS_GRID_MAX_Y = 10;

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
