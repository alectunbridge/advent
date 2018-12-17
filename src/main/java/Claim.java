import java.util.Objects;

public class Claim {
    private int id;
    private int distanceFromLeft;
    private int distanceFromTop;
    private int width;
    private int height;

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    int getDistanceFromLeft() {
        return distanceFromLeft;
    }

    void setDistanceFromLeft(int distanceFromLeft) {
        this.distanceFromLeft = distanceFromLeft;
    }

    int getDistanceFromTop() {
        return distanceFromTop;
    }

    void setDistanceFromTop(int distanceFromTop) {
        this.distanceFromTop = distanceFromTop;
    }

    int getWidth() {
        return width;
    }

    void setWidth(int width) {
        this.width = width;
    }

    int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("#%d @ %d,%d: %dx%d", id, distanceFromLeft,distanceFromTop,width,height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return getId() == claim.getId() &&
                getDistanceFromLeft() == claim.getDistanceFromLeft() &&
                getDistanceFromTop() == claim.getDistanceFromTop() &&
                getWidth() == claim.getWidth() &&
                getHeight() == claim.getHeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDistanceFromLeft(), getDistanceFromTop(), getWidth(), getHeight());
    }
}
