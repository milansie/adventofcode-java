package y2015.d4;

import common.AdventDay;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;

public class D4 extends AdventDay {

    public static void main(String[] argv) {
        new D4().run();
    }

    @Override
    public String first(List<String> input) {
        StringBuilder result = new StringBuilder();

        for (String key : input) {


            int res = countMD5(key, 5);
            if (!result.isEmpty()) {
                result.append(",");
            }
            result.append(res);

        }

        return result.toString();
    }

    private int countMD5(String key, int zeroes) {

        StringBuilder zeroesString = new StringBuilder();
        zeroesString.append("0".repeat(Math.max(0, zeroes)));

        int result = 0;
        while (true) {
            String hash = DigestUtils.md5Hex(key + result);
            if (hash.startsWith(zeroesString.toString())) {
                return result;
            }
            result++;
        }
    }


    @Override
    public String second(List<String> input) {
        StringBuilder result = new StringBuilder();

        for (String key : input) {


            int res = countMD5(key, 6);
            if (!result.isEmpty()) {
                result.append(",");
            }
            result.append(res);

        }

        return result.toString();
    }
}
