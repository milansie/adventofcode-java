package y2015.d6;

import common.AdventDay;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D6 extends AdventDay {

    public static void main(String[] argv) {
        new D6().run();
    }

    @Override
    public String first(List<String> input) {

        boolean[][] lights = new boolean[1000][1000];

        for (String command : input) {

            Pattern pattern = Pattern.compile(".* ([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)");
            Matcher matcher = pattern.matcher(command);

            if (matcher.find()) {
                int x1 = Integer.parseInt(matcher.group(1));
                int y1 = Integer.parseInt(matcher.group(2));
                int x2 = Integer.parseInt(matcher.group(3));
                int y2 = Integer.parseInt(matcher.group(4));

                for (int y = y1; y <= y2; y++) {
                    for (int x = x1; x <= x2; x++) {
                        if (command.startsWith("turn on")) {
                            lights[y][x] = true;
                        } else if (command.startsWith("turn off")) {
                            lights[y][x] = false;
                        } else if (command.startsWith("toggle")) {
                            lights[y][x] = !lights[y][x];
                        }
                    }
                }
            }
        }

        int count = 0;

        for (int y = 0; y < 1000; y++) {
            for (int x = 0; x < 1000; x++) {
                if (lights[y][x]) {
                    count++;
                }
            }
        }

        return count + "";
    }

    @Override
    public String second(List<String> input) {

        int[][] lights = new int[1000][1000];
        for (int y = 0; y < 1000; y++) {
            for (int x = 0; x < 1000; x++) {
                lights[y][x] = 0;
            }
        }

        for (String command : input) {

            Pattern pattern = Pattern.compile(".* ([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)");
            Matcher matcher = pattern.matcher(command);

            if (matcher.find()) {
                int x1 = Integer.parseInt(matcher.group(1));
                int y1 = Integer.parseInt(matcher.group(2));
                int x2 = Integer.parseInt(matcher.group(3));
                int y2 = Integer.parseInt(matcher.group(4));

                for (int y = y1; y <= y2; y++) {
                    for (int x = x1; x <= x2; x++) {
                        if (command.startsWith("turn on")) {
                            lights[y][x] = lights[y][x] + 1;
                        } else if (command.startsWith("turn off")) {
                            lights[y][x] = lights[y][x] == 0 ? 0 : lights[y][x] - 1;
                        } else if (command.startsWith("toggle")) {
                            lights[y][x] = lights[y][x] + 2;
                        }
                    }
                }
            }
        }

        long count = 0;

        for (int y = 0; y < 1000; y++) {
            for (int x = 0; x < 1000; x++) {
                count += lights[y][x];
            }
        }

        return count + "";
    }

}
