// --== CS400 File Header Information ==-- 
// Name: Eric Zhang
// Email: erzhang2@wisc.edu 
// Team: BI 
// TA: Yuye 
// Lecturer: Gary 
// Notes to Grader: none
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.NoSuchElementException;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

  private CS400Graph<String> graph;

  /**
   * Instantiate graph from last week's shortest path activity.
   */
  @BeforeEach
  public void createGraph() {
    graph = new CS400Graph<>();
    // insert vertices A-F
    graph.insertVertex("A");
    graph.insertVertex("B");
    graph.insertVertex("C");
    graph.insertVertex("D");
    graph.insertVertex("E");
    graph.insertVertex("F");
    // insert edges from Week 11. Shortest Path Activity
    graph.insertEdge("A","B",6);
    graph.insertEdge("A","C",2);
    graph.insertEdge("A","D",5);
    graph.insertEdge("B","E",1);
    graph.insertEdge("B","C",2);
    graph.insertEdge("C","B",3);
    graph.insertEdge("C","F",1);
    graph.insertEdge("D","E",3);
    graph.insertEdge("E","A",4);
    graph.insertEdge("F","A",1);
    graph.insertEdge("F","D",1);
  }

  /**
   * Checks the distance/total weight cost from the vertex A to F.
   */
  @Test
  public void testPathCostAtoF() {
    assertTrue(graph.getPathCost("A", "F") == 3);
  }

  /**
   * Checks the distance/total weight cost from the vertex A to D.
   */
  @Test
  public void testPathCostAtoD() {
    assertTrue(graph.getPathCost("A", "D") == 4);
  }

  /**
   * Checks the ordered sequence of data within vertices from the vertex 
   * A to D.
   */
  @Test
  public void testPathAtoD() {
    assertTrue(graph.shortestPath("A", "D").toString().equals(
        "[A, C, F, D]"
        ));
  }

  /**
   * Checks the ordered sequence of data within vertices from the vertex 
   * A to F.
   */
  @Test
  public void testPathAtoF() {
    assertTrue(graph.shortestPath("A", "F").toString().equals(
        "[A, C, F]"
        ));
  }

  /**
   * Checks the distance/total weight cost from the vertex E to F.
   */
  @Test
  public void testPathCostEtoF() {
    assertTrue(graph.getPathCost("E", "F") == 7);
  }

  /**
   * Checks the ordered sequence of data within vertices from the vertex 
   * E to F.
   */
  @Test
  public void testPathEtoF() {
    assertTrue(graph.shortestPath("E", "F").toString().equals(
        "[E, A, C, F]"
        ));
  }

  /**
   * Checks the distance/total weight cost from the vertex A to E.
   */
  @Test
  public void testPathCostAtoE() {
    assertTrue(graph.getPathCost("A", "E") == 6);
  }

  /**
   * Checks the ordered sequence of data within vertices from the vertex 
   * A to E.
   */
  @Test
  public void testPathAtoE() {
    assertTrue(graph.shortestPath("A", "E").toString().equals(
        "[A, C, B, E]"
        ));
  }
  /**
   * Checks that an exception is thrown when the given Start is not valid
   */
  @Test
  public void testExceptionStart() {
    NoSuchElementException exception = assertThrows(NoSuchElementException.class,
        () -> graph.getPathCost("MISINPUT", "E"));

    String expectedMessage = "Path from start to end could not be found";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Checks that an exception is thrown when the given End is not valid
   */
  @Test
  public void testExceptionEnd() {
    NoSuchElementException exception = assertThrows(NoSuchElementException.class,
        () -> graph.getPathCost("A", "MISINPUT"));

    String expectedMessage = "Path from start to end could not be found";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}