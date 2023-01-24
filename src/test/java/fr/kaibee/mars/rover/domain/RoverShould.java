package fr.kaibee.mars.rover.domain;

import fr.kaibee.mars.rover.domain.exceptions.ObstacleEncounteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

class RoverShould {

    public static final int GRID_SIZE = 4;
    public static final Grid NO_OBSTACLE_GRID = Grid.emptyGrid(GRID_SIZE);

    private final static Position FIRST_OBSTACLE = new Position(1, 2);
    private final static Position SECOND_OBSTACLE = new Position(2, 2);
    private final static Grid GRID = new Grid(GRID_SIZE, Set.of(FIRST_OBSTACLE, SECOND_OBSTACLE));

    private Rover rover;

    @ParameterizedTest
    @MethodSource("useCaseProvider")
    void have_the_right_coordinates_after_movement(RoverMovement roverMovement, int startPositionX, int startPositionY, Direction startDirection,
                                                   int expectedPositionX, int expectedPositionY, Direction expectedDirection) {

        Coordinates startCoordinates = createCoordinates(startPositionX, startPositionY, startDirection);
        rover = new Rover(startCoordinates, NO_OBSTACLE_GRID);

        List<RoverMovement> roverMovements = List.of(roverMovement);
        rover.performMovements(roverMovements);

        Coordinates expectedCoordinates = createCoordinates(expectedPositionX, expectedPositionY, expectedDirection);
        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    private static Stream<Arguments> useCaseProvider() {
        return Stream.of(
                Arguments.of(RoverMovement.F, 1, 1, Direction.NORTH, 1, 2, Direction.NORTH),
                Arguments.of(RoverMovement.F, 1, 1, Direction.EAST, 2, 1, Direction.EAST),
                Arguments.of(RoverMovement.F, 1, 1, Direction.SOUTH, 1, GRID.size(), Direction.SOUTH),
                Arguments.of(RoverMovement.F, 1, 1, Direction.WEST, GRID.size(), 1, Direction.WEST),
                Arguments.of(RoverMovement.B, 1, 1, Direction.NORTH, 1, GRID_SIZE, Direction.NORTH),
                Arguments.of(RoverMovement.B, 1, 1, Direction.EAST, GRID_SIZE, 1, Direction.EAST),
                Arguments.of(RoverMovement.B, 1, 1, Direction.SOUTH, 1, 2, Direction.SOUTH),
                Arguments.of(RoverMovement.B, 1, 1, Direction.WEST, 2, 1, Direction.WEST)
        );
    }


    @Test
    void throw_exception_when_performing_forward_to_an_obstacle() {
        Coordinates coordinates = createCoordinates(1, 1, Direction.NORTH);
        rover = new Rover(coordinates, GRID);
        List<RoverMovement> roverMovements = List.of(RoverMovement.F);

        ObstacleEncounteredException obstacleEncounteredException = Assertions.assertThrows(
                ObstacleEncounteredException.class,
                () -> rover.performMovements(roverMovements));

        Assertions.assertEquals("obstacle encountered at position" + FIRST_OBSTACLE,
                obstacleEncounteredException.getMessage());
    }

    @Test
    void throw_exception_when_performing_backward_to_an_obstacle() {
        Coordinates coordinates = createCoordinates(2, 3, Direction.NORTH);
        rover = new Rover(coordinates, GRID);
        List<RoverMovement> roverMovements = List.of(RoverMovement.B);

        ObstacleEncounteredException obstacleEncounteredException = Assertions.assertThrows(
                ObstacleEncounteredException.class,
                () -> rover.performMovements(roverMovements));

        Assertions.assertEquals("obstacle encountered at position" + SECOND_OBSTACLE,
                obstacleEncounteredException.getMessage());
    }

    private static Coordinates createCoordinates(int positionX, int positionY, Direction direction) {
        Position position = new Position(positionX, positionY);
        return new Coordinates(position, direction);
    }
}
