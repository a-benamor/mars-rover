package fr.kaibee.mars.rover;

import fr.kaibee.mars.rover.domain.RoverCommand;
import fr.kaibee.mars.rover.domain.exceptions.WrongClientCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ClientCommandInterpreterShould {
    private static final Character WRONG_CLIENT_COMMAND = 'x';
    private static final Character FORWARD_CLIENT_COMMAND = 'f';
    private static final Character BACKWARD_CLIENT_COMMAND = 'b';
    private ClientCommandInterpreter clientCommandInterpreter;

    @BeforeEach
    void setUp() {
        clientCommandInterpreter = new ClientCommandInterpreter();
    }

    @Test
    void interpret_a_valid_client_command() {
        Character[] clientCommands = {FORWARD_CLIENT_COMMAND, BACKWARD_CLIENT_COMMAND};

        List<RoverCommand> roverCommands = clientCommandInterpreter.toRoverCommands(clientCommands);

        RoverCommand expectedFirstRoverCommand = RoverCommand.toRoverCommand("f");
        RoverCommand expectedSecondRoverCommand = RoverCommand.toRoverCommand("b");
        Assertions.assertEquals(expectedFirstRoverCommand, roverCommands.get(0));
        Assertions.assertEquals(expectedSecondRoverCommand, roverCommands.get(1));
    }

    @Test
    void throw_an_exception_for_wrong_client_command() {
        Character[] clientCommands = {WRONG_CLIENT_COMMAND};

        WrongClientCommandException wrongClientCommandException = Assertions.assertThrows(
                WrongClientCommandException.class,
                () -> clientCommandInterpreter.toRoverCommands(clientCommands));

        Assertions.assertEquals("client command x is not supported", wrongClientCommandException.getMessage());
    }

}