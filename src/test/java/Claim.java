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

    public int getDistanceFromTop() {
        return distanceFromTop;
    }

    public void setDistanceFromTop(int distanceFromTop) {
        this.distanceFromTop = distanceFromTop;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
