import java.util.NoSuchElementException;

// --== CS400 Project One File Header ==--
// Name: Eric Zhang
// CSL Username: ericz
// Email: erzhang2@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: 
public class HashtableMapTests {

  public static boolean test1() { 
    //put + clear tester
    HashtableMap<Integer, String> tester = new HashtableMap<Integer, String>();
    //adds first pair
    tester.put(1, "cat");
    if (tester.size() != 1) {
      return false;
    }
    //adds second pair that DOESNT WORK
    tester.put(1, "dog");
    if (tester.size() != 1) {
      return false;
    }
    //adds legit second pair
    tester.put(2, "dog");
    if (tester.size() != 2) {
      return false;
    }
  //adds third pair that uses chaining
    tester.put(22, "tree");
    if (tester.size() != 3) {
      return false;
    }
    //adds pair with null key
    tester.put(null, "yee");
    if (tester.size() != 3) {
      return false;
    }
    //tests clear
    tester.clear();
    if(tester.size() != 0) {
      return false;
    }
    return true;
  }
  
  public static boolean test2() {
    //containsKey tester
    HashtableMap<Integer, String> tester = new HashtableMap<Integer, String>();
    //adds first pair
    tester.put(1, "cat");
    if (tester.size() != 1) {
      return false;
    }
    if(tester.containsKey(1) != true) { //checking contains method
      return false;
    }
    //adding second pair
    tester.put(15, "my guy");
    if (tester.size() != 2) {
      return false;
    }
    if(tester.containsKey(15) != true) { //check contain method
      return false;
    }
    if(tester.containsKey(null) != false) { //check when key given is null
      return false;
    }
    if(tester.containsKey(3) != false) { //check when key given is not in hashtable
      return false;
    }
    return true;
  }
  
  public static boolean test3() {
    //get tester
    HashtableMap<Integer, String> tester = new HashtableMap<Integer, String>();
    //adds first pair
    tester.put(1, "cat");
    if(tester.get(1) != "cat") { //check get on working input
      return false;
    }
//    if(tester.get(null) != ) {
//      
//    }
    try { //tests get for a non-existent key
      tester.get(3);
      return false;
    } catch (NoSuchElementException npe) {
    }
    try { //tests get for a null key
      tester.get(null);
      return false;
    } catch (NoSuchElementException npe) {
    }
    return true;
  }
  
  public static boolean test4() { 
    //remove tester
    HashtableMap<Integer, String> tester = new HashtableMap<Integer, String>();
    //adds first pair
    tester.put(1, "cat");
    if (tester.size() != 1) {//size should be 1
      return false;
    }
    if (tester.containsKey(1) != true) {//should contain 1
      return false;
    }
    if (tester.get(1) != "cat") {//should return "cat"
      return false;
    }
    //removes first pair
    tester.remove(1);
    if (tester.size() != 0) {//size should be 0
      return false;
    }
    if (tester.containsKey(1) != false) { //contains should return false
      return false;
    }
    try { //get should throw an exception
      tester.get(1);
      return false;
    } catch (NoSuchElementException npe) {
    }
    return true;
  }
  
  public static boolean test5() {
    //resize tester
    HashtableMap<Integer, String> tester = new HashtableMap<Integer, String>(4);
    //adding pairs so that resize is called
    tester.put(1, "one");
    tester.put(2, "two");
    tester.put(6, "bruh");
    tester.put(3, "wat");
    if (tester.size() != 4) {
      return false;
    }
    if(tester.get(6) != "bruh") {
      return false;
    }
    if (tester.containsKey(3) != true) {
      return false;
    }
    tester.remove(1);
    tester.remove(2);
    if(tester.size() != 2) {
      return false;
    }
    if (tester.get(3) != "wat") {
      return false;
    }
    return true;
  }
  
  public static boolean runAllTests() {
    System.out.println("test1: "+test1());
    System.out.println("test2: "+test2());
    System.out.println("test3: "+test3());
    System.out.println("test4: "+test4());
    System.out.println("test5: "+test5());
    if(!test1() || !test2()|| !test3() || !test4()|| !test5()) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("All tests: " + runAllTests());
  }
}
