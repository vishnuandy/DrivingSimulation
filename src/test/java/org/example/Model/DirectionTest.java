package org.example.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void shouldReturnCorrectValueForNorth() {
        assertEquals('N', Direction.NORTH.getValue());
    }

    @Test
    void shouldReturnCorrectValueForEast() {
        assertEquals('E', Direction.EAST.getValue());
    }

    @Test
    void shouldReturnCorrectValueForSouth() {
        assertEquals('S', Direction.SOUTH.getValue());
    }

    @Test
    void shouldReturnCorrectValueForWest() {
        assertEquals('W', Direction.WEST.getValue());
    }

    @Test
    void shouldCreateNorthFromCharacter() {
        assertEquals(Direction.NORTH, Direction.from('N'));
    }

    @Test
    void shouldCreateEastFromCharacter() {
        assertEquals(Direction.EAST, Direction.from('E'));
    }

    @Test
    void shouldCreateSouthFromCharacter() {
        assertEquals(Direction.SOUTH, Direction.from('S'));
    }

    @Test
    void shouldCreateWestFromCharacter() {
        assertEquals(Direction.WEST, Direction.from('W'));
    }

    @Test
    void shouldAcceptLowerCaseDirection() {
        assertEquals(Direction.NORTH, Direction.from('n'));
        assertEquals(Direction.EAST, Direction.from('e'));
        assertEquals(Direction.SOUTH, Direction.from('s'));
        assertEquals(Direction.WEST, Direction.from('w'));
    }

    @Test
    void shouldRejectInvalidDirection() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Direction.from('X')
        );
    }

    @Test
    void shouldTurnLeftFromNorth() {
        assertEquals(Direction.WEST, Direction.NORTH.turnLeft());
    }

    @Test
    void shouldTurnLeftFromWest() {
        assertEquals(Direction.SOUTH, Direction.WEST.turnLeft());
    }

    @Test
    void shouldTurnLeftFromSouth() {
        assertEquals(Direction.EAST, Direction.SOUTH.turnLeft());
    }

    @Test
    void shouldTurnLeftFromEast() {
        assertEquals(Direction.NORTH, Direction.EAST.turnLeft());
    }

    @Test
    void shouldTurnRightFromNorth() {
        assertEquals(Direction.EAST, Direction.NORTH.turnRight());
    }

    @Test
    void shouldTurnRightFromEast() {
        assertEquals(Direction.SOUTH, Direction.EAST.turnRight());
    }

    @Test
    void shouldTurnRightFromSouth() {
        assertEquals(Direction.WEST, Direction.SOUTH.turnRight());
    }

    @Test
    void shouldTurnRightFromWest() {
        assertEquals(Direction.NORTH, Direction.WEST.turnRight());
    }
}