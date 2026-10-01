package org.example.Model;

public record Field(int width, int height) {

    public Field {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "Field width and height must be greater than zero");
        }
    }

    public boolean contains(Position position) {
        return position.x() >= 0
                && position.x() < width
                && position.y() >= 0
                && position.y() < height;
    }
}
