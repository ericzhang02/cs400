// Name: Eric Zhang
// Email: erzhang2@wisc.edu
// Team: BI
// TA: Yuye
// Lecturer: Gary
// Notes to Grader: none
import java.util.ArrayList;

public class Point implements IPoint{
  
  String name;
  ArrayList<IPointDistance> paths;
  
  public Point(String name) {
    this.name = name;
    paths = new ArrayList<IPointDistance>();
  }

  @Override
  public void connect(IPoint other, Long distance, Long speed) {
    PointDistance temp = new PointDistance(other, distance, speed);
    this.paths.add(temp);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ArrayList<IPointDistance> getConnections() {
    return this.paths;
  }
  
  @Override
  public String toString() {
    String toString = "Name: \"" + getName() + "\"\n";
    if (paths.isEmpty() == true) {
      toString = toString + "\tNo Paths";
    }
    else {
      toString = toString + "Paths:";
      for (int i = 0; i < paths.size(); i++) {
        toString = toString + "\n\t\"" + paths.get(i).getPoint().getName() + "\" distance of " 
      + paths.get(i).getDistance() + " and speed of " + paths.get(i).getSpeed();
      }
    }
    return toString;
  }

}
