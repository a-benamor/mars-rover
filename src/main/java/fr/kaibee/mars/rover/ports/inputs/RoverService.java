package fr.kaibee.mars.rover.ports.inputs;

import fr.kaibee.mars.rover.domain.Rover;

public interface RoverService {
    void executeCommandsOnRover(Rover rover, Character[] clientCommands);
}
