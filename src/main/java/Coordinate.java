class Coordinate {
    static char lastName = 'A';
    private final char name;
    private final int x;
    private final int y;
    private int[][] manhattanDistances;

    Coordinate(int x, int y) {
        this.name = lastName++;
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

    void setManhattanDistances(int[][] manhattanDistances) {
        this.manhattanDistances = manhattanDistances;
    }

    int getManahttanDistance(int j, int i) {
        return manhattanDistances[j][i];
    }
}
