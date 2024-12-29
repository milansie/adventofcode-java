package y2015.d3;

import common.AdventDay;
import common.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class D3 extends AdventDay {

    public static void main(String[] argv) {
        new D3().run();
    }

    @Override
    public String first(List<String> input) {
        int result = 0;

        for (String path : input) {
            Set<Position> visited = new HashSet<>();
            countHouses(path, visited);
            result += visited.size();
        }

        return result + "";
    }

    private void countHouses(String path, Set<Position> visited) {

        Position current = new Position(0, 0);
        visited.add(current);

        for (char p : path.toCharArray()) {

            current = switch (p) {
                case '^' -> new Position(current.getX(), current.getY() - 1);
                case 'v' -> new Position(current.getX(), current.getY() + 1);
                case '>' -> new Position(current.getX() + 1, current.getY());
                case '<' -> new Position(current.getX() - 1, current.getY());
                default -> current;
            };
            System.out.println(current);
            visited.add(current);
        }

    }

    @Override
    public String second(List<String> input) {
        int result = 0;

        for (String path : input) {

            StringBuilder santaPath = new StringBuilder();
            StringBuilder roboSantaPath = new StringBuilder();
            for (int i = 0; i < path.length(); i++) {
                if (i % 2 == 0) {
                    santaPath.append(path.charAt(i));
                } else {
                    roboSantaPath.append(path.charAt(i));
                }
            }

            Set<Position> visited = new HashSet<>();

            countHouses(santaPath.toString(), visited);
            countHouses(roboSantaPath.toString(), visited);

            result += visited.size();
        }

        return result + "";
    }
}
