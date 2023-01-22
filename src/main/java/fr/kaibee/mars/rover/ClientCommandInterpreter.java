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
        if (isValidCommand(clientCommand)) {
            return clientCommand;
        } else {
            throw new UnsupportedClientCommandException(String.format("client command %s is not supported", clientCommand));
        }
    }

    private boolean isValidCommand(Character clientCommand) {
        return String.valueOf(clientCommand).equalsIgnoreCase(RoverMovement.F.name())
                || String.valueOf(clientCommand).equalsIgnoreCase(RoverMovement.B.name())
                || String.valueOf(clientCommand).equalsIgnoreCase(RoverMovement.L.name())
                || String.valueOf(clientCommand).equalsIgnoreCase(RoverMovement.R.name());
    }
}
