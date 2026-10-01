package org.example.Model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void shouldCreateCar() {

        Car car = new Car(
                "Car1",
                new Position(1, 2),
                Direction.NORTH,
                List.of(Command.FORWARD)
        );

        assertEquals("Car1", car.getName());
        assertEquals(new Position(1, 2), car.getPosition());
        assertEquals(Direction.NORTH, car.getDirection());
        assertEquals(
                List.of(Command.FORWARD),
                car.getCommands()
        );
        assertFalse(car.isCrashed());
    }

    @Test
    void shouldMoveCarToNewPosition() {

        Car car = new Car(
                "Car1",
                new Position(1, 2),
                Direction.NORTH,
                List.of()
        );

        Position newPosition = new Position(1, 3);

        car.moveTo(newPosition);

        assertEquals(newPosition, car.getPosition());
    }

    @Test
    void shouldReturnNextPosition() {

        Car car = new Car(
                "Car1",
                new Position(1, 2),
                Direction.NORTH,
                List.of()
        );

        assertEquals(
                new Position(1, 3),
                car.nextPosition()
        );
    }

    @Test
    void shouldTurnLeft() {

        Car car = new Car(
                "Car1",
                new Position(1, 2),
                Direction.NORTH,
                List.of()
        );

        car.turnLeft();

        assertEquals(Direction.WEST, car.getDirection());
    }

    @Test
    void shouldTurnRight() {

        Car car = new Car(
                "Car1",
                new Position(1, 2),
                Direction.NORTH,
                List.of()
        );

        car.turnRight();

        assertEquals(Direction.EAST, car.getDirection());
    }

    @Test
    void shouldCrashCar() {

        Car car = new Car(
                "Car1",
                new Position(1, 2),
                Direction.NORTH,
                List.of()
        );

        assertFalse(car.isCrashed());

        car.crash();

        assertTrue(car.isCrashed());
    }

    @Test
    void shouldRemainCrashedWhenCrashCalledMultipleTimes() {

        Car car = new Car(
                "Car1",
                new Position(1, 2),
                Direction.NORTH,
                List.of()
        );

        car.crash();
        car.crash();

        assertTrue(car.isCrashed());
    }
}