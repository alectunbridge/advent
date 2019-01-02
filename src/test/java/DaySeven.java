import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class DaySeven {
    private int[][] adjacencyMatrix;
    private int[][] drawnNodes;
    private Worker[] workers;

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
        workers = new Worker[]{new Worker(), new Worker(), new Worker(), new Worker(), new Worker()};

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
                if (result.indexOf("" + endNode) == -1 && getNoOfParents(endNode) == 0) {
                    result.append(endNode);
                    for (int i = 0; i < adjacencyMatrix.length; i++) {
                        drawnNodes[endNode - 'A'][i] = -adjacencyMatrix[endNode - 'A'][i];
                    }
                    break;
                }
            }
        }

        return result.toString();
    }

    int getSecondSolution() {
        int secondNo = 0;
        StringBuilder assignedNodes = new StringBuilder();
        do {
            for (char endNode = 'A'; endNode < (adjacencyMatrix.length + 'A'); endNode++) {
                if (assignedNodes.indexOf("" + endNode) == -1 && getNoOfParents(endNode) == 0) {
                    if (assignNodeToWorker(endNode)) {
                        assignedNodes.append(endNode);
                    }
                }
            }
            for (Worker worker : workers) {
                char completedNode = worker.doWork();
                if (!(Character.MIN_VALUE == completedNode)) {
                    //mark node as drawn
                    for (int i = 0; i < adjacencyMatrix.length; i++) {
                        drawnNodes[completedNode - 'A'][i] = -adjacencyMatrix[completedNode - 'A'][i];
                    }
                }
            }
            secondNo++;
        } while (assignedNodes.length() != adjacencyMatrix.length || workersAreBusy());

        return secondNo;
    }

    private boolean workersAreBusy() {
        for (Worker worker : workers) {
            if (worker.isBusy()) {
                return true;
            }
        }
        return false;
    }

    private boolean assignNodeToWorker(char node) {
        for (Worker worker : workers) {
            if (worker.assignNode(node)) {
                return true;
            }
        }
        return false;
    }


    private int getNoOfParents(char node) {
        int noOfParents = 0;
        for (int startNode = 0; startNode < adjacencyMatrix.length; startNode++) {
            noOfParents += (adjacencyMatrix[startNode][node - 'A'] + drawnNodes[startNode][node - 'A']);
        }
        return noOfParents;
    }

}
