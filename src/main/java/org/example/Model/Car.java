package org.example.Model;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Car {

    private final String name;
    private final List<Command> commands;

    private Position position;
    private Direction direction;
    private boolean crashed;

    public Car(
            String name,
            Position position,
            Direction direction,
            List<Command> commands) {

        this.name = validateName(name);
        this.position = Objects.requireNonNull(
                position,
                "Position cannot be null"
        );
        this.direction = Objects.requireNonNull(
                direction,
                "Direction cannot be null"
        );
        this.commands = List.copyOf(
                Objects.requireNonNull(
                        commands,
                        "Commands cannot be null"
                )
        );
    }

    private static String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Car name cannot be blank"
            );
        }

        return name.trim();
    }

    public void turnLeft() {
        if (!crashed) {
            direction = direction.turnLeft();
        }
    }

    public void turnRight() {
        if (!crashed) {
            direction = direction.turnRight();
        }
    }

    public Position nextPosition() {
        return position.move(direction);
    }

    public void moveTo(Position newPosition) {
        if (crashed) {
            return;
        }

        this.position = Objects.requireNonNull(
                newPosition,
                "New position cannot be null"
        );
    }

    public void crash() {
        crashed = true;
    }
}