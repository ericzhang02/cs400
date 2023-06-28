import java.util.ArrayList;

interface MathOperation {
  public double compute(double a, double b);
}

class AdditionOperation implements MathOperation{
  @Override
  public double compute(double a, double b) {
    return a+b;
  }
}

public class CalculatorApp {

  public static MathOperation add() {
    // TODO: Define a class named AdditionOperation that implements
    // MathOperation (within this java source file) and defines its
    // compute method to return the sum of its operands.  Then return
    // a new instance of this class from this method (on the line below):
    return new AdditionOperation();
  }

  public static MathOperation sub() {
    // TODO:  Return a new instance of an anonymous class that implements
    // MathOperation and defines its compute method to return the
    // the difference of its operands (first minus second), from this
    // method.
    return new MathOperation() {
      @Override
      public double compute(double a, double b) {
        return a-b;
      }
    };
  }

  public static MathOperation mul() {
    // TODO: Use a lambda expression on the line below to create and
    // return an object with a compute method that returns the product
    // of its operands.
    return ((a,b) -> (a * b));
  }

  /**
   * DO NOT MAKE ANY CHANGES TO THE MAIN METHOD BELOW FOR THIS ACTIVITY.
   *
   * This main method uses the objects returned by the methods above to
   * display the sum, difference, and product of operands between 1 and 5.
   * @param args is not used by this program
   */
  public static void main(String[] args) {
    // add all math operations to this array
    ArrayList<MathOperation> ops = new ArrayList<>();
    ops.add( add() );
    ops.add( sub() );
    ops.add( mul() );

    // display table of math operations applied to operands
    System.out.println("Operands:  add  sub  mul");
    for(int b = 1; b < 6; b++) // second operand (b) goes from 1 to 5
      for(int a = b + 1; a < 6; a++) { // first operand goes from b+1 to 5
        System.out.print("     "+a+","+b+":"); // print out operands first
        for(MathOperation op: ops)
          if(op != null) // then print out result of operation for those available
            System.out.printf( "%5.1f", op.compute(a,b) );
          else // and print out a dash otherwise
            System.out.print("    -");
        System.out.println(); // ensure that the next operands are printed to the next line
      }
  }
}
