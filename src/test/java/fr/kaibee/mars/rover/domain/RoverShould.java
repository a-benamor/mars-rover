package fr.kaibee.mars.rover.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class RoverShould {

    private static final String FORWARD = "F";
    private static final String BACKWARD = "B";

    private Rover rover;

    private Coordinates startedCoordinates;
    private Position startedPosition;

    @BeforeEach
    void setUp() {
        startedPosition = new Position(0, 0);
        startedCoordinates = createCoordinates(startedPosition, Direction.NORTH);
        rover = new Rover(startedCoordinates);
    }

    @Test
    void have_the_right_coordinates() {
        Position expectedPosition = new Position(0, 0);
        Coordinates expectedCoordinates = createCoordinates(expectedPosition, Direction.NORTH);

        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void have_the_right_coordinates_after_performing_forward_movement_in_north_direction() {
        List<RoverMovement> roverMovements = List.of(RoverMovement.F);

        rover.performMovements(roverMovements);

        Position expectedPosition = new Position(0, 1);
        Coordinates expectedCoordinates = createCoordinates(expectedPosition, Direction.NORTH);
        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void have_the_right_coordinates_after_performing_forward_movement_in_east_direction() {
        startedCoordinates = createCoordinates(startedPosition, Direction.EAST);
        rover = new Rover(startedCoordinates);
        List<RoverMovement> roverMovements = List.of(RoverMovement.F);

        rover.performMovements(roverMovements);

        Position expectedPosition = new Position(1, 0);
        Coordinates expectedCoordinates = createCoordinates(expectedPosition, Direction.EAST);
        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void have_the_right_coordinates_after_performing_forward_movement_in_south_direction() {
        startedCoordinates = createCoordinates(startedPosition, Direction.SOUTH);
        rover = new Rover(startedCoordinates);
        List<RoverMovement> roverMovements = List.of(RoverMovement.F);

        rover.performMovements(roverMovements);

        Position expectedPosition = new Position(0, -1);
        Coordinates expectedCoordinates = createCoordinates(expectedPosition, Direction.SOUTH);
        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void have_the_right_coordinates_after_performing_forward_movement_in_west_direction() {
        startedCoordinates = createCoordinates(startedPosition, Direction.WEST);
        rover = new Rover(startedCoordinates);
        List<RoverMovement> roverMovements = List.of(RoverMovement.F);

        rover.performMovements(roverMovements);

        Position expectedPosition = new Position(-1, 0);
        Coordinates expectedCoordinates = createCoordinates(expectedPosition, Direction.WEST);
        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void have_the_right_coordinates_after_performing_backward_movement_in_north_direction() {
        List<RoverMovement> roverMovements = List.of(RoverMovement.B);

        rover.performMovements(roverMovements);

        Position expectedPosition = new Position(0, -1);
        Coordinates expectedCoordinates = createCoordinates(expectedPosition, Direction.NORTH);
        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void have_the_right_coordinates_after_performing_backward_movement_in_east_direction() {
        startedCoordinates = createCoordinates(startedPosition, Direction.EAST);
        rover = new Rover(startedCoordinates);
        List<RoverMovement> roverMovements = List.of(RoverMovement.B);

        rover.performMovements(roverMovements);

        Position expectedPosition = new Position(-1, 0);
        Coordinates expectedCoordinates = createCoordinates(expectedPosition, Direction.EAST);

        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void have_the_right_coordinates_after_performing_backward_movement_in_south_direction() {
        startedCoordinates = createCoordinates(startedPosition, Direction.SOUTH);
        rover = new Rover(startedCoordinates);
        List<RoverMovement> roverMovements = List.of(RoverMovement.B);

        rover.performMovements(roverMovements);

        Position expectedPosition = new Position(0, 1);
        Coordinates expectedCoordinates = createCoordinates(expectedPosition, Direction.SOUTH);

        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void have_the_right_coordinates_after_performing_backward_movement_in_west_direction() {
        startedCoordinates = createCoordinates(startedPosition, Direction.WEST);
        rover = new Rover(startedCoordinates);
        List<RoverMovement> roverMovements = List.of(RoverMovement.B);

        rover.performMovements(roverMovements);

        Position expectedPosition = new Position(1, 0);
        Coordinates expectedCoordinates = createCoordinates(expectedPosition, Direction.WEST);

        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    private Coordinates createCoordinates(Position startedPosition, Direction east) {
        return Coordinates.Builder.builder()
                .position(startedPosition)
                .direction(east)
                .build();
    }


}
