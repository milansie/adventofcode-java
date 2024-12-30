package y2015.d7;

import common.AdventDay;
import common.algorithms.Gate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class D7 extends AdventDay {

    public static void main(String[] argv) {
        new D7().run();
    }

    private final Map<String, Integer> wiremap = new HashMap<>();
    private final Map<String, Gate> gates = new HashMap<>();


    @Override
    public String first(List<String> input) {

        parseInput(input);
        executeInstructions();

        if (isTest) {
            return wiremap.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(e -> e.getKey() + ": " + e.getValue()).collect(Collectors.joining("\n"));
        } else {
            return wiremap.get("a").toString();
        }
    }

    private void parseInput(List<String> input) {

        for (String commandLine : input) {

            String[] parts = commandLine.split(" ");

            if (parts.length == 3) {
                gates.put(parts[2], new Gate(parts[2], Gate.InstructionType.SET, parts[0]));
            } else if (parts.length == 4) {
                gates.put(parts[3], new Gate(parts[3], Gate.InstructionType.valueOf(parts[0]), parts[1]));
            } else if (parts.length == 5) {
                gates.put(parts[4], new Gate(parts[4], Gate.InstructionType.valueOf(parts[1]), parts[0], parts[2]));
            }

        }
    }


    /**
     * Resolves and computes values associated with a directed acyclic graph of gates within the wiremap.
     *
     * This method performs the following steps:
     *
     * 1. Initializes two data structures:
     *    - `dependOnMe`: A map tracking dependent gates for each wire.
     *    - `inDegree`: A map tracking the in-degree (number of incoming dependencies) for each wire.
     *
     */
    public void executeInstructions() {

        Map<String, List<String>> dependOnMe = new HashMap<>();

        Map<String, Integer> inDegree = new HashMap<>();
        for (String key : wiremap.keySet()) {
            inDegree.put(key, 0);
            dependOnMe.put(key, new ArrayList<>());
        }


        for (Gate gate : gates.values()) {

            if (gate.getInstruction() == Gate.InstructionType.SET && gate.getInput1().matches("\\d*")) {
                wiremap.put(gate.getOutput(), gate.performIntegerAction(Integer.parseInt(gate.getInput1()), null));
                inDegree.put(gate.getOutput(), 0);
                continue;
            }

            if (gates.containsKey(gate.getInput1())) {
                inDegree.computeIfAbsent(gate.getOutput(), k -> 0);
                inDegree.put(gate.getOutput(), inDegree.get(gate.getOutput()) + 1);
            }
            dependOnMe.computeIfAbsent(gate.getInput1(), k -> new ArrayList<>()).add(gate.getOutput());

            if (gates.containsKey(gate.getInput2())) {
                inDegree.computeIfAbsent(gate.getOutput(), k -> 0);
                inDegree.put(gate.getOutput(), inDegree.get(gate.getOutput()) + 1);
            }
            dependOnMe.computeIfAbsent(gate.getInput2(), k -> new ArrayList<>()).add(gate.getOutput());

        }

        Queue<String> queue = new LinkedList<>();

        for (String key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                queue.add(key);
            }
        }

        while (!queue.isEmpty()) {
            String key = queue.poll();

            Gate gate = gates.get(key);
            if (gate != null) {

                Integer input1 = null;
                if (gate.getInput1() != null) {
                    input1 = gate.getInput1().matches("\\d*") ? Integer.parseInt(gate.getInput1()) : wiremap.get(gate.getInput1());
                }

                Integer input2 = null;
                if (gate.getInput2() != null) {
                    input2 = gate.getInput2().matches("\\d*") ? Integer.parseInt(gate.getInput2()) : wiremap.get(gate.getInput2());
                }

                wiremap.put(gate.getOutput(), gate.performIntegerAction(input1, input2));
            }

            if (dependOnMe.containsKey(key)) {
                for (String depending : dependOnMe.get(key)) {
                    if (inDegree.get(depending) == null) {
                        queue.add(depending);
                    } else {
                        inDegree.put(depending, inDegree.get(depending) - 1);
                        if (inDegree.get(depending) == 0) {
                            queue.add(depending);
                        }
                    }
                }
            }

        }
    }


    @Override
    public String second(List<String> input) {
        input.set(3, "3176 -> b");  // we know the result
        parseInput(input);
        executeInstructions();

        if (isTest) {
            return wiremap.get("a").toString();

        } else {
            return wiremap.get("a").toString();
        }
    }

}
