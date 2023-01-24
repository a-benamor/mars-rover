package fr.kaibee.mars.rover;

import fr.kaibee.mars.rover.domain.RoverMovement;
import fr.kaibee.mars.rover.domain.exceptions.UnsupportedClientCommandException;

import java.util.Arrays;
import java.util.List;

public class ClientCommandInterpreter {
    public List<RoverMovement> toRoverMovement(Character[] clientCommands) {
        return Arrays.stream(clientCommands)
                .map(this::checkClientCommand)
                .map(String::valueOf)
                .map(String::toUpperCase)
                .map(RoverMovement::valueOf)
                .toList();
    }

    private Character checkClientCommand(Character clientCommand) {
        if (isCommandValid(clientCommand)) {
            return clientCommand;
        }
        throw new UnsupportedClientCommandException(String.format("client command %s is not supported", clientCommand));
    }

    private boolean isCommandValid(Character clientCommand) {
        String command = String.valueOf(clientCommand);
        return Arrays.stream(RoverMovement.values())
                .map(Enum::name)
                .anyMatch(command::equalsIgnoreCase);
    }
}
