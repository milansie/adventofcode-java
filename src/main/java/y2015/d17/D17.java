package y2015.d17;

import common.AdventDay;

import java.util.Arrays;
import java.util.List;

public class D17 extends AdventDay {

    private static final int TEST_TARGET = 25;
    private static final int PROD_TARGET = 150;

    public static void main(String[] argv) {
        new D17().run();
    }



    @Override
    public String first(List<String> input) {

        List<Integer> containers = input.stream().map(Integer::parseInt).toList();

        int target = isTest ? TEST_TARGET : PROD_TARGET;

        int[] combinations = new int[target + 1];
        combinations[0] = 1;


        for (int container : containers) {

            for (int i = target; i >= container; i--) {
                combinations[i] += combinations[i - container];
            }
        }

        return "" + combinations[target];
    }

    @Override
    public String second(List<String> input) {

        List<Integer> containers = input.stream().map(Integer::parseInt).toList();

        int target = isTest ? TEST_TARGET : PROD_TARGET;

        int[] combinations = new int[target + 1];
        combinations[0] = 1;


        int[] minContainers = new int[target + 1];
        Arrays.fill(minContainers, Integer.MAX_VALUE);
        minContainers[0] = 0;

        int[] minCombinations = new int[target + 1];
        minCombinations[0] = 1;

        for (int container : containers) {

            for (int i = target; i >= container; i--) {

                if (combinations[i - container] > 0) {

                    combinations[i] += combinations[i - container];

                    if (minContainers[i] > minContainers[i - container] + 1) {
                        minContainers[i] = minContainers[i - container] + 1;
                        minCombinations[i] = minCombinations[i - container];
                    } else if (minContainers[ i] ==minContainers[i - container] + 1) {
                        minCombinations[i] += minCombinations[i - container];
                    }
                }

            }
        }

        return "" + minCombinations[target];
    }


}

