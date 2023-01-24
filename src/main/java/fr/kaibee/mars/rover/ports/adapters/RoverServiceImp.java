package fr.kaibee.mars.rover.ports.adapters;

import fr.kaibee.mars.rover.ClientCommandInterpreter;
import fr.kaibee.mars.rover.domain.Rover;
import fr.kaibee.mars.rover.domain.RoverMovement;
import fr.kaibee.mars.rover.ports.inputs.RoverService;

import java.util.List;

public class RoverServiceImp implements RoverService {
    private ClientCommandInterpreter clientCommandInterpreter;

    public RoverServiceImp(ClientCommandInterpreter clientCommandInterpreter) {
        this.clientCommandInterpreter = clientCommandInterpreter;
    }

    @Override
    public void executeCommandsOnRover(Rover rover, Character[] clientCommands) {
        List<RoverMovement> roverMovements = clientCommandInterpreter.toRoverMovement(clientCommands);

        rover.performMovements(roverMovements);
    }
}
