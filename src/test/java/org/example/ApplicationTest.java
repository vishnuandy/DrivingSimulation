package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void shouldRunSimulationForSingleCar() {

        String input = """
                5 5
                1
                Car1
                1 1 N
                FFRF
                """;

        setInput(input);

        Application.main(new String[]{});

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains(
                "Car 'Car1' -> Position: (2, 3), Direction: EAST, Status: ACTIVE"
        ));

        assertTrue(result.contains(
                "No collisions detected."
        ));
    }

    @Test
    void shouldDetectCollision() {

        String input = """
                5 5
                2
                Car1
                1 1 E
                F
                Car2
                3 1 W
                F
                """;

        setInput(input);

        Application.main(new String[]{});

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains(
                "Car 'Car1' -> Position: (2, 1), Direction: EAST, Status: CRASHED"
        ));

        assertTrue(result.contains(
                "Car 'Car2' -> Position: (2, 1), Direction: WEST, Status: CRASHED"
        ));

        assertTrue(result.contains(
                "Cars 'Car1' and 'Car2' collided"
        ));
    }

    @Test
    void shouldRejectInvalidNumberOfCars() {

        String input = """
                5 5
                0
                """;

        setInput(input);

        assertThrows(
                IllegalArgumentException.class,
                () -> Application.main(new String[]{})
        );
    }

    @Test
    void shouldRejectInvalidFieldFormat() {

        String input = """
                5
                """;

        setInput(input);

        assertThrows(
                IllegalArgumentException.class,
                () -> Application.main(new String[]{})
        );
    }

    @Test
    void shouldRejectEmptyCarName() {

        String input = """
                5 5
                1

                """;

        setInput(input);

        assertThrows(
                IllegalArgumentException.class,
                () -> Application.main(new String[]{})
        );
    }

    @Test
    void shouldRejectInvalidPositionFormat() {

        String input = """
                5 5
                1
                Car1
                1 1
                """;

        setInput(input);

        assertThrows(
                IllegalArgumentException.class,
                () -> Application.main(new String[]{})
        );
    }

    @Test
    void shouldRejectInvalidDirection() {

        String input = """
                5 5
                1
                Car1
                1 1 X
                """;

        setInput(input);

        assertThrows(
                IllegalArgumentException.class,
                () -> Application.main(new String[]{})
        );
    }

    @Test
    void shouldRejectInvalidCommand() {

        String input = """
                5 5
                1
                Car1
                1 1 N
                X
                """;

        setInput(input);

        assertThrows(
                IllegalArgumentException.class,
                () -> Application.main(new String[]{})
        );
    }

    private void setInput(String input) {
        System.setIn(
                new ByteArrayInputStream(
                        input.getBytes(StandardCharsets.UTF_8)
                )
        );
    }
}