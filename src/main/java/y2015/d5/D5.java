package y2015.d5;

import common.AdventDay;

import java.util.List;

public class D5 extends AdventDay {

    public static void main(String[] argv) {
        new D5().run();
    }

    @Override
    public String first(List<String> input) {
        int result = 0;

        for (String word : input) {

            boolean vowels = containVowels(word);
            if (!vowels) {
                continue;
            }
            boolean doubleLetter = containDoubleLetter(word);
            if (!doubleLetter) {
                continue;
            }
            boolean notForbidden = notContainForbidden(word);

            if (notForbidden) {
                result++;
            }

        }

        return result + "";
    }

    private boolean containVowels(String word) {
        return word.matches(".*[aeiou].*[aeiou].*[aeiou].*");
    }

    private boolean containDoubleLetter(String word) {
        return word.matches(".*(.)\\1.*");
    }

    private boolean notContainForbidden(String word) {
        return !word.matches(".*(ab|cd|pq|xy).*");
    }

    @Override
    public String second(List<String> input) {
        int result = 0;

        for (String word : input) {

            boolean twoLettersTwice = containsTwoLettersTwice(word);
            if (!twoLettersTwice) {
                continue;
            }
            boolean doubleLetter = containDoubleLetterWithOtherBetween(word);

            if (doubleLetter) {
                result++;
            }

        }

        return result + "";
    }

    private boolean containDoubleLetterWithOtherBetween(String word) {
        return word.matches(".*(.).\\1.*");
    }

    private boolean containsTwoLettersTwice(String word) {
        return word.matches(".*(..).*\\1.*");
    }
}
