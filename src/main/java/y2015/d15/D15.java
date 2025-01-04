package y2015.d15;

import common.AdventDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D15 extends AdventDay {

    private List<Ingredient> ingredients;

    public static void main(String[] argv) {
        new D15().run();
    }

    @Override
    public void reinit() {
        ingredients = new ArrayList<>();
    }

    @Override
    public String first(List<String> input) {

        parse(input);

        List<Integer> results = new ArrayList<>();
        if (isTest) {

            for (int i = 0; i < 100; i++) {
                results.add(computeTestIngredients(i, false));
            }

        } else {

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100 - i; j++) {
                    for (int k = 0; k < 100 - i - j; k++) {
                        results.add(computeIngredients(i, j, k, false));
                    }
                }
            }

        }
        return results.stream().max(Comparator.comparingInt(Integer::intValue)).map(String::valueOf).orElse("0");


    }


    @Override
    public String second(List<String> input) {
        parse(input);

        List<Integer> results = new ArrayList<>();
        if (isTest) {

            for (int i = 0; i < 100; i++) {
                results.add(computeTestIngredients(i, true));
            }

        } else {

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100 - i; j++) {
                    for (int k = 0; k < 100 - i - j; k++) {
                        results.add(computeIngredients(i, j, k, true));
                    }
                }
            }

        }
        return results.stream().max(Comparator.comparingInt(Integer::intValue)).map(String::valueOf).orElse("0");
    }

    private void parse(List<String> lines) {

        Pattern pattern = Pattern.compile("(\\w+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                Ingredient ingredient = new Ingredient(matcher.group(1), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
                ingredients.add(ingredient);
            }
        }
    }

    private Integer computeTestIngredients(int first, boolean checkCalories) {
        int second = 100 - first;

        int capacity = ingredients.getFirst().getCapacity() * first + ingredients.get(1).getCapacity() * second;
        int durability = ingredients.getFirst().getDurability() * first + ingredients.get(1).getDurability() * second;
        int flavor = ingredients.getFirst().getFlavor() * first + ingredients.get(1).getFlavor() * second;
        int texture = ingredients.getFirst().getTexture() * first + ingredients.get(1).getTexture() * second;
        int calories = ingredients.getFirst().getCalories() * first + ingredients.get(1).getCalories() * second;

        if (checkCalories && calories != 500) {
            return 0;
        }

        if (capacity < 0 || durability < 0 || flavor < 0 || texture < 0) {
            return 0;
        }

        return capacity * durability * flavor * texture;
    }

    private Integer computeIngredients(int first, int second, int third, boolean checkCalories) {
        int fourth = 100 - first - second - third;

        int capacity = ingredients.getFirst().getCapacity() * first + ingredients.get(1).getCapacity() * second + ingredients.get(2).getCapacity() * third + ingredients.get(3).getCapacity() * fourth;
        int durability = ingredients.getFirst().getDurability() * first + ingredients.get(1).getDurability() * second + ingredients.get(2).getDurability() * third + ingredients.get(3).getDurability() * fourth;
        int flavor = ingredients.getFirst().getFlavor() * first + ingredients.get(1).getFlavor() * second + ingredients.get(2).getFlavor() * third + ingredients.get(3).getFlavor() * fourth;
        int texture = ingredients.getFirst().getTexture() * first + ingredients.get(1).getTexture() * second + ingredients.get(2).getTexture() * third + ingredients.get(3).getTexture() * fourth;
        int calories = ingredients.getFirst().getCalories() * first + ingredients.get(1).getCalories() * second + ingredients.get(2).getCalories() * third + ingredients.get(3).getCalories() * fourth;

        if (checkCalories && calories != 500) {
            return 0;
        }

        if (capacity < 0 || durability < 0 || flavor < 0 || texture < 0) {
            return 0;
        }

        return capacity * durability * flavor * texture;
    }



}

