package fr.kaibee.mars.rover.domain;

import java.util.List;

public class Rover {
    private Coordinates coordinates;

    public Rover(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void executeCommands(List<RoverCommand> roverCommands){
        roverCommands.stream().forEach(this::executeASingleCommand);
    }

    private void executeASingleCommand(RoverCommand roverCommand) {
        if (isForwardCommand(roverCommand)){
            moveForward();
        } else if (isBackwardCommand(roverCommand)){
            moveBackward();
        }
    }

    private static boolean isForwardCommand(RoverCommand roverCommand) {
        return MovementType.F.name().equalsIgnoreCase(roverCommand.getCommand());
    }

    private void moveForward() {
        this.coordinates.performForwardMovement();
    }

    private static boolean isBackwardCommand(RoverCommand roverCommand) {
        return MovementType.B.name().equalsIgnoreCase(roverCommand.getCommand());
    }

    private void moveBackward() {
        this.coordinates.performBackwardMovement();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
