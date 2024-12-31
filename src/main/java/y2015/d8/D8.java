package y2015.d8;

import common.AdventDay;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D8 extends AdventDay {

    public static void main(String[] argv) {
        new D8().run();
    }

    public static class StringValue {
        String value;
        int length;
        int realLength;
        int encodedLength;
        String strippedValue;
        String encodedValue;

        public StringValue(String value) {
            this.value = value;
            this.length = value.length();
            this.realLength = computeRealLength();
            this.encodedLength = computeEncodedLength();
        }

        private int computeEncodedLength() {

            encodedValue = value;

            encodedValue = encodedValue.replace("\\", "\\\\");
            encodedValue = encodedValue.replace("\"", "\\\"");

            encodedValue = "\"" + encodedValue + "\"";
            return encodedValue.length();
        }

        private int computeRealLength() {

            strippedValue = value.substring(1, value.length() - 1);

            strippedValue = strippedValue.replace("\\\"", "\"");
            strippedValue = strippedValue.replace("\\\\", "\\");

            Pattern pattern = Pattern.compile("\\\\x([0-9a-fA-F]{2})");
            Matcher matcher = pattern.matcher(strippedValue);
            StringBuilder result = new StringBuilder();

            while (matcher.find()) {
                String hex = matcher.group(1);
                char c = (char) Integer.parseInt(hex, 16);
                String replacement = String.valueOf(c);

                if (c == '\\' || c == '$' || c == '(' || c == ')' || c == '[' || c == ']' ||
                        c == '{' || c == '}' || c == '|' || c == '.' || c == '?' || c == '*' ||
                        c == '+' || c == '^') {
                    replacement = "\\" + c;
                }                matcher.appendReplacement(result, replacement);
            }
            matcher.appendTail(result);
            strippedValue = result.toString();

            return strippedValue.length();
        }

        public String toString() {
            return value + " (" + strippedValue + ", " + encodedValue + ") " + length + " " + realLength + " " + encodedLength;
        }

    }

    @Override
    public String first(List<String> input) {

        List<StringValue> stringValues = new ArrayList<>();
        for (String line : input) {
            stringValues.add(new StringValue(line));
        }

        int totalLength = stringValues.stream().mapToInt(sv -> sv.length - sv.realLength).sum();

        return totalLength + "";
    }

    @Override
    public String second(List<String> input) {
        List<StringValue> stringValues = new ArrayList<>();
        for (String line : input) {
            stringValues.add(new StringValue(line));
        }

        int totalLength = stringValues.stream().mapToInt(sv -> sv.encodedLength - sv.length).sum();
        return totalLength + "";
    }

}
