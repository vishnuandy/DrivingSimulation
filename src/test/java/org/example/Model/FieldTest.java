package org.example.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void shouldCreateFieldWithValidDimensions() {
        Field field = new Field(5, 5);

        assertEquals(5, field.width());
        assertEquals(5, field.height());
    }

    @Test
    void shouldRejectZeroWidth() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Field(0, 5)
        );
    }

    @Test
    void shouldRejectZeroHeight() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Field(5, 0)
        );
    }

    @Test
    void shouldRejectNegativeWidth() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Field(-1, 5)
        );
    }

    @Test
    void shouldRejectNegativeHeight() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Field(5, -1)
        );
    }

    @Test
    void shouldContainPositionInsideField() {
        Field field = new Field(5, 5);

        assertTrue(field.contains(new Position(2, 3)));
    }

    @Test
    void shouldContainPositionAtBottomLeftBoundary() {
        Field field = new Field(5, 5);

        assertTrue(field.contains(new Position(0, 0)));
    }

    @Test
    void shouldContainPositionAtTopRightBoundary() {
        Field field = new Field(5, 5);

        assertTrue(field.contains(new Position(4, 4)));
    }

    @Test
    void shouldRejectPositionOutsideLeftBoundary() {
        Field field = new Field(5, 5);

        assertFalse(field.contains(new Position(-1, 2)));
    }

    @Test
    void shouldRejectPositionOutsideRightBoundary() {
        Field field = new Field(5, 5);

        assertFalse(field.contains(new Position(5, 2)));
    }

    @Test
    void shouldRejectPositionOutsideBottomBoundary() {
        Field field = new Field(5, 5);

        assertFalse(field.contains(new Position(2, -1)));
    }

    @Test
    void shouldRejectPositionOutsideTopBoundary() {
        Field field = new Field(5, 5);

        assertFalse(field.contains(new Position(2, 5)));
    }
}