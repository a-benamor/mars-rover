package fr.kaibee.mars.rover.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionTest {

    public static final int SIZE = 4;
    public static final Grid GRID = Grid.emptyGrid(SIZE);
    public static final int MIN_X = 1;
    public static final int MIN_Y = 1;
    public static final int MAX_X = SIZE;
    public static final int MAX_Y = SIZE;

    @Test
    void should_move_forward_positionX() {
        int initialPositionY = 2;
        int initialPositionX = MIN_X;
        Position initialPosition = new Position(initialPositionX, initialPositionY);

        Position newPosition = initialPosition.moveForwardPositionX(GRID);

        assertPositionIsEqualTo(newPosition, initialPositionX + 1, initialPositionY);
    }

    @Test
    void should_move_backward_positionX() {
        int initialPositionY = 2;
        int initialPositionX = 2;
        Position initialPosition = new Position(initialPositionX, initialPositionY);

        Position newPosition = initialPosition.moveBackwardPositionX(GRID);

        assertPositionIsEqualTo(newPosition, initialPositionX - 1, initialPositionY);
    }

    @Test
    void should_move_forward_positionY() {
        int initialPositionY = MIN_Y;
        int initialPositionX = MIN_X;
        Position initialPosition = new Position(initialPositionX, initialPositionY);

        Position newPosition = initialPosition.moveForwardPositionY(GRID);

        assertPositionIsEqualTo(newPosition, initialPositionX, initialPositionY + 1);
    }

    @Test
    void should_move_backward_positionY() {
        int initialPositionY = 2;
        int initialPositionX = MIN_X;
        Position initialPosition = new Position(initialPositionX, initialPositionY);

        Position newPosition = initialPosition.moveBackwardPositionY(GRID);

        assertPositionIsEqualTo(newPosition, initialPositionX, initialPositionY - 1);
    }


    @Test
    void return_the_left_edge_when_performing_forward_movement_at_right_edge() {
        int initialPositionY = 2;
        Position positionAtRightEdge = new Position(MAX_X, initialPositionY);

        Position newPosition = positionAtRightEdge.moveForwardPositionX(GRID);

        assertPositionIsEqualTo(newPosition, MIN_X, initialPositionY);
    }

    @Test
    void return_the_right_edge_when_performing_backward_movement_at_left_edge() {
        int initialPositionY = 2;
        Position positionAtLeftEdge = new Position(MIN_X, initialPositionY);

        Position newPosition = positionAtLeftEdge.moveBackwardPositionX(GRID);

        assertPositionIsEqualTo(newPosition, MAX_X, initialPositionY);
    }

    @Test
    void return_the_bottom_edge_when_performing_forward_movement_at_top_edge() {
        int initialPositionX = 2;
        Position positionAtTopEdge = new Position(initialPositionX, MAX_Y);

        Position newPosition = positionAtTopEdge.moveForwardPositionY(GRID);

        assertPositionIsEqualTo(newPosition, initialPositionX, MIN_Y);
    }

    @Test
    void return_the_top_edge_when_performing_backward_movement_at_bottom_edge() {
        int initialPositionX = 2;
        Position positionAtBottomEdge = new Position(initialPositionX, MIN_Y);

        Position newPosition = positionAtBottomEdge.moveBackwardPositionY(GRID);

        assertPositionIsEqualTo(newPosition, initialPositionX, MAX_Y);
    }

    private static void assertPositionIsEqualTo(Position newPosition, int expectedX, int expectedY) {
        Assertions.assertEquals(expectedX, newPosition.positionX());
        Assertions.assertEquals(expectedY, newPosition.positionY());
    }
}
