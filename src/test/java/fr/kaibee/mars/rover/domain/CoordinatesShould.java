package fr.kaibee.mars.rover.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordinatesShould {
    private Coordinates coordinates;
    private Position startPosition;

    @BeforeEach
    void setUp() {
        startPosition = new Position(0,0);
    }

    @Test
    void have_the_west_direction_when_performing_turn_left_for_rover_in_north_direction(){
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.NORTH)
                .build();

        coordinates.performTurnLeftMovement();
        Assertions.assertEquals(Direction.WEST, coordinates.getDirection());
    }

    @Test
    void have_north_direction_when_performing_turn_left_on_rover_in_east_direction(){
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.EAST)
                .build();

        coordinates.performTurnLeftMovement();
        Assertions.assertEquals(Direction.NORTH, coordinates.getDirection());
    }

    @Test
    void have_east_direction_when_performing_turn_left_on_rover_in_south_direction(){
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.SOUTH)
                .build();

        coordinates.performTurnLeftMovement();
        Assertions.assertEquals(Direction.EAST, coordinates.getDirection());
    }

    @Test
    void have_south_direction_when_performing_turn_left_on_rover_in_west_direction(){
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.WEST)
                .build();

        coordinates.performTurnLeftMovement();
        Assertions.assertEquals(Direction.SOUTH, coordinates.getDirection());
    }

    @Test
    void have_the_east_direction_when_performing_turn_right_on_rover_in_north_direction(){
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.NORTH)
                .build();

        coordinates.performTurnRightMovement();
        Assertions.assertEquals(Direction.EAST, coordinates.getDirection());
    }

    @Test
    void have_south_direction_when_performing_turn_right_on_rover_in_east_direction(){
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.EAST)
                .build();

        coordinates.performTurnRightMovement();
        Assertions.assertEquals(Direction.SOUTH, coordinates.getDirection());
    }

    @Test
    void have_west_direction_when_performing_turn_right_on_rover_in_south_direction(){
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.SOUTH)
                .build();

        coordinates.performTurnRightMovement();
        Assertions.assertEquals(Direction.WEST, coordinates.getDirection());
    }

    @Test
    void have_north_direction_when_performing_turn_right_on_rover_in_west_direction(){
        coordinates = Coordinates.Builder.builder()
                .position(startPosition)
                .direction(Direction.WEST)
                .build();

        coordinates.performTurnRightMovement();
        Assertions.assertEquals(Direction.NORTH, coordinates.getDirection());
    }
}