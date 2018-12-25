import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.*;

class DaySix {

    private int[][] grid;
    private Character[][] owners;
    private Coordinate[] coords;
    private final int maxX;
    private final int maxY;
    private Set<Character> infiniteAreas;

    DaySix(Coordinate[] coords) {
        char lastChar = 'A';
        for (Coordinate coord : coords) {
            coord.setName(lastChar++);
        }
        this.coords = coords;
        maxX = Arrays.stream(coords).mapToInt(Coordinate::getX).max().orElse(0);
        maxY = Arrays.stream(coords).mapToInt(Coordinate::getY).max().orElse(0);

        grid = new int[maxX + 1][maxY + 1];
        owners = new Character[maxX + 1][maxY + 1];

        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[j][i] = Integer.MAX_VALUE;
            }
        }
    }

    String calculateOwners() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                for (Coordinate coord : coords) {
                    if (coord.getManahttanDistance(j, i) < grid[j][i]) {
                        grid[j][i] = coord.getManahttanDistance(j, i);
                        owners[j][i] = coord.getName();
                    } else if (coord.getManahttanDistance(j, i) == grid[j][i]) {
                        owners[j][i] = ' ';
                    }
                }
            }
        }

        for (int i = 0; i < owners[0].length; i++) {
            for (int j = 0; j < owners.length; j++) {
                result.append(String.format("%c", owners[j][i]));
            }
            result.append("\n");
        }
        return result.toString();
    }

    void calculateManhattanDistances() {
        for (Coordinate coord : coords) {
            int[][] manhattan = new int[maxX + 1][maxY + 1];

            for (int i = 0; i < grid[0].length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    manhattan[j][i] = Math.abs(coord.getX() - j) + Math.abs(coord.getY() - i);
                    coord.setManhattanDistances(manhattan);
                }
            }
        }
    }

    Set<Character> getInfiniteAreas() {
        HashSet<Character> result = new HashSet<>();
        for (int y = 0; y < maxY; y++) {
            if (owners[0][y] != ' ') {
                result.add(owners[0][y]);
            }
            if (owners[maxX][y] != ' ') {
                result.add(owners[maxX][y]);
            }

        }
        for (int x = 0; x < maxX; x++) {
            if (owners[x][0] != ' ') {
                result.add(owners[x][0]);
            }
            if (owners[x][maxY] != ' ') {
                result.add(owners[x][maxY]);
            }
        }
        infiniteAreas = result;
        return result;
    }

    int getFirstSolution() {
        return Arrays.stream(owners).flatMap(Arrays::stream)
                .filter(name -> !infiniteAreas.contains(name))
                .collect(groupingBy(name -> name, counting()))
                .values().stream().max(Long::compare)
                .map(Math::toIntExact).orElse(0);
    }

    int getSecondSolution(int maxSumOfDistances) {
        int[][] sumOfDistances = new int[maxX + 1][maxY + 1];
        for (int y = 0; y < maxY + 1; y++) {
            for (int x = 0; x < maxX + 1; x++) {
                for (Coordinate coord : coords) {
                    int distance = Math.abs(coord.getX() - x) + Math.abs(coord.getY() - y);
                    sumOfDistances[x][y] += distance;
                }
            }
        }

        for (int y = 0; y < maxY + 1; y++) {
            for (int x = 0; x < maxX + 1; x++) {
                if(sumOfDistances[x][y] < maxSumOfDistances){
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        return (int) Arrays.stream(sumOfDistances).flatMapToInt(Arrays::stream).filter(sum->sum<maxSumOfDistances).count();
    }
}
