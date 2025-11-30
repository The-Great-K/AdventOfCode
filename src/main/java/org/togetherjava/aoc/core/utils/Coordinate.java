package org.togetherjava.aoc.core.utils;

import lombok.*;

/**
 *  Wrapper for x/y coordinates
 */
//@ToString
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(Coordinate coordinate) {
        this.x = coordinate.x;
        this.y = coordinate.y;
    }

    public Coordinate move(Direction direction, int magnitude) {
        int x = this.x + (direction.getXDirection() * magnitude);
        int y = this.y + (direction.getYDirection() * magnitude);
        return new Coordinate(x, y);
    }

    public Coordinate move(Direction direction) {
        return move(direction, 1);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(Coordinate offset) {
        this.x += offset.getX();
        this.y += offset.getY();
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x + 1, y + 1);
    }
}