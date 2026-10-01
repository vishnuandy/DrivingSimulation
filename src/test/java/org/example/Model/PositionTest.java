package org.example.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void shouldMoveNorth() {
        Position position = new Position(2, 3);

        Position result = position.move(Direction.NORTH);

        assertEquals(new Position(2, 4), result);
    }

    @Test
    void shouldMoveEast() {
        Position position = new Position(2, 3);

        Position result = position.move(Direction.EAST);

        assertEquals(new Position(3, 3), result);
    }

    @Test
    void shouldMoveSouth() {
        Position position = new Position(2, 3);

        Position result = position.move(Direction.SOUTH);

        assertEquals(new Position(2, 2), result);
    }

    @Test
    void shouldMoveWest() {
        Position position = new Position(2, 3);

        Position result = position.move(Direction.WEST);

        assertEquals(new Position(1, 3), result);
    }

    @Test
    void shouldNotModifyOriginalPosition() {
        Position position = new Position(2, 3);

        position.move(Direction.NORTH);

        assertEquals(new Position(2, 3), position);
    }
}