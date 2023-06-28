// Name: Eric Zhang
// Email: erzhang2@wisc.edu
// Team: BI
// TA: Yuye
// Lecturer: Gary
// Notes to Grader: none
public class PointDistance implements IPointDistance {
  
  IPoint otherPoint;
  Long distance;
  Long speed;
  
  public PointDistance(IPoint otherpoint, Long distance, Long speed) {
    this.otherPoint = otherpoint;
    this.distance = distance;
    this.speed = speed;
  }

  @Override
  public IPoint getPoint() {
    return this.otherPoint;
  }

  @Override
  public Long getDistance() {
    return this.distance;
  }
  
  @Override
  public Long getSpeed() {
    return this.speed;
  }

}
