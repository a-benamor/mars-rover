package fr.kaibee.mars.rover.domain;

import fr.kaibee.mars.rover.domain.exceptions.ObstacleEncounteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class CoordinatesShould {
    private Coordinates coordinates;
    private Position startPosition;
    private Set<Position> obstacles;
    private Position firstObstaclePosition;
    private Position secondObstaclePosition;

    @BeforeEach
    void setUp() {
        startPosition = new Position(0, 0);

        firstObstaclePosition = new Position(0, 2);
        secondObstaclePosition = new Position(2, 2);
        obstacles = Set.of(firstObstaclePosition, secondObstaclePosition);
    }

    @Test
    void have_the_west_direction_when_performing_turn_left_for_rover_in_north_direction() {
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.NORTH)
                .build();

        coordinates.performTurnLeftMovement();
        Assertions.assertEquals(Direction.WEST, coordinates.getDirection());
    }

    @Test
    void have_north_direction_when_performing_turn_left_on_rover_in_east_direction() {
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.EAST)
                .build();

        coordinates.performTurnLeftMovement();
        Assertions.assertEquals(Direction.NORTH, coordinates.getDirection());
    }

    @Test
    void have_east_direction_when_performing_turn_left_on_rover_in_south_direction() {
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.SOUTH)
                .build();

        coordinates.performTurnLeftMovement();
        Assertions.assertEquals(Direction.EAST, coordinates.getDirection());
    }

    @Test
    void have_south_direction_when_performing_turn_left_on_rover_in_west_direction() {
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.WEST)
                .build();

        coordinates.performTurnLeftMovement();
        Assertions.assertEquals(Direction.SOUTH, coordinates.getDirection());
    }

    @Test
    void have_the_east_direction_when_performing_turn_right_on_rover_in_north_direction() {
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.NORTH)
                .build();

        coordinates.performTurnRightMovement();
        Assertions.assertEquals(Direction.EAST, coordinates.getDirection());
    }

    @Test
    void have_south_direction_when_performing_turn_right_on_rover_in_east_direction() {
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.EAST)
                .build();

        coordinates.performTurnRightMovement();
        Assertions.assertEquals(Direction.SOUTH, coordinates.getDirection());
    }

    @Test
    void have_west_direction_when_performing_turn_right_on_rover_in_south_direction() {
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.SOUTH)
                .build();

        coordinates.performTurnRightMovement();
        Assertions.assertEquals(Direction.WEST, coordinates.getDirection());
    }

    @Test
    void have_north_direction_when_performing_turn_right_on_rover_in_west_direction() {
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.WEST)
                .build();

        coordinates.performTurnRightMovement();
        Assertions.assertEquals(Direction.NORTH, coordinates.getDirection());
    }

    @Test
    void throw_exception_when_performing_forward_to_an_obstacle() {
        startPosition = new Position(0, 1);
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.NORTH)
                .obstacles(obstacles)
                .build();

        ObstacleEncounteredException obstacleEncounteredException = Assertions.assertThrows(
                ObstacleEncounteredException.class,
                () -> coordinates.performForwardMovement());

        Assertions.assertEquals("obstacle encountered at position" + firstObstaclePosition.toString(),
                obstacleEncounteredException.getMessage());
    }

    @Test
    void throw_exception_when_performing_backward_to_an_obstacle() {
        startPosition = new Position(2, 3);
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.NORTH)
                .obstacles(obstacles)
                .build();

        ObstacleEncounteredException obstacleEncounteredException = Assertions.assertThrows(
                ObstacleEncounteredException.class,
                () -> coordinates.performBackwardMovement());

        Assertions.assertEquals("obstacle encountered at position" + secondObstaclePosition,
                obstacleEncounteredException.getMessage());
    }
}