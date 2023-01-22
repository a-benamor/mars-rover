package fr.kaibee.mars.rover;

import fr.kaibee.mars.rover.domain.RoverCommand;
import fr.kaibee.mars.rover.domain.Rover;
import fr.kaibee.mars.rover.ports.inputs.RoverService;

import java.util.List;

public class RoverServiceImp implements RoverService {
    private ClientCommandInterpreter clientCommandInterpreter;

    public RoverServiceImp(ClientCommandInterpreter clientCommandInterpreter) {
        this.clientCommandInterpreter = clientCommandInterpreter;
    }

    @Override
    public void executeCommandsOnRover(Rover rover, Character[] clientCommands) {
        List<RoverCommand> roverCommands = clientCommandInterpreter.toRoverCommands(clientCommands);

        rover.executeCommands(roverCommands);
    }
}
