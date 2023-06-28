// --== CS400 Project One File Header ==--
// Name: Eric Zhang
// CSL Username: ericz
// Email: erzhang2@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: MY TESTER CLASS MAKES USE OF BOTH PLACE HOLDER BACKEND AND A PLACEHOLDER SHOW 
// BECAUSE WE DON'T HAVE WORKING BACKEND END CODE. SO THIS IS THE ONLY WAY I CAN FUTHER TEST MY
// FRONTEND CLASS. Testers 1-7 are using my place holders that I made.

import java.util.ArrayList;
import java.util.List;

public class FrontendDeveloperTests {

  public static boolean test1() {
    //tests printCommandMenu() and the quit function for runCommandLoop()
    TextUITester tester = new TextUITester("q\n");
    ShowSearcherBackendPlaceholder backend = new ShowSearcherBackendPlaceholder();
    ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
    frontend.runCommandLoop();
    String output = tester.checkOutput();
    //makes sure output match
    if(output.startsWith("Welcome to the Show Searcher App!")
        && output.contains("=================================")
        && output.contains("Command Menu:")
        && output.contains("1) Search by [T]itle Word")
        && output.contains("2) Search by [Y]ear First Produced")
        && output.contains("3) [F]ilter by Streaming Provider")
        && output.contains("0) [Q]uit")
        && output.contains("Choose a command from the menu above:")
        && output.contains("=============Goodbye!============")) {
      return true;
    }
    return false;
  }

  public static boolean test2() {
    //tests displayShows()
    TextUITester tester = new TextUITester("");
    ShowPlaceholder show0 = new ShowPlaceholder(0);
    ShowPlaceholder show1 = new ShowPlaceholder(1);
    List<IShow> list=new ArrayList<IShow>();
    list.add(show0);
    list.add(show1);
    ShowSearcherBackendPlaceholder backend = new ShowSearcherBackendPlaceholder();
    ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
    frontend.displayShows(list);
    String output = tester.checkOutput();
    //makes sure output match
    if(output.startsWith("Found 2/16 matches.")
        && output.contains("1. title0")
        && output.contains("0/100 (0) on: Netflix Hulu Prime Video Disney+")
        && output.contains("2. title1")
        && output.contains("1/100 (1) on: Netflix Hulu Prime Video Disney+")) {
      return true;
    }
    return false;

  }

  public static boolean test3() {
    //tests titleSearch()
    TextUITester tester = new TextUITester("1\ntree\n0\n");
    ShowSearcherBackendPlaceholder backend = new ShowSearcherBackendPlaceholder();
    ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
    frontend.runCommandLoop();
    String output = tester.checkOutput();
    //makes sure output match
    if(output.startsWith("Welcome to the Show Searcher App!")
        && output.contains("Choose a word that you would like to search for:")
        && output.contains("Found 2/16 matches.")
        && output.contains("1. title5")
        && output.contains("5/100 (5) on: Netflix Hulu Prime Video Disney+")
        && output.contains("2. title6")
        && output.contains("6/100 (6) on: Netflix Hulu Prime Video Disney+")
        && output.contains("Choose a command from the menu above:")
        && output.contains("=============Goodbye!============")) {
      return true;
    }
    return false;
  }

  public static boolean test4() {
    //tests yearSearch()
    TextUITester tester = new TextUITester("2\n20\nq\n");
    ShowSearcherBackendPlaceholder backend = new ShowSearcherBackendPlaceholder();
    ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
    frontend.runCommandLoop();
    String output = tester.checkOutput();
    //makes sure output match
    if(output.startsWith("Welcome to the Show Searcher App!")
        && output.contains("Choose a year that you would like to search for:")
        && output.contains("Found 2/16 matches.")
        && output.contains("1. title3")
        && output.contains("3/100 (3) on: Netflix Hulu Prime Video Disney+")
        && output.contains("2. title4")
        && output.contains("4/100 (4) on: Netflix Hulu Prime Video Disney+")
        && output.contains("Choose a command from the menu above:")
        && output.contains("=============Goodbye!============")
        ) {
      return true;
    }
    return false;
  }

