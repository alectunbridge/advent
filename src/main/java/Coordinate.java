class Coordinate {
    private final int x;
    private final int y;
    private char name;
    private int[][] manhattanDistances;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    char getName() {
        return name;
    }

    void setName(char name) {
        this.name = name;
    }

    void setManhattanDistances(int[][] manhattanDistances) {
        this.manhattanDistances = manhattanDistances;
    }

    public int[][] getManhattanDistances() {
        return manhattanDistances;
    }

    int getManahttanDistance(int j, int i) {
        return manhattanDistances[j][i];
    }
}
