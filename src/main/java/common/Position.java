package common;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Position() {
        this(0,0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[" +
                 x +
                ","+ y +
                ']';
    }

    public Position newPositionWithChange(Direction change) {
        Position newPosition = new Position(this);
        newPosition.change(change);
        return newPosition;
    }

    public void change(Direction move) {
        if (move == Direction.UP) {
            this.y = this.y - 1;
        } else if (move == Direction.DOWN) {
            this.y = this.y + 1;
        } else if (move == Direction.LEFT) {
            this.x = this.x - 1;
        } else if (move == Direction.RIGHT) {
            this.x = this.x + 1;
        } else {
            throw new IllegalArgumentException("Unknown direction: " + move);
        }
    }
}
