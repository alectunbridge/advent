import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DayTwoTest {

    private DayTwo dayTwo;

    @Before
    public void setUp() {
        dayTwo = new DayTwo();
    }

    @Test
    public void countLetter() {
        assertThat(dayTwo.count("input aa", 'a')).isEqualTo(2);
    }

    @Test
    public void countEachLetterOfTheAlphabet() {
        int[] counts = new int[26];
        String input = "abcdefghijklmnopqrstuvwxyz";
        for(char character ='a'; character<='z'; character++ ){
            counts[character-'a'] = dayTwo.count(input, character);
        }

        assertThat(counts).containsOnly(1);
    }

    @Test
    public void containsLetterTwice() {
        assertThat(dayTwo.containsGivenNumberOfOccurences("bb", 2)).isTrue();
    }

    @Test
    public void containsLetterThrice() {
        assertThat(dayTwo.containsGivenNumberOfOccurences("bbb", 3)).isTrue();
    }

    @Test
    public void getsCountOfDoubleCharacters() {
        assertThat(dayTwo.count(new String[] {"aa", "bb", "ccc"}, 2)).isEqualTo(2);
    }

    @Test
    public void getsCountOfTripleCharacters() {
        assertThat(dayTwo.count(new String[] {"a", "bbba", "c"}, 3)).isEqualTo(1);
    }

    @Test
    public void getsCountOfDoubleCharactersMultipliedByCountOfTripleCharacters() {
        assertThat(dayTwo.count(new String[] {"aa", "aa", "bbb", "bbb"})).isEqualTo(4);
    }

    @Test
    public void firstSolution(){
        assertThat(dayTwo.count(Input2.STRINGS)).isEqualTo(8118);
    }

    @Test
    public void compareTwoStringForOneLetterDifferent() {
        assertThat(dayTwo.oneCharacterDifferent("bbc","abc")).isEqualTo("bc");
        assertThat(dayTwo.oneCharacterDifferent("bbc","bbc")).isEqualTo("");
    }

    @Test
    public void secondSolution() {
        for (int i = 0; i < Input2.STRINGS.length/2; i++) {
            for(int j = 0; j < Input2.STRINGS.length; j++ )    {
                if(i!=j){
                    String commmonChars = dayTwo.oneCharacterDifferent(Input2.STRINGS[i], Input2.STRINGS[j]);
                    if(!"".equals(commmonChars)){
                        System.out.println(commmonChars);
                        break;
                    }
                }
            }
        }
    }
}
