

public class Main {

  public static void main(String[] args) {
    MyList<Integer> test = new MyList<Integer>();
    Integer seven = 7;
    Integer eight = 8;
    test.add(seven);
    test.add(eight);
    for (int i = 0; i < test.size(); i++) {
      System.out.println(test.get(i));
    }
  }

}
