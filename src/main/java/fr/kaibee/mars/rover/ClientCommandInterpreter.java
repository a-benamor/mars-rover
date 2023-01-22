package fr.kaibee.mars.rover;

import fr.kaibee.mars.rover.domain.RoverCommand;

import java.util.Arrays;
import java.util.List;

public class ClientCommandInterpreter {

    public List<RoverCommand> toRoverCommands(Character[] clientCommands) {
        return Arrays.stream(clientCommands)
                .map(String::valueOf)
                .map(RoverCommand::toRoverCommand)
                .toList();
    }
}
