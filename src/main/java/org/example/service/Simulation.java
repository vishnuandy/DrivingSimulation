package org.example.service;

import org.example.Model.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class Simulation {
    private final Field field;
    private final List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        Objects.requireNonNull(car, "Car cannot be null");
        validateCar(car);
        if (cars.stream().anyMatch(existingCar -> existingCar.getName().equals(car.getName()))) {
            throw new IllegalArgumentException("Car with name '%s' already exists".formatted(car.getName()));
        }
        cars.add(car);
    }

    private void validateCar(Car car) {
        if (!field.contains(car.getPosition())) {
            throw new IllegalArgumentException("Car '%s' starting position %s is outside the field".formatted(car.getName(), car.getPosition()));
        }
        if (cars.stream().anyMatch(existingCar -> existingCar.getPosition().equals(car.getPosition()))) {
            throw new IllegalArgumentException("Car '%s' cannot be placed at occupied position %s".formatted(car.getName(), car.getPosition()));
        }
    }

    public SimulationResult run() {
        List<Collision> collisions = new ArrayList<>();
        int maxSteps = cars.stream().mapToInt(car -> car.getCommands().size()).max().orElse(0);
        for (int step = 1; step <= maxSteps; step++) {
            executeStep(step, collisions);
        }
        return new SimulationResult(List.copyOf(cars), List.copyOf(collisions));
    }

    private void executeStep(int step, List<Collision> collisions) {
        for (Car car : cars) {
            if (car.isCrashed()) {
                continue;
            }
            List<Command> commands = car.getCommands();
            if (step > commands.size()) {
                continue;
            }
            Command command = commands.get(step - 1);
            executeCommand(car, command);
        }
        detectCollisions(step, collisions);
    }

    private void executeCommand(Car car, Command command) {
        switch (command) {
            case LEFT -> car.turnLeft();
            case RIGHT -> car.turnRight();
            case FORWARD -> moveForward(car);
        }
    }

    private void moveForward(Car car) {
        Position nextPosition = car.nextPosition();
        if (field.contains(nextPosition)) {
            car.moveTo(nextPosition);
        }
    }

    private void detectCollisions(int step, List<Collision> collisions) {
        for (int firstIndex = 0; firstIndex < cars.size(); firstIndex++) {
            Car firstCar = cars.get(firstIndex);
            if (firstCar.isCrashed()) {
                continue;
            }
            for (int secondIndex = firstIndex + 1; secondIndex < cars.size(); secondIndex++) {
                Car secondCar = cars.get(secondIndex);
                if (secondCar.isCrashed()) {
                    continue;
                }
                if (firstCar.getPosition().equals(secondCar.getPosition())) {
                    Position collisionPosition = firstCar.getPosition();
                    firstCar.crash();
                    secondCar.crash();
                    collisions.add(new Collision(firstCar.getName(), secondCar.getName(), collisionPosition, step));
                }
            }
        }
    }

    public record SimulationResult(List<Car> cars, List<Collision> collisions) {
    }
}