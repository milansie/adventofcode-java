package y2015.d14;

import common.AdventDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D14 extends AdventDay {

    private List<Reindeer> reindeerMap;

    public static void main(String[] argv) {
        new D14().run();
    }

    @Override
    public void reinit() {
        reindeerMap = new ArrayList<>();
    }

    @Override
    public String first(List<String> input) {

        parse(input);

        int seconds = isTest ? 1000 : 2503;
        int maxDistance = 0;

        for (Reindeer reindeer : reindeerMap) {
            int distance = getDistance(reindeer, seconds);
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }

        return maxDistance + "";
    }

    @Override
    public String second(List<String> input) {

        parse(input);

        int seconds = isTest ? 1000 : 2503;
        Map<String, Integer> race = new HashMap<>();
        for (Reindeer reindeer : reindeerMap) {
            race.put(reindeer.getName(), 0);
        }

        for (int second = 1; second <= seconds + 1; second++) {
            int maxDistance = 0;
            List<Reindeer> maxReindeer = new ArrayList<>();
            for (Reindeer reindeer : reindeerMap) {
                int distance = getDistance(reindeer, second);
                if (distance > maxDistance) {
                    maxDistance = distance;
                    maxReindeer = new ArrayList<>(List.of(reindeer));
                } else if (distance == maxDistance) {
                    maxReindeer.add(reindeer);
                }
            }

            for (Reindeer reindeer : maxReindeer) {
                race.put(reindeer.getName(), race.get(reindeer.getName()) + 1);
            }
        }

        return race.values().stream().max(Comparator.comparingInt(Integer::intValue)).map(String::valueOf).orElse("0");
    }

    private void parse(List<String> lines) {

        Pattern pattern = Pattern.compile("(\\w+) can fly (\\d+) km/s for (\\d+) seconds.*for (\\d+) seconds.");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                Reindeer reindeer = new Reindeer(matcher.group(1), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
                reindeerMap.add(reindeer);
            }
        }
    }

    private int getDistance(Reindeer reindeer, int seconds) {

        int interval = reindeer.getFlyTime() + reindeer.getRestTime();
        int count = seconds / interval;

        int lastDistance = reindeer.getSpeed() * Math.min(seconds % interval, reindeer.getFlyTime());

        return count * reindeer.getFlyTime() * reindeer.getSpeed() + lastDistance;

    }

}

