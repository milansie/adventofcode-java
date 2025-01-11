package y2015.d19;

import common.AdventDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class D19 extends AdventDay {

    private Map<String, List<String>> map = new HashMap<>();

    public static void main(String[] argv) {
        new D19().run();
    }

    public void reinit() {
        map = new HashMap<>();
    }

    @Override
    public String first(List<String> input) {

        String req = parse(input);
        return process_first(req);

    }

    @Override
    public String second(List<String> input) {
        String req = parse(input);
        return process_second(req);
    }


    private String process_first(String req) {

        Set<String> molecules = new HashSet<>();

        for (int i = 0; i < req.length(); i++) {

            String repl = req.substring(i, i+1);

            if (i > req.length() - 1 && !molecules.contains(req.substring(i, i + 2))) {
                repl = req.substring(i, i + 2);
            }

            if (map.containsKey(repl)) {
                List<String> replacements = map.get(repl);
                for (String replacement : replacements) {
                    molecules.add(req.substring(0, i) + replacement + req.substring(i + repl.length()));
                }
            }

            if (repl.length() > 1) {
                i = i + 1;
            }
        }

        return molecules.size() + "";
    }


    private String process_second(String req) {

        for (int i = 0; i < 1000; i++) {

            String current = req;
            int steps = 0;

            List<String> keys = new ArrayList<>(map.keySet().stream().toList());
            Collections.shuffle(keys);

            while (!current.equals ("e")) {

                boolean replaced = false;

                for (String key : keys) {

                    for (String replacement : map.get(key)) {

                        if (current.contains(replacement)) {
                            current = current.replaceFirst(replacement, key);
                            steps++;
                            replaced = true;
                        }

                        if (replaced) {
                            break;
                        }

                    }
                    if (replaced) {
                        break;
                    }

                }

                if (!replaced) {
                    break;
                }

            }

            if (current.equals("e")) {
                return steps + "";
            }
        }

        return "No solution found";


    }

    private String parse(List<String> input) {

        for (String line : input) {

            if (line.contains(" => ")) {

                String[] split = line.split(" ");

                map.computeIfAbsent(split[0], k -> new ArrayList<>()).add(split[2]);

                continue;
            }
            if (line.isEmpty()) {
                continue;
            }
            return line;
        }

        throw new RuntimeException("No input found");

    }


}

