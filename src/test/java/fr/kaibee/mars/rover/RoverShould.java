package fr.kaibee.mars.rover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoverShould {

    @Test
    void have_a_position_and_a_direction(){
        Position startPosition = new Position(0, 0);
        Rover rover = new Rover(startPosition, Direction.N);

        Assertions.assertEquals(startPosition, rover.getPosition());
        Assertions.assertEquals(Direction.N, rover.getDirection());
    }

}
