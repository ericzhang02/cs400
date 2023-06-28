// Name: Eric Zhang
// Email: erzhang2@wisc.edu
// Team: BI
// TA: Yuye
// Lecturer: Gary
// Notes to Grader: none
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class DataWranglerTests {
  
  /**
   * Tests a points constructor
   */
  @Test
  void testPointWithNoPaths() {
    Point testPoint = new Point("This is a test");
    assertEquals(testPoint.name, "This is a test");
    assertEquals(testPoint.paths.size(), 0);
  }
  
  /**
   * Tests a points constructor
   */
  @Test
  void testPointDistance() {
    Point testPoint = new Point("This is a test");
    PointDistance testPointDistance = new PointDistance(testPoint, (long)10, (long)5);
    assertEquals(testPointDistance.distance, (long)10);
    assertEquals(testPointDistance.otherPoint, testPoint);
    assertEquals(testPointDistance.speed, (long)5);
  }
  
  /**
   * Tests a points constructor as well as adding a connection to said point
   */
  @Test
  void testPointWithPaths() {
    Point testPoint = new Point("This is a test");
    assertEquals(testPoint.name, "This is a test");
    Point pathPoint = new Point("This is one of testPoints paths");
    assertEquals(pathPoint.name, "This is one of testPoints paths");
    testPoint.connect(pathPoint, (long) 10, (long) 100);
    assertEquals(testPoint.paths.get(0).getPoint().getName(), "This is one of testPoints paths");
    assertEquals(testPoint.paths.get(0).getDistance(), 10);
    assertEquals(testPoint.paths.get(0).getSpeed(), 100);
  }
  
  /**
   * Test the toString method of a Point when it has no connections
   */
  @Test
  void testToStringNoPaths() {
    Point testPoint = new Point("This is a test");
    assertEquals(testPoint.name, "This is a test");
    assertEquals(testPoint.toString(), "Name: \"This is a test\"\n\tNo Paths");
  }
  
  /**
   * Test the toString method of a Point when it has a connection
   */
  @Test
  void testToStringWithPaths() {
    Point testPoint = new Point("This is a test");
    assertEquals(testPoint.name, "This is a test");
    Point pathPoint = new Point("This is one of testPoints paths");
    testPoint.connect(pathPoint, (long) 10, (long) 10);
    assertEquals(testPoint.toString(), "Name: \"This is a test\"\nPaths:"
        + "\n\t\"This is one of testPoints paths\" distance of 10 and speed of 10");
  }
  
  /**
   * Test that the PointLoader loads an List of Points
   * @throws ParseException 
   * @throws IOException 
   */
  @Test
  void testPointLoader() throws IOException, ParseException {
    IPointLoader loader = new PointLoader();
    List<IPoint> points = null;
    try {
      points = loader.getPoints();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    for (int i = 0; i < points.size(); i++) {
      assertEquals(points.get(i).getClass().toString(),"class Point");

    }
    assertEquals(points.size(), 325);

  }
  
  /**
   * Test the that the PointLoader correctly loads the right points by checking the toString method
   * @throws ParseException 
   * @throws IOException 
   */
  @Test
  void testPointLoaderToString() throws IOException, ParseException {
    IPointLoader loader = new PointLoader();
    List<IPoint> points = null;
    try {
      points = loader.getPoints();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals(points.get(0).toString(), "Name: \"0 James Avenue"
        + "\"\nPaths:\n\t\"1 John Road\" distance of 2 and speed of 45"
        +"\n\t\"324 Wade Avenue\" distance of 16 and speed of 45");
    assertTrue(points.get(1).toString().contains("Name: \"1 John Road"
        + "\"\nPaths:\n\t\"0 James Avenue\" distance of 2 and speed of 45"
        +"\n\t\"2 Robert Avenue\" distance of 12 and speed of 60"
        + "\n\t\"3 Michael Street\" distance of 21 and speed of 35"
        + "\n\t\"4 William Road\" distance of 2 and speed of 60"
        + "\n\t\"324 Wade Avenue\" distance of 24 and speed of 60"));
  }
  
  /**
     * Checks the distance weight cost from the vertex "0 James Avenue" to "1 John Road"

   */
  @Test
  void testAlgorithEngineerGetDistance() throws ParseException{
    IPointLoader loader = new PointLoader();
    List<IPoint> points = null;
    try {
      points = loader.getPoints();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    streetGraph<String> graph = new streetGraph<String>();
    for (int i = 0; i < points.size(); i++) {
      graph.insertVertex(points.get(i).getName());
    }
    graph.insertEdge("0 James Avenue","1 John Road",2,45);

    assertEquals(graph.getPathCost("0 James Avenue", "1 John Road",true,x->x.getDistance()), 02);
  }  
  
  /**
     * Checks the speed weight cost from the vertex A to F.
   */
  @Test
  void testAlgorithEngineerGetSpeed() throws ParseException{
    IPointLoader loader = new PointLoader();
    List<IPoint> points = null;
    try {
      points = loader.getPoints();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    streetGraph<String> graph = new streetGraph<String>();
    for (int i = 0; i < points.size(); i++) {
      graph.insertVertex(points.get(i).getName());
    }
    graph.insertEdge("0 James Avenue","1 John Road",2,45);

    assertEquals(graph.getPathCost("0 James Avenue", "1 John Road",true,x->x.getSpeed()), 45);
  }  

}
