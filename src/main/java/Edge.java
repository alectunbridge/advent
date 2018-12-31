import java.util.Objects;

class Edge {
    private final char start;
    private final char end;

    Edge(char start, char end) {
        this.start = start;
        this.end = end;
    }

    char getStart() {
        return start;
    }

    char getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return start == edge.start &&
                end == edge.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
