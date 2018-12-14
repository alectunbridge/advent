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

    public int count(String[] input) {
        return count(input, 2) * count(input, 3);
    }
}
