import java.util.LinkedList;

/**
 * Vertex objects group a data field with an adjacency list of weighted
 * directed edges that lead away from them.
*/
public class Vertex<T> {
    public T data; // vertex label or application specific data
    public LinkedList<Edge<T>> edgesLeaving;

    public Vertex(T data) {
        this.data = data;
        this.edgesLeaving = new LinkedList<>();
    }
}
