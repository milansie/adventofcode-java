package y2015.d18;

import common.AdventDay;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class D18 extends AdventDay {

    public static void main(String[] argv) {
        new D18().run();
    }

    @Override
    public String first(List<String> input) {

        int size = super.isTest ?  6 : 100;
        int steps = super.isTest ? 4 : 100;

        boolean[][] lights_map = fillMapFromInput(size, input);

        List<Pair<Integer, Integer>> fixed = new ArrayList<>(0);

        lights_map = process(lights_map, steps, fixed);

        int onLights = 0;
        for (boolean[] row : lights_map) {
            for (boolean light : row) {
                if (light) {
                    onLights++;
                }
            }
        }

        return "" + onLights;
    }


    @Override
    public String second(List<String> input) {

        int size = super.isTest ?  6 : 100;
        int steps = super.isTest ? 5 : 100;

        boolean[][] lights_map = fillMapFromInput(size, input);

        List<Pair<Integer, Integer>> fixed = new ArrayList<>(0);
        fixed.add(Pair.of(0, 0));
        fixed.add(Pair.of(0, size - 1));
        fixed.add(Pair.of(size - 1, 0));
        fixed.add(Pair.of(size - 1, size - 1));

        lights_map[0][0] = true;
        lights_map[0][size - 1] = true;
        lights_map[size - 1][0] = true;
        lights_map[size - 1][size - 1] = true;

        lights_map = process(lights_map, steps, fixed);

        int onLights = 0;
        for (boolean[] row : lights_map) {
            for (boolean light : row) {
                if (light) {
                    onLights++;
                }
            }
        }

        return "" + onLights;
    }

    private boolean[][] fillMapFromInput(int size, List<String> input) {

        boolean[][] lights_map = new boolean[size][size];
        for (int j = 0; j < input.size(); j++) {
            String line = input.get(j);
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') {
                    lights_map[i][j] = true;
                }
            }
        }
        return lights_map;
    }


    private boolean[][] process(boolean[][] lightsMap, int steps, List<Pair<Integer, Integer>> fixed) {

        for (int i = 0; i < steps; i++) {

            boolean[][] newMap = new boolean[lightsMap.length][lightsMap.length];
            for (int y = 0; y < lightsMap.length; y++) {
                for (int x = 0; x < lightsMap[y].length; x++) {

                    if (fixed.contains(Pair.of(x, y))) {
                        newMap[y][x] = lightsMap[y][x];
                        continue;
                    }

                    int neighbours = getNeighbours(lightsMap, y, x);
                    newMap[y][x] = (lightsMap[y][x] && (neighbours == 2 || neighbours == 3) || !lightsMap[y][x] && neighbours == 3);

                }
            }


            lightsMap = newMap;
        }

        return lightsMap;
    }

    private int getNeighbours(boolean[][] lightsMap, int y, int x) {
        int neighbours = 0;

        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dy == 0 && dx == 0) {
                    continue;
                }

                if (0 <= y + dy && 0 <= x + dx && y + dy < lightsMap.length && x + dx < lightsMap[y + dy].length) {
                    neighbours += lightsMap[y + dy][x + dx] ? 1 : 0;
                }
            }
        }
        return neighbours;
    }


}

