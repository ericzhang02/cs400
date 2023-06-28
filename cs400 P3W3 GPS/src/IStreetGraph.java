import java.util.List;



public interface IStreetGraph<T> {

    interface streetAttribute {
        public abstract int get(Edge x);
    }

    /**
     * Insert a new vertex into the graph.
     *
     * @param name the data item stored in the new vertex
     * @return true if the data can be inserted as a new vertex, false if it is already in the graph
     * @throws NullPointerException if data is null
     */
    public boolean insertVertex(T data);


    /**
     * Remove a vertex from the graph.
     * Also removes all edges adjacent to the vertex from the graph (all edges that have the vertex as a source or a destination vertex).
     *
     * @param data the data item stored in the vertex to remove
     * @return true if a vertex with *data* has been removed, false if it was not in the graph
     * @throws NullPointerException if data is null
     */
    public boolean removeVertex(T data);

    /**
     * Insert a new directed edge with a positive edge weight into the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @param weight the weight for the edge (has to be a positive integer)
     * @return true if the edge could be inserted or its weight updated, false
     *     if the edge with the same weight was already in the graph
     * @throws IllegalArgumentException if either source or target or both are not in the graph,
     *     or if its weight is < 0
     * @throws NullPointerException if either source or target or both are null
     */
    public boolean insertEdge(T source, T target, int distance, int speed);

    /**
     * Remove an edge from the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge could be removed, false if it was not in the graph
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     */
    public boolean removeEdge(T source, T target);

    /**
     * Check if the graph contains a vertex with data item *data*.
     *
     * @param v the data item to check check for
     * @return true if data item is stored in a vertex of the graph, false otherwise
     * @throws NullPointerException if *data* is null
     */
    public boolean containsVertex(T data);

    /**
     * Check if edge is in the graph.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge is in the graph, false if it is not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     */
    public boolean containsEdge(T source, T target);

    /**
     * Return the weight of an edge.
     *
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return the weight of the edge (0 or positive integer)
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     * @throws NoSuchElementException if edge is not in the graph
     */
    public int getWeight(T source, T target, streetAttribute getter);

    /**
     * Returns the shortest path between startingVertex and destinationVertex.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     *
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @param allowHighways boolean flag to mark if the shortestpath should contain highways
     * @param getter Lambda expression that dictates what field in the edge class the shortest path should use as a weight
     * @return list of data item in vertices in order on the shortest path between vertex with data item startingVertex and vertex with data item destinationVertex, including both startingVertex and destinationVertex
     */
    public List<T> shortestPath(T start, T end, boolean allowHighways, streetAttribute getter);

    /**
    * @param start
    * @param end
    * @param allowHighways
    * @param getter
    *
    * Calculates path cost of the shortest path in the map between two points. Bassed on the
    * streetAttribut found using the lambda expression getter. Allows for paths that
    * contain no highways through use of boolean flag allowHighways.
    *
    * @return Array[]<IPoint> a list of the points that are visited when going from point 1 to point 2
    *  in the shortest possible path
    */
    public int getPathCost(T start, T end, boolean allowHighways, streetAttribute getter);

        /**
     * Check if the graph is empty (does not contain any vertices or edges).
     *
     * @return true if the graph does not contain any vertices or edges, false otherwise
     */
    public boolean isEmpty();

    /**
     * Return the number of edges in the graph.
     *
     * @return the number of edges in the graph
     */
    public int getEdgeCount();

    /**
     * Return the number of vertices in the graph
     *
     * @return the number of vertices in the graph
     */
    public int getVertexCount();
}
