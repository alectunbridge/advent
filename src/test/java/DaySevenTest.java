import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DaySevenTest {


    @Test
    public void buildAdjacencyMatrix() {
        DaySeven daySeven = new DaySeven("Step A must be finished before step B can begin.");
        assertThat(daySeven.getAdjacencyMatrix()).containsExactly(
                new int[][]{
                        {0, 1},
                        {0, 0}
                }
        );
        System.out.println(daySeven);
    }

    @Test
    public void printAdjMatrix() {
        DaySeven daySeven = new DaySeven(Input7.INSTRUCTIONS);
        System.out.println(daySeven);
    }

    @Test
    public void getFirstNode() {
        DaySeven daySeven = new DaySeven(Input7.INSTRUCTIONS);
        assertThat(daySeven.getFirstSolution()).startsWith("IJLF");
    }

    @Test
    public void multipleParents() {
        DaySeven daySeven = new DaySeven(
                "Step A must be finished before step B can begin.",
                "Step C must be finished before step A can begin.",
                "Step C must be finished before step D can begin.",
                "Step D must be finished before step B can begin."
        );
        assertThat(daySeven.getAdjacencyMatrix()).containsExactly(
                new int[][]{
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                        {1, 0, 0, 1},
                        {0, 1, 0, 0},

                }
        );
        assertThat(daySeven.getFirstSolution()).isEqualTo("CADB");
    }

    @Test
    public void exampleSolution() {
        DaySeven daySeven = new DaySeven(
                "Step C must be finished before step A can begin.",
                "Step C must be finished before step F can begin.",
                "Step A must be finished before step B can begin.",
                "Step A must be finished before step D can begin.",
                "Step B must be finished before step E can begin.",
                "Step D must be finished before step E can begin.",
                "Step F must be finished before step E can begin."
        );

        assertThat(daySeven.getFirstSolution()).isEqualTo("CABDFE");
    }

    @Test
    public void secondSolution() {
        DaySeven daySeven = new DaySeven(
            Input7.INSTRUCTIONS
        );
        assertThat(daySeven.getSecondSolution()).isEqualTo(1072);
    }


}
