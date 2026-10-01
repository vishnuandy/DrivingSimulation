package org.example.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void shouldReturnCorrectValueForLeft() {
        assertEquals('L', Command.LEFT.getValue());
    }

    @Test
    void shouldReturnCorrectValueForRight() {
        assertEquals('R', Command.RIGHT.getValue());
    }

    @Test
    void shouldReturnCorrectValueForForward() {
        assertEquals('F', Command.FORWARD.getValue());
    }

    @Test
    void shouldCreateLeftCommandFromCharacter() {
        assertEquals(Command.LEFT, Command.from('L'));
    }

    @Test
    void shouldCreateRightCommandFromCharacter() {
        assertEquals(Command.RIGHT, Command.from('R'));
    }

    @Test
    void shouldCreateForwardCommandFromCharacter() {
        assertEquals(Command.FORWARD, Command.from('F'));
    }

    @Test
    void shouldAcceptLowerCaseCommand() {
        assertEquals(Command.LEFT, Command.from('l'));
        assertEquals(Command.RIGHT, Command.from('r'));
        assertEquals(Command.FORWARD, Command.from('f'));
    }

    @Test
    void shouldRejectInvalidCommand() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Command.from('X')
        );
    }
}