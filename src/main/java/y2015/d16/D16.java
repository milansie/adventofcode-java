package y2015.d16;

import common.AdventDay;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D16 extends AdventDay {


    private Map<Integer, Map<String, Integer>> sues = new HashMap<>();

    private final Map<String, Integer> card = Map.of(
            "children", 3,
            "cats", 7,
            "samoyeds", 2,
            "pomeranians", 3,
            "akitas", 0,
            "vizslas", 0,
            "goldfish", 5,
            "trees", 3,
            "cars", 2,
            "perfumes", 1
    );



    public static void main(String[] argv) {
        new D16().run();
    }

    @Override
    public void reinit() {
        sues = new HashMap<>();


    }

    @Override
    public String first(List<String> input) {
        parse(input);
        return find(true);
    }

    @Override
    public String second(List<String> input) {
        parse(input);
        return find(false);
    }

    private void parse(List<String> lines) {

        Pattern pattern_line = Pattern.compile("Sue (\\d+): (.*)");
        Pattern pattern_prop = Pattern.compile("(\\w+): (-?\\d+)");


        for (String line : lines) {
            Matcher matcher = pattern_line.matcher(line);
            if (matcher.find()) {
                int sue = Integer.parseInt(matcher.group(1));
                String props = matcher.group(2);

                sues.put(sue, new HashMap<>());

                String[] prop_arr = props.split(", ");
                for (String prop : prop_arr) {
                    Matcher matcher_prop = pattern_prop.matcher(prop);
                    if (matcher_prop.find()) {
                        String name = matcher_prop.group(1);
                        int value = Integer.parseInt(matcher_prop.group(2));
                        sues.get(sue).put(name, value);
                    }
                }

            }
        }
    }

    private String find(boolean isFirst) {
        Set<Integer> possibleSues = sues.keySet();

        for (Map.Entry<String, Integer> item : card.entrySet()) {
            Set<Integer> suesToRemove = new HashSet<>();

            for (Integer sue : possibleSues) {

                Integer sueValue = sues.get(sue).get(item.getKey());

                if (isFirst) {
                    if (sueValue != null && !sueValue.equals(item.getValue())) {
                        suesToRemove.add(sue);
                    }

                } else {
                    if (sueValue != null) {

                        if ("cats".equals (item.getKey()) || "trees".equals(item.getKey())) {
                            if (sueValue <= item.getValue()) {
                                suesToRemove.add(sue);
                            }
                        } else if ("pomeranians".equals(item.getKey()) || "goldfish".equals(item.getKey())) {
                            if (sueValue >= item.getValue()) {
                                suesToRemove.add(sue);
                            }
                        } else {
                            if (!sueValue.equals(item.getValue())) {
                                suesToRemove.add(sue);
                            }
                        }

                    }
                }
            }

            possibleSues.removeAll(suesToRemove);
        }

        return possibleSues.stream().findFirst().map(String::valueOf).orElse("0");

    }



}

