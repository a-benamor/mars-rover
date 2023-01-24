package fr.kaibee.mars.rover.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionShould {

    @Test
    void return_the_left_edge_when_performing_forward_movement_at_right_edge() {
        int positionY = 2;
        Position positionAtRightEdge = new Position(Position.MARS_GRID_MAX_X, positionY);

        Position positionAtLeftEdge = positionAtRightEdge.moveForwardPositionX();

        Assertions.assertEquals(-Position.MARS_GRID_MAX_X, positionAtLeftEdge.positionX());
        Assertions.assertEquals(positionY, positionAtLeftEdge.positionY());
    }

    @Test
    void return_the_right_edge_when_performing_backward_movement_at_left_edge() {
        int positionY = 2;
        Position positionAtLeftEdge = new Position(-Position.MARS_GRID_MAX_X, positionY);

        Position positionAtRightEdge = positionAtLeftEdge.moveBackwardPositionX();

        Assertions.assertEquals(Position.MARS_GRID_MAX_X, positionAtRightEdge.positionX());
        Assertions.assertEquals(positionY, positionAtRightEdge.positionY());
    }

    @Test
    void return_the_bottom_edge_when_performing_forward_movement_at_top_edge() {
        int positionX = 2;
        Position positionAtTopEdge = new Position(positionX, Position.MARS_GRID_MAX_Y);

        Position positionAtBottomEdge = positionAtTopEdge.moveForwardPositionY();

        Assertions.assertEquals(positionX, positionAtBottomEdge.positionX());
        Assertions.assertEquals(-Position.MARS_GRID_MAX_Y, positionAtBottomEdge.positionY());
    }

    @Test
    void return_the_top_edge_when_performing_backward_movement_at_bottom_edge() {
        int positionX = 2;
        Position positionAtBottomEdge = new Position(positionX, -Position.MARS_GRID_MAX_Y);

        Position positionAtTopEdge = positionAtBottomEdge.moveBackwardPositionY();

        Assertions.assertEquals(positionX, positionAtTopEdge.positionX());
        Assertions.assertEquals(Position.MARS_GRID_MAX_Y, positionAtTopEdge.positionY());
    }

}