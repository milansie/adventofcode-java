package common;

public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Direction createDirectionFromChar(char c) {
        return switch (c) {
            case '^' -> UP;
            case 'v' -> DOWN;
            case '<' -> LEFT;
            case '>' -> RIGHT;
            default -> null;
        };
    }

    public static Character convertDirectionToChar(int[] direction) {
        if (direction[0] == 0 && direction[1] == -1) {
            return '^';
        } else if (direction[0] == 0 && direction[1] == 1) {
            return 'v';
        } else if (direction[0] == -1 && direction[1] == 0) {
            return '<';
        } else if (direction[0] == 1 && direction[1] == 0) {
            return '>';
        }
        return null;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
        }
        return null;
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
        }
        return null;
    }

    /**
     * returns the cardinal directions in the order of UP, DOWN, LEFT, RIGHT
     * @return
     */
    public static int[][] getCardinalDirections() {
        return new int[][]{
                {0, -1},  // up
                {0, 1},   // down
                {-1, 0},  // left
                {1, 0}    // right
        };
    }

    /**
     * returns the directions in the order of UP, DOWN, LEFT, RIGHT, UP_LEFT, DOWN_LEFT, UP_RIGHT, DOWN_RIGHT
     * @return
     */
    public static int[][] getAllDirections() {
        return new int[][]{
                {0, -1},  // up
                {0, 1},   // down
                {-1, 0},  // left
                {1, 0},   // right
                {-1, -1}, // up left
                {1, -1},  // up right
                {-1, 1},  // down left
                {1, 1}    // down right
        };
    }
}
