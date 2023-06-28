// Name: Eric Zhang
// Email: erzhang2@wisc.edu
// Team: BI
// TA: Yuye
// Lecturer: Gary
// Notes to Grader: none

import java.util.List;

public interface IPoint {
  /*
   * Connects this point to another one.
   */
  public void connect(IPoint other, Long distance, Long speed);

  
  /*
   * returns the name of the Point
   */
  public String getName();

  /**
   * Returns a list of all connections to this point.
   */
  public List<IPointDistance> getConnections();

}
