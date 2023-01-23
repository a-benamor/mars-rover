package fr.kaibee.mars.rover.ports.input;

import fr.kaibee.mars.rover.ClientCommandInterpreter;
import fr.kaibee.mars.rover.RoverServiceImp;
import fr.kaibee.mars.rover.domain.Coordinates;
import fr.kaibee.mars.rover.domain.Direction;
import fr.kaibee.mars.rover.domain.Position;
import fr.kaibee.mars.rover.domain.Rover;
import fr.kaibee.mars.rover.domain.exceptions.ObstacleEncounteredException;
import fr.kaibee.mars.rover.ports.inputs.RoverService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class RoverServiceShould {

    private static final Character FORWARD_CLIENT_COMMAND = 'f';
    private static final Character BACKWARD_CLIENT_COMMAND = 'b';
    private static final Character TURN_LEFT_CLIENT_COMMAND = 'l';
    private static final Character TURN_RIGHT_CLIENT_COMMAND = 'r';
    private Character[] clientCommands;

    private RoverService roverService;
    private Rover rover;
    private Set<Position> obstacles;
    Position startedPosition;

    @BeforeEach
    void setUp() {
        startedPosition = new Position(0, 0);

        obstacles = Set.of(new Position(4,4), new Position(1, 3));
        Coordinates initialCoordinates = getCoordinates(startedPosition, Direction.NORTH, obstacles);
        rover = new Rover(initialCoordinates);

        ClientCommandInterpreter clientCommandInterpreter = new ClientCommandInterpreter();
        roverService = new RoverServiceImp(clientCommandInterpreter);

        clientCommands = new Character[]{FORWARD_CLIENT_COMMAND, FORWARD_CLIENT_COMMAND,
                BACKWARD_CLIENT_COMMAND, FORWARD_CLIENT_COMMAND,
                TURN_LEFT_CLIENT_COMMAND, FORWARD_CLIENT_COMMAND,
                TURN_RIGHT_CLIENT_COMMAND, FORWARD_CLIENT_COMMAND,
                FORWARD_CLIENT_COMMAND, FORWARD_CLIENT_COMMAND,
                TURN_RIGHT_CLIENT_COMMAND, FORWARD_CLIENT_COMMAND};
    }

    @Test
    void return_rover_with_the_right_coordinates_after_commands_execution() {
        roverService.executeCommandsOnRover(rover, clientCommands);

        Position expectedPosition = new Position(0, 5);
        Coordinates expectedCoordinates = Coordinates.Builder.builder()
                .position(expectedPosition)
                .direction(Direction.EAST)
                .build();

        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }

    @Test
    void throw_an_exception_when_encountering_an_obstacle() {
        obstacles = Set.of(new Position(-1,5), new Position(1, 3));
        Coordinates initialCoordinates = getCoordinates(startedPosition, Direction.NORTH, obstacles);
        rover = new Rover(initialCoordinates);

        ObstacleEncounteredException obstacleEncounteredException = Assertions.assertThrows(
                ObstacleEncounteredException.class,
                () -> roverService.executeCommandsOnRover(rover, clientCommands));

        Position expectedObstaclePosition = new Position(-1, 5);
        Assertions.assertEquals("obstacle encountered at position" + expectedObstaclePosition,
                obstacleEncounteredException.getMessage());

        Position expectedLastRoverPosition = new Position(-1, 4);
        Coordinates expectedRoverLastCoordinates = getCoordinates(expectedLastRoverPosition, Direction.NORTH, Set.of());
        Assertions.assertEquals(expectedRoverLastCoordinates, rover.getCoordinates());
    }

    private Coordinates getCoordinates(Position startedPosition, Direction direction, Set<Position> obstacles) {
        Coordinates coordinates = Coordinates.Builder.builder()
                .position(startedPosition)
                .direction(direction)
                .obstacles(obstacles)
                .build();

        return coordinates;
    }
}