  public static boolean test5() {
    //tests toggle menu
    TextUITester tester = new TextUITester("3\n2\nq\nq\n");
    ShowSearcherBackendPlaceholder backend = new ShowSearcherBackendPlaceholder();
    ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
    frontend.runCommandLoop();
    String output = tester.checkOutput();
    //makes sure output match
    if(output.startsWith("Welcome to the Show Searcher App!")
        && output.contains("Providers that shows are being searched for:")
        && output.contains("1) _X_ [N]etflix")
        && output.contains("2) _X_ [H]ulu")
        && output.contains("3) _X_ [P]rime Video")
        && output.contains("4) _X_ [D]isney+")
        && output.contains("5) [Q]uit toggling provider filters")
        && output.contains("Choose the provider that you'd like to toggle the filter for:")
        && output.contains("2) ___ [H]ulu")
        && output.contains("=============Goodbye!============")
        ) {
      return true;
    }
    return false;
  }
  
  public static boolean test6() {
    //tests titleSearch() when user uses CAPS
    TextUITester tester = new TextUITester("1\nTree\n0\n");
    ShowSearcherBackendPlaceholder backend = new ShowSearcherBackendPlaceholder();
    ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
    frontend.runCommandLoop();
    String output = tester.checkOutput();
    //makes sure output match
    if(output.startsWith("Welcome to the Show Searcher App!")
        && output.contains("Choose a word that you would like to search for:")
        && output.contains("Found 2/16 matches.")
        && output.contains("1. title5")
        && output.contains("5/100 (5) on: Netflix Hulu Prime Video Disney+")
        && output.contains("2. title6")
        && output.contains("6/100 (6) on: Netflix Hulu Prime Video Disney+")
        && output.contains("Choose a command from the menu above:")
        && output.contains("=============Goodbye!============")) {
      return true;
    }
    return false;
  }
  
  public static boolean test7() {
    //tests yearSearch()
    TextUITester tester = new TextUITester("2\nnotanumber\nq\n");
    ShowSearcherBackendPlaceholder backend = new ShowSearcherBackendPlaceholder();
    ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
    frontend.runCommandLoop();
    String output = tester.checkOutput();
    //makes sure output match
    if(output.startsWith("Welcome to the Show Searcher App!")
        && output.contains("Choose a command from the menu above:")
        && output.contains("Choose a year that you would like to search for:")
        && output.contains("notanumber is not a year")
        && output.contains("Choose a command from the menu above:")
        && output.contains("=============Goodbye!============")
        ) {
      return true;
    }
    return false;
    }
  
  public static boolean test8() {
    //tester for the given Backend code and not the place holder
    //tests for when given "netflix" and not "Netflix" in toggle method: should not throw an exception
    try {
      TextUITester tester = new TextUITester("1\title\nq\n");
      ShowSearcherBackend backend = new ShowSearcherBackend();
      backend.setProviderFilter("Netflix", true);
      backend.setProviderFilter("Hulu", true);
      backend.setProviderFilter("Prime Video", true);
      backend.setProviderFilter("Disney+", true);
      //    public Show(String title, int year, int rating, String providers) {
      backend.addShow(new Show("title 1", 2021, 1, "netflix"));
      backend.addShow(new Show("title 2", 2021, 2, "Hulu"));
      backend.toggleProviderFilter("netflix");
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean test9() {
    //tester for the given Backend code and not the place holder
    //tests for when given "hulu" and not "Hulu" in toggle method: should not throw an exception
    try {
      TextUITester tester = new TextUITester("1\title\nq\n");
      ShowSearcherBackend backend = new ShowSearcherBackend();
      backend.setProviderFilter("Netflix", true);
      backend.setProviderFilter("Hulu", true);
      backend.setProviderFilter("Prime Video", true);
      backend.setProviderFilter("Disney+", true);
      backend.addShow(new Show("title 1", 2021, 1, "netflix"));
      backend.addShow(new Show("title 2", 2021, 2, "Hulu"));
      backend.getProviderFilter("hulu");
      return true;
    } catch (Exception e) {
      return false;
    }  }
  
  public static boolean runAllTestsFrontend() {
    if(!test1() || !test2()|| !test3() || !test4()|| !test5() || !test6() || !test7()) {
      return false;
    }
    return true;
  }
  
  public static boolean runAllTestsBackend() {
    if (!test8() || !test9()) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("All Frontend tests: " + runAllTestsFrontend());
    System.out.println("New Backend tests: " + runAllTestsBackend());
  }

}
