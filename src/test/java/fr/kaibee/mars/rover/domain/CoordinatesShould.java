package fr.kaibee.mars.rover.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinatesShould {
    private Coordinates coordinates;
    private final static Position START_POSITION = new Position(0, 0);


    @Test
    void have_the_west_direction_when_performing_turn_left_in_north_direction() {
        coordinates = createCoordinatesWithDirection(Direction.NORTH);

        Coordinates newCoordinates = coordinates.turnLeft();

        Assertions.assertEquals(Direction.WEST, newCoordinates.direction());
    }

    @Test
    void have_north_direction_when_performing_turn_left_in_east_direction() {
        coordinates = createCoordinatesWithDirection(Direction.EAST);

        Coordinates newCoordinates = coordinates.turnLeft();

        Assertions.assertEquals(Direction.NORTH, newCoordinates.direction());
    }

    @Test
    void have_east_direction_when_performing_turn_left_in_south_direction() {
        coordinates = createCoordinatesWithDirection(Direction.SOUTH);

        Coordinates newCoordinates = coordinates.turnLeft();

        Assertions.assertEquals(Direction.EAST, newCoordinates.direction());
    }

    @Test
    void have_south_direction_when_performing_turn_left_in_west_direction() {
        coordinates = createCoordinatesWithDirection(Direction.WEST);

        Coordinates newCoordinates = coordinates.turnLeft();

        Assertions.assertEquals(Direction.SOUTH, newCoordinates.direction());
    }

    @Test
    void have_east_direction_when_performing_turn_right_in_north_direction() {
        coordinates = createCoordinatesWithDirection(Direction.NORTH);

        Coordinates newCoordinates = coordinates.turnRight();

        Assertions.assertEquals(Direction.EAST, newCoordinates.direction());
    }

    @Test
    void have_south_direction_when_performing_turn_right_in_east_direction() {
        coordinates = createCoordinatesWithDirection(Direction.EAST);

        Coordinates newCoordinates = coordinates.turnRight();

        Assertions.assertEquals(Direction.SOUTH, newCoordinates.direction());
    }

    @Test
    void have_west_direction_when_performing_turn_right_in_south_direction() {
        coordinates = createCoordinatesWithDirection(Direction.SOUTH);

        Coordinates newCoordinates = coordinates.turnRight();

        Assertions.assertEquals(Direction.WEST, newCoordinates.direction());
    }

    @Test
    void have_north_direction_when_performing_turn_right_in_west_direction() {
        coordinates = createCoordinatesWithDirection(Direction.WEST);

        Coordinates newCoordinates = coordinates.turnRight();

        Assertions.assertEquals(Direction.NORTH, newCoordinates.direction());
    }


    private static Coordinates createCoordinatesWithDirection(Direction north) {
        return new Coordinates(START_POSITION, north);
    }

}
