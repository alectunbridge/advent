import static java.lang.Character.*;

class DayFive {
    boolean react(StringBuilder input) {
        for (int i = 0; i + 1 < input.length(); i++) {
            boolean charactersAreTheSame = input.charAt(i) == input.charAt(i+1);
            boolean lowerThenUpper =
                    toUpperCase(input.charAt(i)) == input.charAt(i + 1);
            boolean upperThenLower = input.charAt(i) ==
                    toUpperCase(input.charAt(i + 1));
            if (!charactersAreTheSame && (lowerThenUpper || upperThenLower)) {
                input.replace(i,i+2,"");
                return true;
            }
        }
        return false;
    }

    String reactFully(StringBuilder input) {
        while (react(input)) {

        }
        return input.toString();
    }

    String removeAll(String input, char character) {
        return input.replaceAll("["+character+toUpperCase(character)+"]", "");
    }
}
