package fr.kaibee.mars.rover.domain;

import fr.kaibee.mars.rover.domain.exceptions.ObstacleEncounteredException;

import java.util.Collections;
import java.util.Set;

/**
 * Square Grid based (from (1,1) to (size, size)
 */
public record Grid(int size, Set<Position> obstacles) {
    private static final int MIN_X = 1;
    private static final int MIN_Y = 1;

    public static Grid emptyGrid(int size) {
        return new Grid(size, Collections.emptySet());
    }

    public void checkAndGetPosition(Position position) {
        if (isPositionAnObstacle(position)) {
            throw new ObstacleEncounteredException("obstacle encountered at position" + position.toString());
        }
    }

    // min chosen based on the example of the kata.
    public int minX() {
        return MIN_X;
    }

    public int minY() {
        return MIN_Y;
    }

    private boolean isPositionAnObstacle(Position position) {
        return obstacles.contains(position);
    }
}
