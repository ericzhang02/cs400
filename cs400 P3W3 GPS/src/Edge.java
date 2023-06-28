public class Edge<T> {
    public Vertex<T> target;
    public int distance;
    public int speed;
    public boolean isHighway;

    public Edge(Vertex<T> target, int distance, int speed) {
        this.target = target;
        this.distance = distance;
        this.speed = speed;
        this.isHighway = speed >= 55;
    }

    public int getDistance() { return this.distance; }
    public int getSpeed() { return this.speed; }
    public boolean getIsHighway() { return this.isHighway; }
}
