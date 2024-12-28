package y2015.d1;

import common.AdventDay;

import java.util.List;

public class D1 extends AdventDay {

    public static void main(String[] argv) {
        new D1().run();
    }

    @Override
    public String first(List<String> input) {

        StringBuilder result = new StringBuilder();

        for (String testInput : input) {
            int floor = 0;
            for (int i = 0; i < testInput.length(); i++) {
                if (testInput.charAt(i) == '(') {
                    floor++;
                } else {
                    floor--;
                }
            }

            result.append(floor);
        }

        return result.toString();
    }

    @Override
    public String second(List<String> input) {
        StringBuilder result = new StringBuilder();

        for (String testInput : input) {
            int floor = 0;
            for (int i = 0; i < testInput.length(); i++) {
                if (testInput.charAt(i) == '(') {
                    floor++;
                } else {
                    floor--;
                }
                if (floor < 0) {
                    return (i+1)+ "";
                }
            }

            result.append(floor);
        }

        return result.toString();
    }
}
