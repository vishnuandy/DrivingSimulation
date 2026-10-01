package org.example.service;

import org.example.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    private Simulation simulation;

    @BeforeEach
    void setUp() {
        simulation = new Simulation(new Field(5, 5));
    }

    @Test
    void shouldMoveCarForward() {

        Car car = new Car(
                "Car1",
                new Position(1, 1),
                Direction.NORTH,
                List.of(Command.FORWARD)
        );

        simulation.addCar(car);

        Simulation.SimulationResult result = simulation.run();

        Car resultCar = result.cars().getFirst();

        assertEquals(new Position(1, 2), resultCar.getPosition());
        assertEquals(Direction.NORTH, resultCar.getDirection());
        assertFalse(resultCar.isCrashed());
    }

    @Test
    void shouldTurnLeft() {

        Car car = new Car(
                "Car1",
                new Position(1, 1),
                Direction.NORTH,
                List.of(Command.LEFT)
        );

        simulation.addCar(car);

        Simulation.SimulationResult result = simulation.run();

        Car resultCar = result.cars().getFirst();

        assertEquals(Direction.WEST, resultCar.getDirection());
        assertEquals(new Position(1, 1), resultCar.getPosition());
    }

    @Test
    void shouldTurnRight() {

        Car car = new Car(
                "Car1",
                new Position(1, 1),
                Direction.NORTH,
                List.of(Command.RIGHT)
        );

        simulation.addCar(car);

        Simulation.SimulationResult result = simulation.run();

        Car resultCar = result.cars().getFirst();

        assertEquals(Direction.EAST, resultCar.getDirection());
    }

    @Test
    void shouldNotMoveOutsideField() {

        Car car = new Car(
                "Car1",
                new Position(0, 0),
                Direction.SOUTH,
                List.of(Command.FORWARD)
        );

        simulation.addCar(car);

        Simulation.SimulationResult result = simulation.run();

        assertEquals(
                new Position(0, 0),
                result.cars().getFirst().getPosition()
        );
    }

    @Test
    void shouldDetectCollision() {

        Car car1 = new Car(
                "Car1",
                new Position(1, 1),
                Direction.EAST,
                List.of(Command.FORWARD)
        );

        Car car2 = new Car(
                "Car2",
                new Position(3, 1),
                Direction.WEST,
                List.of(Command.FORWARD)
        );

        simulation.addCar(car1);
        simulation.addCar(car2);

        Simulation.SimulationResult result = simulation.run();

        assertEquals(1, result.collisions().size());

        Collision collision = result.collisions().getFirst();

        assertEquals("Car1", collision.firstCar());
        assertEquals("Car2", collision.secondCar());
        assertEquals(new Position(2, 1), collision.position());
        assertEquals(1, collision.step());

        assertTrue(result.cars().get(0).isCrashed());
        assertTrue(result.cars().get(1).isCrashed());
    }

    @Test
    void shouldRejectCarOutsideField() {

        Car car = new Car(
                "Car1",
                new Position(10, 10),
                Direction.NORTH,
                List.of()
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> simulation.addCar(car)
        );
    }

    @Test
    void shouldRejectDuplicateCarName() {

        Car car1 = new Car(
                "Car1",
                new Position(1, 1),
                Direction.NORTH,
                List.of()
        );

        Car car2 = new Car(
                "Car1",
                new Position(2, 2),
                Direction.NORTH,
                List.of()
        );

        simulation.addCar(car1);

        assertThrows(
                IllegalArgumentException.class,
                () -> simulation.addCar(car2)
        );
    }
}