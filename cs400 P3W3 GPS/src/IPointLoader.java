// --== CS400 File Header Information ==--
// Name: Eric Zhang
// Email: erzhang2@wisc.edu
// Team: BI
// TA: Yuye
// Lecturer: Gary
// Notes to Grader: none

import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;

public interface IPointLoader {

  /*
   * This function loads the points from the JSON and returns them as a list.
   */
  public List<IPoint> getPoints() throws IOException, ParseException;
}
