package y2015.d10;

import common.AdventDay;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class D10 extends AdventDay {

    public static void main(String[] argv) {
        new D10().run();
    }

    private Set<String> cities;
    private Map<Pair<String, String>, Integer> distances;

    @Override
    public String first(List<String> input) {

        int count = 40;
        return "" + process(count, input.getFirst());

    }


    @Override
    public String second(List<String> input) {

        int count = 50;
        return "" + process(count, input.getFirst());
    }

    private int process(int count, String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < count; i++) {
           result = new StringBuilder();

           char previousChar = input.charAt(0);
           int number = 1;

           for (int j = 1; j < input.length(); j++) {
               if (input.charAt(j) == previousChar) {
                   number++;
                   continue;
               }

               result.append(number).append(previousChar);
               previousChar = input.charAt(j);
               number = 1;
           }

           result.append(number).append(previousChar);
           input = result.toString();
        }

        return result.length();
    }
}
