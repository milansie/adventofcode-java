package y2015.d2;

import common.AdventDay;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D2 extends AdventDay {

    public static void main(String[] argv) {
        new D2().run();
    }

    @Override
    public String first(List<String> input) {
        int result = 0;

        for (String dimension : input) {
            Pattern pattern = Pattern.compile("([0-9]+)x([0-9]+)x([0-9]+)");
            Matcher matcher = pattern.matcher(dimension);
            if (matcher.find()) {
                int l = Integer.parseInt(matcher.group(1));
                int w = Integer.parseInt(matcher.group(2));
                int h = Integer.parseInt(matcher.group(3));
                int lw = l * w;
                int wh = w * h;
                int hl = h * l;
                int smallest = Math.min(lw, Math.min(wh, hl));
                result += 2 * lw + 2 * wh + 2 * hl + smallest;
            }
        }

        return result + "";
    }

    @Override
    public String second(List<String> input) {
        int result = 0;

        for (String dimension : input) {
            Pattern pattern = Pattern.compile("([0-9]+)x([0-9]+)x([0-9]+)");
            Matcher matcher = pattern.matcher(dimension);
            if (matcher.find()) {
                int l = Integer.parseInt(matcher.group(1));
                int w = Integer.parseInt(matcher.group(2));
                int h = Integer.parseInt(matcher.group(3));

                int first = Math.min(Math.min(l,w),h);
                int second = Math.max(Math.min(l,w), Math.min(Math.max(l,w),h));

                int ribbon = 2 * first + 2 * second;
                int bow = l * w * h;

                result += ribbon + bow;
            }
        }

        return result + "";
    }
}
