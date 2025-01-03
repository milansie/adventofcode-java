package y2015.d13;

import common.AdventDay;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D13 extends AdventDay {

    public static void main(String[] argv) {
        new D13().run();
    }

    final private Set<String> attendees = new HashSet<>();
    final private Map<Pair<String, String>, Integer> attendeesRelations = new HashMap<>();

    @Override
    public String first(List<String> input) {

        parse(input);
        return "" + findBestArrangement();
    }

    @Override
    public String second(List<String> input) {
        parse(input);


        for (String attendee : attendees ) {
            attendeesRelations.put(Pair.of(attendee, "me"), 0);
            attendeesRelations.put(Pair.of("me", attendee), 0);
        }

        attendees.add("me");

        return "" + findBestArrangement();
    }

    private void parse(List<String> lines) {

        Pattern pattern = Pattern.compile("(\\w+) would (\\w+) (\\d+) happiness.*to (\\w+).");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                int points = Integer.parseInt(matcher.group(3));
                if (matcher.group(2).equals("lose")) {
                    points *= -1;
                }
                Pair<String, String> attendee = Pair.of(matcher.group(1), matcher.group(4));
                attendeesRelations.put(attendee, points);
                attendees.add(matcher.group(1));
                attendees.add(matcher.group(4));
            }
        }
    }

    private int findBestArrangement() {

        int bestArrangement = Integer.MIN_VALUE;

        Queue<ArrangementStep> queue = new LinkedList<>();
        for (String attendee : attendees) {
            queue.add(new ArrangementStep(attendee, new ArrayList<>(List.of(attendee)), 0));
        }


        while (!queue.isEmpty()) {

            ArrangementStep current = queue.poll();

            if (current.getPath().size() == attendees.size()) {

                int possibleArrangement = (current.getTotalCost() +
                        attendeesRelations.get(Pair.of(current.getPath().getFirst(), current.getPath().getLast())) +
                        attendeesRelations.get(Pair.of(current.getPath().getLast(), current.getPath().getFirst())));

                if (possibleArrangement > bestArrangement) {
                    bestArrangement = possibleArrangement;
                    continue;
                }
            }

            for (String attendee : attendees) {

                if (!current.getPath().contains(attendee)) {

                    int newArrangement = (current.getTotalCost() +
                            attendeesRelations.get(Pair.of(current.getCurrent(), attendee))) +
                            attendeesRelations.get(Pair.of(attendee, current.getCurrent()));

                    ArrayList<String> newPath = new ArrayList<>(current.getPath());
                    newPath.add(attendee);

                    ArrangementStep newStep = new ArrangementStep(attendee, newPath, newArrangement);
                    queue.add(newStep);
                }
            }
        }

        return bestArrangement;

    }

}

