import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DaySixTest {

    @Test
    public void printCoords() {
        Coordinate[] coords = {
                new Coordinate(1, 1),
                new Coordinate(1, 6),
                new Coordinate(8, 3),
                new Coordinate(3, 4),
                new Coordinate(5, 5),
                new Coordinate(8, 9)
        };
        DaySix daySix = new DaySix(coords);

        daySix.calculateManhattanDistances();
        assertThat(daySix.calculateOwners()).isEqualTo(
                "AAAAA CCC\n" +
                "AAAAA CCC\n" +
                "AAADDECCC\n" +
                "AADDDECCC\n" +
                "  DDDEECC\n" +
                "BB DEEEEC\n" +
                "BBB EEEE \n" +
                "BBB EEEFF\n" +
                "BBB EEFFF\n" +
                "BBB FFFFF\n"
        );
    }

    @Test
    public void manhanttanDistance() {
        DaySix daySix = new DaySix(
                new Coordinate[]{
                        new Coordinate(0, 0),
                        new Coordinate(2, 2),
                        new Coordinate(1, 1)});
        daySix.calculateManhattanDistances();

        assertThat(daySix.calculateOwners()).isEqualTo(
                "A  \n" +
                " C \n" +
                "  B\n"
        );
    }

    @Test
    public void excludeInfiniteAreas() {
        Coordinate[] coords = {
                new Coordinate(1, 1),
                new Coordinate(1, 6),
                new Coordinate(8, 3),
                new Coordinate(3, 4),
                new Coordinate(5, 5),
                new Coordinate(8, 9)
        };
        DaySix daySix = new DaySix(coords);

        daySix.calculateManhattanDistances();
        daySix.calculateOwners();

        assertThat(daySix.getInfiniteAreas()).containsOnly('A', 'B', 'C', 'F');

        assertThat(daySix.getFirstSolution()).isEqualTo(17);
    }

    @Test
    public void getFirstSolution() {
        DaySix daySix = new DaySix(Input6.COORDS);

        daySix.calculateManhattanDistances();
        daySix.calculateOwners();
        daySix.getInfiniteAreas();

        assertThat(daySix.getFirstSolution()).isEqualTo(4475);
    }
}