package fr.kaibee.mars.rover.ports.inputs;

import fr.kaibee.mars.rover.ClientCommandInterpreter;
import fr.kaibee.mars.rover.ports.adapters.RoverServiceImp;
import fr.kaibee.mars.rover.domain.*;
import fr.kaibee.mars.rover.domain.exceptions.ObstacleEncounteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class RoverServiceShould {

    private static final Character FORWARD_CLIENT_COMMAND = 'f';
    private static final Character BACKWARD_CLIENT_COMMAND = 'b';
    private static final Character TURN_LEFT_CLIENT_COMMAND = 'l';
    private static final Character TURN_RIGHT_CLIENT_COMMAND = 'r';
    public static final int GRID_SIZE = 10;
    private Character[] clientCommands;

    private RoverService roverService;
    private Rover rover;
    private Set<Position> obstacles;

    @BeforeEach
    void setUp() {

        ClientCommandInterpreter clientCommandInterpreter = new ClientCommandInterpreter();
        roverService = new RoverServiceImp(clientCommandInterpreter);

        clientCommands =  new Character[]{ // (1,1)
                FORWARD_CLIENT_COMMAND, // => (1,2) N
                FORWARD_CLIENT_COMMAND, // => (1,3) N
                BACKWARD_CLIENT_COMMAND, // => (1,2) N
                FORWARD_CLIENT_COMMAND,// => (1,3) N
                TURN_LEFT_CLIENT_COMMAND, // => (1,3) W
                FORWARD_CLIENT_COMMAND, // => (10,3) W
                TURN_RIGHT_CLIENT_COMMAND,// => (10,3) N
                FORWARD_CLIENT_COMMAND, // => (10,4) N
                FORWARD_CLIENT_COMMAND, // => (10,5) N
                FORWARD_CLIENT_COMMAND, // => (10,6) N
                TURN_RIGHT_CLIENT_COMMAND, // => (10,6) E
                FORWARD_CLIENT_COMMAND}; // => (1,6)
    }

    @Test
    void return_rover_with_the_right_coordinates_after_commands_execution() {

        Coordinates initialCoordinates = createCoordinates(1, 1, Direction.NORTH);
        rover = new Rover(initialCoordinates, Grid.emptyGrid(GRID_SIZE));

        roverService.executeCommandsOnRover(rover, clientCommands);

        Coordinates expectedCoordinates =  createCoordinates(1, 6, Direction.EAST);
        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void throw_an_exception_when_encountering_an_obstacle() {
        Position obstaclePosition = new Position(10, 4);
        obstacles = Set.of(obstaclePosition);
        Coordinates initialCoordinates = createCoordinates(1, 1, Direction.NORTH);
        rover = new Rover(initialCoordinates, new Grid(GRID_SIZE, obstacles));

        ObstacleEncounteredException obstacleEncounteredException = Assertions.assertThrows(
                ObstacleEncounteredException.class,
                () -> roverService.executeCommandsOnRover(rover, clientCommands));

        Assertions.assertEquals("obstacle encountered at position" + obstaclePosition,
                obstacleEncounteredException.getMessage());
        Coordinates expectedRoverLastCoordinates = createCoordinates(10, 3, Direction.NORTH);
        Assertions.assertEquals(expectedRoverLastCoordinates, rover.getCoordinates());
    }

    private Coordinates createCoordinates(int positionX, int positionY, Direction direction) {
        Position position = new Position(positionX, positionY);
        return new Coordinates(position, direction);
    }
}
