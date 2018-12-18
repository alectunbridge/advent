import java.util.Arrays;

class DayTwo {
    int count(String input, char character) {
        return (int) input.chars().filter(c->c == character).count();
    }

    boolean containsGivenNumberOfOccurences(String input, int noOfOccurences) {
        int[] counts = new int[26];
        for(char character ='a'; character<='z'; character++ ){
            counts[character-'a'] = count(input, character);
        }

        for(int count:counts){
            if(count == noOfOccurences){
                return true;
            }
        }
        return false;
    }

    int count(String[] input, int noOfOccurences) {
        return (int) Arrays.stream(input).filter(s->containsGivenNumberOfOccurences(s, noOfOccurences)).count();
    }

    int count(String[] input) {
        return count(input, 2) * count(input, 3);
    }

    String oneCharacterDifferent(String string1, String string2) {
        String result = "";
        boolean foundOneDifference = false;
        for (int i = 0; i < string1.length(); i++) {
            if(string1.charAt(i) != string2.charAt(i)){
                if (foundOneDifference){
                    return "";
                } else {
                    foundOneDifference = true;
                    result = string1.substring(0,i) + string1.substring(i+1);
                }
            }
        }
        return result;
    }
}
