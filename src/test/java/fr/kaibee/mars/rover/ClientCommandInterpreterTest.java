package fr.kaibee.mars.rover;

import fr.kaibee.mars.rover.domain.RoverMovement;
import fr.kaibee.mars.rover.domain.exceptions.UnsupportedClientCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ClientCommandInterpreterTest {
    private static final Character WRONG_CLIENT_COMMAND = 'x';
    private static final Character FORWARD_CLIENT_COMMAND = 'f';
    private static final Character BACKWARD_CLIENT_COMMAND = 'b';
    private static final Character TURN_LEFT_CLIENT_COMMAND = 'l';
    private static final Character TURN_RIGHT_CLIENT_COMMAND = 'r';
    private ClientCommandInterpreter clientCommandInterpreter;

    @BeforeEach
    void setUp() {
        clientCommandInterpreter = new ClientCommandInterpreter();
    }

    @Test
    void should_interpret_a_valid_client_command() {
        Character[] clientCommands = {FORWARD_CLIENT_COMMAND, TURN_LEFT_CLIENT_COMMAND,
                BACKWARD_CLIENT_COMMAND, TURN_RIGHT_CLIENT_COMMAND};

        List<RoverMovement> roverMovements = clientCommandInterpreter.toRoverMovement(clientCommands);

        Assertions.assertEquals(RoverMovement.F, roverMovements.get(0));
        Assertions.assertEquals(RoverMovement.L, roverMovements.get(1));
        Assertions.assertEquals(RoverMovement.B, roverMovements.get(2));
        Assertions.assertEquals(RoverMovement.R, roverMovements.get(3));
    }

    @Test
    void should_throw_an_exception_for_unsupported_client_command() {
        Character[] clientCommands = {WRONG_CLIENT_COMMAND};

        UnsupportedClientCommandException unsupportedClientCommandException = Assertions.assertThrows(
                UnsupportedClientCommandException.class,
                () -> clientCommandInterpreter.toRoverMovement(clientCommands));

        Assertions.assertEquals("client command x is not supported", unsupportedClientCommandException.getMessage());
    }

}