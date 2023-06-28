// --== CS400 File Header Information ==--
// Name: Eric Leonard
// Email: ecleonard@wisc.edu
// Team: BI
// TA: Yuye
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

public class AlgorithmEngineerTests {

    private streetGraph<String> graph;

    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new streetGraph<String>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6,60);
        graph.insertEdge("A","C",2,20);
        graph.insertEdge("A","D",5,50);
        graph.insertEdge("B","E",1,10);
        graph.insertEdge("B","C",2,20);
        graph.insertEdge("C","B",3,30);
        graph.insertEdge("C","F",1,20);
        graph.insertEdge("D","E",3,10);
        graph.insertEdge("E","A",4,50);
        graph.insertEdge("F","A",1,20);
        graph.insertEdge("F","D",1,60);
    }

    /**
     * Checks the distance weight cost from the vertex A to F.
     */
    @Test
    public void testPathCostAtoF() {
        assertEquals(graph.getPathCost("A", "F",true,x->x.getDistance()), 03);
    }

    /**
     * Checks the speed weight cost from the vertex A to D.
     */
    @Test
    public void testPathCostAtoD() {
        assertEquals(graph.getPathCost("A", "D", true, x-> x.getSpeed()), 50);
    }
    
    /**
     * Checks an exception is thrown if the only path to a vertex
     * contains a highway and allowHighways is set to false
     */
    @Test
    public void testNoHighways() {
        graph.insertVertex("HighWay Only");
        graph.insertEdge("A", "HighWay Only", 5, 70);
        try {
            graph.shortestPath("A", "HighWayOnly", false, x->x.getDistance());
        } catch (NoSuchElementException e) {
            assertEquals(e.getMessage(), "No path found between vertices");
        }
    }

    /**
     * Checks that a different path is taken even if a shorter path exists
     * if that path contains a highway and allow highways is false
     */
    @Test
    public void testIgnoreHighway() {
        graph.insertVertex("Shorter By Highway");
        graph.insertVertex("in between stop");
        graph.insertEdge("A", "Shorter By Highway", 5, 70);
        graph.insertEdge("A", "in between stop", 5, 35);
        graph.insertEdge("in between stop","Shorter By Highway", 5, 35);
        try {
            graph.shortestPath("A", "HighWayOnly", false, x->x.getDistance());
        } catch (NoSuchElementException e) {
            assertEquals(e.getMessage(), "No path found between vertices");
        }
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex
     * A to F.
     */
    @Test
    public void testPathAtoF() {
        assertTrue(graph.shortestPath("A", "F", false, x->x.getDistance()).toString().equals(
            "[A, C, F]"
        ));
    }
}
