import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DayThreeTest {

    private DayThree dayThree;

    @Before
    public void setUp() {
        dayThree = new DayThree();
    }

    @Test
    public void parseASingleClaim() {
        Claim claim = dayThree.parseClaim("#1 @ 306,433: 16x11");
        assertThat(claim.getId()).isEqualTo(1);
        assertThat(claim.getDistanceFromLeft()).isEqualTo(306);
        assertThat(claim.getDistanceFromTop()).isEqualTo(433);
        assertThat(claim.getWidth()).isEqualTo(16);
        assertThat(claim.getHeight()).isEqualTo(11);
    }

    @Test
    public void fabricSizeIsCorrect() {
        int[][] fabric = dayThree.parseClaims(new String[]{"","#1 @ 306,433: 16x11"});
        assertThat(fabric.length).isEqualTo(322);
        assertThat(fabric[0].length).isEqualTo(444);
    }

    @Test
    public void drawASingleClaim() {
        int[][] fabric = dayThree.parseClaims(new String[]{"", "#1 @ 306,433: 16x11"});
        int claimedCellCount = 0;
        for (int[] cols : fabric) {
            claimedCellCount += Arrays.stream(cols).sum();
        }
        assertThat(claimedCellCount).isEqualTo(16*11);
    }

    @Test
    public void drawOverlappingClaims() {
        int[][] fabric = dayThree.parseClaims(new String[]{"#1 @ 306,433: 16x11", "#2 @ 306,433: 1x1"});
        int claimedCellCount = 0;
        for (int[] cols : fabric) {
            claimedCellCount += Arrays.stream(cols).sum();
        }
        assertThat(claimedCellCount).isEqualTo(16*11+1);
    }

    @Test
    public void onlyCountSquareInchesOfOverlap(){
        dayThree.parseClaims(new String[]{"#1 @ 306,433: 16x11", "#2 @ 306,433: 1x1"});

        assertThat(dayThree.calculateOverlap()).isEqualTo(1);
    }

    @Test
    public void firstResult() {
        dayThree.parseClaims(Input3.STRINGS);

        assertThat(dayThree.calculateOverlap()).isEqualTo(105071);
    }

    @Test
    public void toStringForFabric() {
        dayThree.parseClaims(new String[]{"#1 @ 0,0: 2x1", "#2 @ 0,0: 1x2", "#3 @ 1,1: 1x1"});

        assertThat(dayThree.toString()).isEqualTo(
                "21" + "\n" +
                "11" + "\n" );
    }

    @Test
    public void toStringForClaim() {
        assertThat(dayThree.parseClaim("#3 @ 1,1: 1x1").toString()).isEqualTo("#3 @ 1,1: 1x1");
    }

    @Test
    public void checkClaimForNoOverlap() {
        dayThree.parseClaims(new String[]{"#1 @ 0,0: 2x1", "#2 @ 0,0: 1x2", "#3 @ 1,1: 1x1"});

        assertThat(dayThree.checkClaimForNoOverlap(dayThree.parseClaim("#3 @ 1,1: 1x1"))).isTrue();
    }

    @Test
    public void secondResult() {
        dayThree.parseClaims(Input3.STRINGS);
        Claim claim = dayThree.findClaimWithNoOverLap();
        assertThat(claim).isEqualTo(dayThree.parseClaim("#222 @ 359,844: 13x13"));
    }
}
