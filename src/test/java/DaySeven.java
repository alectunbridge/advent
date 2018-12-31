import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class DaySeven {
    private int[][] adjacencyMatrix;
    private int[][] drawnNodes;

    DaySeven(String... input) {
        List<Edge> edges = new ArrayList<>();
        for (String line : input) {
            edges.add(new Edge(line.charAt(5), line.charAt(36)));
        }

        int numberOfStartNodes = edges.stream().mapToInt(edge -> edge.getStart() - 'A' + 1).max().orElse(0);
        int numberOfEndNodes = edges.stream().mapToInt(edge -> edge.getEnd() - 'A' + 1).max().orElse(0);
        int size = numberOfStartNodes > numberOfEndNodes ? numberOfStartNodes : numberOfEndNodes;
        adjacencyMatrix = new int[size][size];
        drawnNodes = new int[size][size];

        edges.stream().forEach(edge -> adjacencyMatrix[edge.getStart() - 'A'][edge.getEnd() - 'A'] = 1);

    }

    int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(" ");
        IntStream.range('A', 'A' + adjacencyMatrix.length).forEach(
                letter -> result.append((char) letter));
        result.append("\n");

        for (int row = 0; row < adjacencyMatrix.length; row++) {
            result.append((char) ('A' + row));
            for (int column = 0; column < adjacencyMatrix.length; column++) {
                result.append(adjacencyMatrix[row][column]);
            }
            result.append("\n");

        }

        return result.toString();
    }

    String getFirstSolution() {
        StringBuilder result = new StringBuilder();
        while (result.length() != adjacencyMatrix.length) {
            for (char endNode = 'A'; endNode < (adjacencyMatrix.length + 'A'); endNode++) {
                if (result.indexOf(""+endNode)==-1 && getNoOfParents(endNode) == 0) {
                    result.append(endNode);
                    for (int i = 0; i < adjacencyMatrix.length; i++) {
                        drawnNodes[endNode - 'A'][i] = -adjacencyMatrix[endNode-'A'][i];
                    }
                    break;
                }
            }
        }

        return result.toString();
    }

    private int getNoOfParents(char node) {
        int noOfParents = 0;
        for (int startNode = 0; startNode < adjacencyMatrix.length; startNode++) {
            noOfParents += (adjacencyMatrix[startNode][node - 'A'] + drawnNodes[startNode][node - 'A']);
        }
        return noOfParents;
    }
}
