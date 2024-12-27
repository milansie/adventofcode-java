package common;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Tools {

    /**
     * creates a new map with the same dimensions as the input map and fills it with the false
     */
    public static boolean[][] copyMapToVisitedMap(char[][] map) {
        return new boolean[map.length][map[0].length];
    }

    /**
     * finds the position of the startChar in the map
     * @param map the map to search in
     * @param startChar the char to search for
     * @return the position of the startChar or an empty optional if the char is not found
     */
    public static Optional<Position> findPositionOf(char[][] map, char startChar) {

        if (map == null || map.length == 0 || map[0].length == 0) {
            return Optional.empty();
        }

        int rows = map.length;
        int cols = map[0].length;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (map[y][x] == startChar) {
                    return Optional.of(new Position(x, y));
                }
            }
        }
        return Optional.empty();
    }


    public static void renderMap(char[][] map) {
        if (map == null || map.length == 0) {
            System.out.println("Mapa je prázdná nebo null.");
            return;
        }

        int rows = map.length;
        int cols = map[0].length;

        // Hlavička tabulky
        System.out.print("    |");
        for (int col = 1; col <= cols; col++) {
            System.out.printf(" %3d ", col);
        }
        System.out.println();

        // Oddělovač hlavičky
        System.out.print("----|");
        for (int col = 1; col <= cols; col++) {
            System.out.print("-----");
        }
        System.out.println();

        // Obsah tabulky
        for (int row = 0; row < rows; row++) {
            System.out.printf("%3d |", row + 1);
            for (int col = 0; col < cols; col++) {
                System.out.printf(" %3c ", map[row][col]);
            }
            System.out.println();
        }
    }
}
