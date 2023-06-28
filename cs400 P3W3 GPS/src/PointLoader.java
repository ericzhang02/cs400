// Name: Eric Zhang
// Email: erzhang2@wisc.edu
// Team: BI
// TA: Yuye
// Lecturer: Gary
// Notes to Grader: none
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PointLoader implements IPointLoader{
  
  @SuppressWarnings("unchecked")
  @Override
  public List<IPoint> getPoints() throws IOException, ParseException {
        
    ArrayList<IPoint> finalPointList = new ArrayList<>();

    JSONParser parser = new JSONParser();
    try (FileReader reader = new FileReader("P3_data.json")){
      Object obj = parser.parse(reader);
      JSONArray pointListJSON = (JSONArray) obj;
      
      pointListJSON.forEach(point -> addPointObj((JSONObject) point, finalPointList));
      pointListJSON.forEach(point -> addPathsForPoints((JSONObject) point, finalPointList));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return finalPointList;
  }
  
  public static void addPointObj(JSONObject point, ArrayList<IPoint> finalList) {
    String name = (String) point.get("name");
    Point newPoint = new Point(name);
    finalList.add(newPoint);
  }
  
  public static void addPathsForPoints(JSONObject point, ArrayList<IPoint> finalList) {
    String currPoint = (String) point.get("name");
    JSONArray pathsList = (JSONArray) point.get("paths");
    
    for (int i = 0; i < pathsList.size(); i++) { //adding each path from each points list of paths
      JSONObject pathObject = (JSONObject) pathsList.get(i); 
      String pathName = (String) pathObject.get("name"); //name of the path
      Long pathDistance = (Long) pathObject.get("distance"); //distance of the path
      Long pathSpeed = (Long) pathObject.get("speed"); //speed of the path
      
      for (int j = 0; j < finalList.size(); j++) {
        for (int k = 0; k < finalList.size(); k++) {
          if (pathName.equals((finalList.get(j)).getName()) 
              && currPoint.equals((finalList.get(k)).getName())) {
            finalList.get(k).connect(finalList.get(j), pathDistance, pathSpeed);
          }
        }
      }
    }
  }

  
}
