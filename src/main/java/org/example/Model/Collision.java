package org.example.Model;

public record Collision(
        String firstCar,
        String secondCar,
        Position position,
        int step
) {
}
