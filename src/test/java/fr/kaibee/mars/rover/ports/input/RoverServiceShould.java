package fr.kaibee.mars.rover.ports.input;

import fr.kaibee.mars.rover.ClientCommandInterpreter;
import fr.kaibee.mars.rover.RoverServiceImp;
import fr.kaibee.mars.rover.domain.Coordinates;
import fr.kaibee.mars.rover.domain.Direction;
import fr.kaibee.mars.rover.domain.Position;
import fr.kaibee.mars.rover.domain.Rover;
import fr.kaibee.mars.rover.ports.inputs.RoverService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoverServiceShould {

    private static final Character FORWARD_CLIENT_COMMAND = 'f';
    private static final Character BACKWARD_CLIENT_COMMAND = 'b';

    private RoverService roverService;
    private Rover rover;

    @BeforeEach
    void setUp() {
        Position startedPosition = new Position(0, 0);
        Coordinates initialCoordinates = Coordinates.Builder.builder()
                .position(startedPosition)
                .direction(Direction.NORTH)
                .build();
        rover = new Rover(initialCoordinates);

        ClientCommandInterpreter clientCommandInterpreter = new ClientCommandInterpreter();

        roverService = new RoverServiceImp(clientCommandInterpreter);
    }

    @Test
    void return_rover_with_the_right_coordinates_after_commands_execution() {
        Character[] clientCommands = {FORWARD_CLIENT_COMMAND, BACKWARD_CLIENT_COMMAND,
                FORWARD_CLIENT_COMMAND, FORWARD_CLIENT_COMMAND};
        roverService.executeCommandsOnRover(rover, clientCommands);

        Position expectedPosition = new Position(0, 2);
        Coordinates expectedCoordinates = Coordinates.Builder.builder()
                .position(expectedPosition)
                .direction(Direction.NORTH)
                .build();

        Assertions.assertEquals(expectedCoordinates, rover.getCoordinates());
    }
}