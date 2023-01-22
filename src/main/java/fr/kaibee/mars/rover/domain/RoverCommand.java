package fr.kaibee.mars.rover.domain;

import fr.kaibee.mars.rover.domain.exceptions.WrongClientCommandException;

import java.util.Objects;

public class RoverCommand {
    private static final String MOVE_FORWARD = "F";
    private static final String MOVE_BACKWARD = "B";

    private final String command;

    public static RoverCommand toRoverCommand(String command) {
        return new RoverCommand(command);
    }

    private RoverCommand(String command) {
        if (isValidCommand(command)) {
            this.command = command;
        } else {
            throw new WrongClientCommandException(String.format("client command %s is not supported", command));
        }
    }

    public String getCommand() {
        return command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoverCommand that = (RoverCommand) o;
        return command.equals(that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command);
    }

    private boolean isValidCommand(String command) {
        return MOVE_FORWARD.equalsIgnoreCase(command)
                || MOVE_BACKWARD.equalsIgnoreCase(command);
    }
}
