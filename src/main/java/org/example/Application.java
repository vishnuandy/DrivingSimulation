package org.example;

import org.example.Model.Car;
import org.example.Model.Command;
import org.example.Model.Direction;
import org.example.Model.Field;
import org.example.Model.Position;
import org.example.service.Simulation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            Field field = readField(scanner);
            Simulation simulation = new Simulation(field);

            int numberOfCars = readNumberOfCars(scanner);

            for (int i = 0; i < numberOfCars; i++) {
                simulation.addCar(readCar(scanner, i + 1));
            }

            Simulation.SimulationResult result = simulation.run();

            printResult(result);
        }
    }

    private static int readNumberOfCars(Scanner scanner) {
        System.out.print("Enter number of cars: ");

        int numberOfCars = Integer.parseInt(scanner.nextLine().trim());

        if (numberOfCars <= 0) {
            throw new IllegalArgumentException(
                    "Number of cars must be greater than zero."
            );
        }

        return numberOfCars;
    }

    private static Field readField(Scanner scanner) {
        System.out.print("Enter field width and height: ");

        String[] values = scanner.nextLine()
                .trim()
                .split("\\s+");

        if (values.length != 2) {
            throw new IllegalArgumentException(
                    "Field must contain width and height."
            );
        }

        int width = Integer.parseInt(values[0]);
        int height = Integer.parseInt(values[1]);

        return new Field(width, height);
    }

    private static Car readCar(Scanner scanner, int carNumber) {

        System.out.println();
        System.out.println("Enter details for Car " + carNumber);

        System.out.print("Car name: ");
        String name = scanner.nextLine().trim();

        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Car name cannot be empty."
            );
        }

        System.out.print("Enter position and direction (x y direction): ");

        String[] positionInput = scanner.nextLine()
                .trim()
                .split("\\s+");

        if (positionInput.length != 3) {
            throw new IllegalArgumentException(
                    "Position must be in the format: x y direction"
            );
        }

        int x = Integer.parseInt(positionInput[0]);
        int y = Integer.parseInt(positionInput[1]);

        Direction direction =
                Direction.from(positionInput[2].charAt(0));

        System.out.print("Enter commands (L/R/F): ");

        String commandInput = scanner.nextLine().trim();

        List<Command> commands = Arrays.stream(
                        commandInput.split("")
                )
                .filter(value -> !value.isBlank())
                .map(value -> Command.from(value.charAt(0)))
                .toList();

        return new Car(
                name,
                new Position(x, y),
                direction,
                commands
        );
    }

    private static void printResult(
            Simulation.SimulationResult result
    ) {

        System.out.println();
        System.out.println("========== Simulation Result ==========");

        for (Car car : result.cars()) {

            Position position = car.getPosition();

            System.out.printf(
                    "Car '%s' -> Position: (%d, %d), " +
                            "Direction: %s, Status: %s%n",
                    car.getName(),
                    position.x(),
                    position.y(),
                    car.getDirection(),
                    car.isCrashed()
                            ? "CRASHED"
                            : "ACTIVE"
            );
        }

        if (result.collisions().isEmpty()) {
            System.out.println("No collisions detected.");
        } else {
            System.out.println();
            System.out.println("Collisions:");

            result.collisions().forEach(collision ->
                    System.out.printf(
                            "Cars '%s' and '%s' collided at %s on step %d%n",
                            collision.firstCar(),
                            collision.secondCar(),
                            collision.position(),
                            collision.step()
                    )
            );
        }

        System.out.println("=======================================");
    }
}