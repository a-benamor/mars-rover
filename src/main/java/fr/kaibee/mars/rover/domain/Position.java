package fr.kaibee.mars.rover.domain;

public record Position(int positionX, int positionY) {

    public Position moveForwardPositionX(Grid grid) {
        int size = grid.size();
        int minX = grid.minX();
        int newX = (positionX + 1 + size - minX) % size + minX;
        return new Position(newX, positionY);
    }

    public Position moveBackwardPositionX(Grid grid) {
        int size = grid.size();
        int minX = grid.minX();
        int newX = (positionX - 1 + size - minX) % size + minX;
        return new Position(newX, positionY);
    }

    public Position moveForwardPositionY(Grid grid) {
        int size = grid.size();
        int minY = grid.minY();
        int newY = (positionY + 1 + size - minY) % size + minY;
        return new Position(positionX, newY);
    }

    public Position moveBackwardPositionY(Grid grid) {
        int size = grid.size();
        int minY = grid.minY();
        int newY = (positionY - 1 - minY + size) % size + minY;
        return new Position(positionX, newY);
    }

}
