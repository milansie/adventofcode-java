package y2015.d9;

import common.AdventDay;
import common.Step;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class D9 extends AdventDay {

    public static void main(String[] argv) {
        new D9().run();
    }

    private Set<String> cities;
    private Map<Pair<String, String>, Integer> distances;

    @Override
    public String first(List<String> input) {

        cities = new HashSet<>();
        distances = new HashMap<>();

        parseInput(input);

        return "" + findWay(true);
    }


    @Override
    public String second(List<String> input) {

        cities = new HashSet<>();
        distances = new HashMap<>();

        parseInput(input);

        return "" + findWay(false);
    }

    private int findWay(boolean shortest) {

        Queue<Step<String>> queue = new LinkedList<>();

        int best_distance = shortest ? Integer.MAX_VALUE : 0;



        for (String city : cities) {
            queue.offer(new Step<String>(city, new ArrayList<>(List.of(city)), 0));
        }

        while (!queue.isEmpty()) {
            Step<String> current = queue.poll();

            if (shortest && current.getTotalCost() > best_distance) {
                continue;
            }

            if (current.getPath().size() == cities.size()) {
                if (shortest) {
                    if (current.getTotalCost() < best_distance) {
                        best_distance = current.getTotalCost();
                    }
                } else {
                    if (current.getTotalCost() > best_distance) {
                        best_distance = current.getTotalCost();
                    }
                }
                continue;
            }

            for (String city : cities) {
                if (!current.getPath().contains(city)) {
                    List<String> newPath = new ArrayList<>(current.getPath());
                    newPath.add(city);
                    queue.offer(new Step<String>(city,
                            newPath,
                            current.getTotalCost() +
                                    distances.get(Pair.of(current.getCurrent(), city))));
                }
            }

        }
        return best_distance;
    }



    private void parseInput(List<String> input) {

        for (String line : input) {
            String[] parts = line.split(" ");
            String from = parts[0];
            String to = parts[2];
            int distance = Integer.parseInt(parts[4]);

            distances.put(Pair.of(from, to), distance);
            distances.put(Pair.of(to, from), distance);
            cities.add(from);
            cities.add(to);
        }


    }
}
