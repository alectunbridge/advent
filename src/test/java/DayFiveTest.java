import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DayFiveTest {

    private DayFive dayFive;

    @Before
    public void setUp() {
        dayFive = new DayFive();
    }

    @Test
    public void testSimpleReactionLowerThenUpper() {
        assertThat(dayFive.react(new StringBuilder("aA"))).isEqualTo(true);
    }

    @Test
    public void testSimpleReactionUpperThenLower() {
        assertThat(dayFive.react(new StringBuilder("Aa"))).isEqualTo(true);
    }

    @Test
    public void reactFully() {
        assertThat(dayFive.reactFully(new StringBuilder("dabAcCaCBAcCcaDA"))).hasSize(10);
    }

    @Test
    public void sameLettersArentReacted() {
        assertThat(dayFive.reactFully(new StringBuilder("Ccaa"))).hasSize(2);
    }

    @Test
    public void firstSolution() {
        assertThat(dayFive.reactFully(new StringBuilder(Input5.STRING))).hasSize(
                9238
        );
    }

    @Test
    public void removeAllOfAGivenCharacter(){
        assertThat(dayFive.removeAll("Aa",'a')).isEqualTo("");
    }

    @Test
    public void secondSolution() {
        int shortestChain = Integer.MAX_VALUE;
        for(char c='a'; c<='z'; c++){
            String strippedChain = dayFive.removeAll(Input5.STRING,c);
            String result = dayFive.reactFully(new StringBuilder(strippedChain));
            if(result.length()<shortestChain){
                shortestChain = result.length();
            }
        }
        assertThat(shortestChain).isEqualTo(0);
    }
}
