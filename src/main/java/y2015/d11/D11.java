package y2015.d11;

import common.AdventDay;

import java.util.List;

public class D11 extends AdventDay {

    public static void main(String[] argv) {
        new D11().run();
    }

    @Override
    public String first(List<String> input) {

        String password = input.getFirst();

        while (!isValidPassword(password)) {
            password = nextPassword(password);
        }

        return password;

    }

    @Override
    public String second(List<String> input) {

        String password = input.getFirst();
        boolean first = false;

        while (true) {
            password = nextPassword(password);
            if (isValidPassword(password)) {
               if (!first) {
                   first = true;
               } else {
                   break;
               }
            }
        }

        return password;
    }


    private String nextPassword(String password) {
        StringBuilder sb = new StringBuilder(password);
        int i = sb.length() - 1;

        while (i >= 0) {
            char currentChar = sb.charAt(i);
            if (currentChar == 'z') {
                sb.setCharAt(i, 'a');
                i--;
            } else {
                sb.setCharAt(i, (char) (currentChar + 1));
                break;
            }
        }

        return sb.toString();
    }

    private boolean isValidPassword(String password) {

        String firstPair = null;
        String secondPair = null;
        boolean straight = false;

        if (password.contains("i") || password.contains("o") || password.contains("l")) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {

            char current_char = password.charAt(i);

            if (i >= 1 && password.charAt(i-1) == current_char) {
                if (firstPair != null && !firstPair.equals(password.charAt(i - 1) + "" + current_char)) {
                    secondPair = password.charAt(i - 1) + "" + current_char;
                } else {
                    firstPair = password.charAt(i - 1) + "" + current_char;
                }
            }

            if (i >= 2 && !straight) {

                char char1 = password.charAt(i-1);
                char char2 = password.charAt(i-2);

                straight = (current_char == char1+1 && current_char == char2+2);

            }


            if ( straight && firstPair != null && secondPair != null) {
               return true;
            }

        }

        return false;
    }

}

