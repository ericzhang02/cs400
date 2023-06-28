// --== CS400 File Header Information ==--
// Name: Eric Zhang
// Email: erzhang2@wisc.edu
// Team: BI
// TA: Yuye
// Lecturer: Gary Dahl
// Notes to Grader: No additional test due to video

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

class AlgorithmEngineerTests {

  int black = 1;
  int red = 0;

  @Test
  void testInsert() {
    RedBlackTree<Item> tester = new RedBlackTree<Item>();
    Item dog = new Item(3, 3.3, 3.33, 33, "dog");
    Item cat = new Item(2, 2.2, 2.22, 22, "cat");
    Item tree = new Item(7, 7.7, 7.77, 77, "tree");
    Item bird = new Item(1, 1.1, 1.11, 11, "bird");

    //inserting items
    tester.insert(dog, x -> ((Item) x).getStock());
    tester.insert(cat, x -> ((Item) x).getStock());
    tester.insert(tree, x -> ((Item) x).getStock());
    tester.insert(bird, x -> ((Item) x).getStock());
    
    assertEquals(tester.root.data, dog);
    assertEquals(tester.root.leftChild.data, cat);
    assertEquals(tester.root.rightChild.data, tree);
    assertEquals(tester.root.leftChild.leftChild.data, bird);
  }
  
  @Test
  void TestRedUncleRecusion() {
    RedBlackTree<Item> tester = new RedBlackTree<Item>();
    //creating Items
    Item dog = new Item(3, 3.3, 3.33, 33, "dog");
    Item cat = new Item(2, 2.2, 2.22, 22, "cat");
    Item tree = new Item(7, 7.7, 7.77, 77, "tree");
    Item bird = new Item(1, 1.1, 1.11, 11, "bird");
    Item ferret = new Item(4, 4.4, 4.44, 44, "ferret"); 
    Item mole = new Item(5, 5.5, 5.55, 55, "mole");  
    Item plant = new Item(6, 6.6, 6.66, 66, "plant");  
    Item wall = new Item(8, 8.8, 8.88, 8, "wall");   
    //making sure tree is how is should look like
    tester.insert(cat, x -> ((Item) x).getAverageRating());
    tester.insert(bird, x -> ((Item) x).getAverageRating());
    tester.insert(tree, x -> ((Item) x).getAverageRating());
    tester.insert(wall, x -> ((Item) x).getAverageRating());
    tester.insert(ferret, x -> ((Item) x).getAverageRating());
    tester.insert(dog, x -> ((Item) x).getAverageRating());
    tester.insert(plant, x -> ((Item) x).getAverageRating());
    assertEquals(tester.root.blackHeight, black);
    assertEquals(tester.root.data, cat);
    assertEquals(tester.root.leftChild.blackHeight, black);
    assertEquals(tester.root.leftChild.data, bird);
    assertEquals(tester.root.rightChild.blackHeight, red);
    assertEquals(tester.root.rightChild.data, tree);
    assertEquals(tester.root.rightChild.leftChild.blackHeight, black);
    assertEquals(tester.root.rightChild.leftChild.data, ferret);
    assertEquals(tester.root.rightChild.leftChild.leftChild.blackHeight, red);
    assertEquals(tester.root.rightChild.leftChild.leftChild.data, dog);
    assertEquals(tester.root.rightChild.rightChild.blackHeight, black);
    assertEquals(tester.root.rightChild.rightChild.data, wall);
    assertEquals(tester.root.rightChild.leftChild.rightChild.blackHeight, red);
    assertEquals(tester.root.rightChild.leftChild.rightChild.data, plant);
    //testing red uncle recursion
    tester.insert(mole, x -> ((Item) x).getAverageRating());
    assertEquals(tester.root.blackHeight, black);
    assertEquals(tester.root.data, ferret);
    assertEquals(tester.root.leftChild.blackHeight, red);
    assertEquals(tester.root.leftChild.data, cat);
    assertEquals(tester.root.rightChild.blackHeight, red);
    assertEquals(tester.root.rightChild.data, tree);
    assertEquals(tester.root.leftChild.leftChild.blackHeight, black);
    assertEquals(tester.root.leftChild.leftChild.data, bird);
    assertEquals(tester.root.leftChild.rightChild.blackHeight, black);
    assertEquals(tester.root.leftChild.rightChild.data, dog);
    assertEquals(tester.root.rightChild.leftChild.blackHeight, black);
    assertEquals(tester.root.rightChild.leftChild.data, plant);
    assertEquals(tester.root.rightChild.rightChild.blackHeight, black);
    assertEquals(tester.root.rightChild.rightChild.data, wall);
    assertEquals(tester.root.rightChild.leftChild.leftChild.blackHeight, red);
    assertEquals(tester.root.rightChild.leftChild.leftChild.data, mole);
  }

  @Test
  void TestSameSide() {
    RedBlackTree<Item> tester = new RedBlackTree<Item>();
    //creating items
    Item dog = new Item(3, 3.3, 3.33, 33, "dog");
    Item cat = new Item(2, 2.2, 2.22, 22, "cat");
    Item tree = new Item(7, 7.7, 7.77, 77, "tree");
    Item bird = new Item(1, 1.1, 1.11, 11, "bird");
    Item ferret = new Item(4, 4.4, 4.44, 44, "ferret"); 
    Item mole = new Item(5, 5.5, 5.55, 55, "mole");  
    Item plant = new Item(6, 6.6, 6.66, 66, "plant");  
    Item wall = new Item(8, 8.8, 8.88, 8, "wall"); 

    //Tests a leftChild-leftChild violation
    tester.insert(ferret, x -> ((Item) x).getName());
    assertEquals(tester.root.blackHeight, black);//root should be black and have a value of 4
    assertEquals(tester.root.data, ferret);
    tester.insert(dog, x -> ((Item) x).getName());
    assertEquals(tester.root.leftChild.blackHeight, red);//left child of should be red and have 
    assertEquals(tester.root.leftChild.data, dog);// a value of 3
    tester.insert(cat, x -> ((Item) x).getName());
    assertEquals(tester.root.blackHeight, black);//root node should still be black but now be node 3
    assertEquals(tester.root.data, dog);
    assertEquals(tester.root.leftChild.blackHeight, red);//left node should be red and now be node 2
    assertEquals(tester.root.leftChild.data, cat);
    assertEquals(tester.root.rightChild.blackHeight, red);//left node should be red and now be node 4
    assertEquals(tester.root.rightChild.data, ferret);
    //Tests a rightChild-rightChild violation
    tester.insert(mole, x -> ((Item) x).getName());
    assertEquals(tester.root.leftChild.blackHeight, black);//left node should now be black
    assertEquals(tester.root.rightChild.blackHeight, black);//right node should no be black
    assertEquals(tester.root.rightChild.rightChild.data, mole);//right right node should be node 5
    assertEquals(tester.root.rightChild.rightChild.blackHeight, red);//right right node should be red
    tester.insert(plant, x -> ((Item) x).getName());
    assertEquals(tester.root.rightChild.rightChild.data, plant);//right right should be node 6
    assertEquals(tester.root.rightChild.rightChild.blackHeight, red);// right right should be red
    assertEquals(tester.root.rightChild.leftChild.data, ferret);//right right should be node 4
    assertEquals(tester.root.rightChild.leftChild.blackHeight, red);// right right should be red
    assertEquals(tester.root.rightChild.data, mole);//right right should be node 5
    assertEquals(tester.root.rightChild.blackHeight, black);// right right should be black
  }

  @Test
  void TestOppositeSide() {
    RedBlackTree<Item> tester = new RedBlackTree<Item>();
    //creating Items
    Item dog = new Item(3, 3.3, 3.33, 33, "dog");
    Item cat = new Item(2, 2.2, 2.22, 22, "cat");
    Item tree = new Item(7, 7.7, 7.77, 77, "tree");
    Item bird = new Item(1, 1.1, 1.11, 11, "bird");
    Item ferret = new Item(4, 4.4, 4.44, 44, "ferret"); 
    Item mole = new Item(5, 5.5, 5.55, 55, "mole");  
    Item plant = new Item(6, 6.6, 6.66, 66, "plant");  
    Item wall = new Item(8, 8.8, 8.88, 8, "wall");  

    //Tests a leftChild-rightChild violation
    tester.insert(ferret, x -> ((Item) x).getRatingCount());
    assertEquals(tester.root.blackHeight, black);//root should be black and have a value of 4
    assertEquals(tester.root.data, ferret);
    tester.insert(cat, x -> ((Item) x).getRatingCount());
    assertEquals(tester.root.leftChild.blackHeight, red);//left child of should be red and have 
    assertEquals(tester.root.leftChild.data, cat);// a value of 2
    tester.insert(dog, x -> ((Item) x).getRatingCount());
    assertEquals(tester.root.blackHeight, black);//root node should still be black but now be node 3
    assertEquals(tester.root.data, dog);
    assertEquals(tester.root.leftChild.blackHeight, red);//left node should be red and now be node 2
    assertEquals(tester.root.leftChild.data, cat);
    assertEquals(tester.root.rightChild.blackHeight, red);//left node should be red and now be node 2
    assertEquals(tester.root.rightChild.data, ferret);
    //Tests a rightChild-leftChild violation
    tester.insert(plant, x -> ((Item) x).getRatingCount());
    assertEquals(tester.root.leftChild.blackHeight, black);//left node should now be black
    assertEquals(tester.root.rightChild.blackHeight, black);//right node should no be black
    assertEquals(tester.root.rightChild.rightChild.data, plant);//right right node should be node 6
    assertEquals(tester.root.rightChild.rightChild.blackHeight, red);//right right node should be red
    tester.insert(mole, x -> ((Item) x).getRatingCount());
    assertEquals(tester.root.rightChild.rightChild.data, plant);//right right should be node 6
    assertEquals(tester.root.rightChild.rightChild.blackHeight, red);// right right should be red
    assertEquals(tester.root.rightChild.leftChild.data, ferret);//right right should be node 4
    assertEquals(tester.root.rightChild.leftChild.blackHeight, red);// right right should be red
    assertEquals(tester.root.rightChild.data, mole);//right right should be node 5
    assertEquals(tester.root.rightChild.blackHeight, black);// right right should be black
  }

  @Test
  void testContains() {
    RedBlackTree<Item> tester = new RedBlackTree<Item>();
    //creating Items
    Item dog = new Item(3, 3.3, 3.33, 33, "dog");
    Item cat = new Item(2, 2.2, 2.22, 22, "cat");
    Item tree = new Item(7, 7.7, 7.77, 77, "tree");
    Item bird = new Item(1, 1.1, 1.11, 11, "bird");
    Item ferret = new Item(4, 4.4, 4.44, 44, "ferret"); 
    Item mole = new Item(5, 5.5, 5.55, 55, "mole");  
    Item plant = new Item(6, 6.6, 6.66, 66, "plant");  
    Item wall = new Item(8, 8.8, 8.88, 8, "wall");   
    assertEquals(tester.contains(dog, x -> ((Item) x).getPrice()), false);
    assertEquals(tester.contains(cat, x -> ((Item) x).getPrice()), false);
    assertEquals(tester.contains(tree, x -> ((Item) x).getPrice()), false);
    assertEquals(tester.contains(bird, x -> ((Item) x).getPrice()), false);
    assertEquals(tester.contains(ferret, x -> ((Item) x).getPrice()), false);
    assertEquals(tester.contains(mole, x -> ((Item) x).getPrice()), false);
    assertEquals(tester.contains(plant, x -> ((Item) x).getPrice()), false);
    assertEquals(tester.contains(wall, x -> ((Item) x).getPrice()), false);
    tester.insert(wall, x -> ((Item) x).getPrice());
    assertEquals(tester.contains(wall, x -> ((Item) x).getPrice()), true);
    tester.insert(dog, x -> ((Item) x).getPrice());
    assertEquals(tester.contains(dog, x -> ((Item) x).getPrice()), true);
    tester.insert(cat, x -> ((Item) x).getPrice());
    assertEquals(tester.contains(cat, x -> ((Item) x).getPrice()), true);
    tester.insert(tree, x -> ((Item) x).getPrice());
    assertEquals(tester.contains(tree, x -> ((Item) x).getPrice()), true);
    tester.insert(bird, x -> ((Item) x).getPrice());
    assertEquals(tester.contains(bird, x -> ((Item) x).getPrice()), true);
    tester.insert(ferret, x -> ((Item) x).getPrice());
    assertEquals(tester.contains(ferret, x -> ((Item) x).getPrice()), true);
    tester.insert(mole, x -> ((Item) x).getPrice());
    assertEquals(tester.contains(mole, x -> ((Item) x).getPrice()), true);
    tester.insert(plant, x -> ((Item) x).getPrice());
    assertEquals(tester.contains(plant, x -> ((Item) x).getPrice()), true);
  }

  @Test
  void testGetList() {
    RedBlackTree<Item> tester = new RedBlackTree<Item>();
    //creating Items
    Item dog = new Item(3, 3.3, 3.33, 33, "dog");
    Item cat = new Item(2, 2.2, 2.22, 22, "cat");
    Item tree = new Item(7, 7.7, 7.77, 77, "tree");
    Item bird = new Item(1, 1.1, 1.11, 11, "bird");
    Item ferret = new Item(4, 4.4, 4.44, 44, "ferret"); 
    Item mole = new Item(5, 5.5, 5.55, 55, "mole");  
    Item plant = new Item(6, 6.6, 6.66, 66, "plant");  
    Item wall = new Item(8, 8.8, 8.88, 8, "wall"); 
    //inserting items
    tester.insert(dog, x -> ((Item) x).getStock());
    tester.insert(cat, x -> ((Item) x).getStock());
    tester.insert(tree, x -> ((Item) x).getStock());
    tester.insert(bird, x -> ((Item) x).getStock());
    tester.insert(ferret, x -> ((Item) x).getStock());
    tester.insert(mole, x -> ((Item) x).getStock());
    tester.insert(plant, x -> ((Item) x).getStock());
    tester.insert(wall, x -> ((Item) x).getStock());
    //getting linkedlist from getList()
    LinkedList<Item> templist = tester.getList();
    //making sure items from linked list are correctly sorted
    assertEquals(templist.get(0), bird);
    assertEquals(templist.get(1), cat);
    assertEquals(templist.get(2), dog);
    assertEquals(templist.get(3), ferret);
    assertEquals(templist.get(4), mole);
    assertEquals(templist.get(5), plant);
    assertEquals(templist.get(6), tree);
    assertEquals(templist.get(7), wall);
  }
  
  @Test
  void testToLevelOrderString() {
    RedBlackTree<Item> tester = new RedBlackTree<Item>();
    Item dog = new Item(3, 3.3, 3.33, 33, "dog");
    Item cat = new Item(2, 2.2, 2.22, 22, "cat");
    Item tree = new Item(7, 7.7, 7.77, 77, "tree");
    Item bird = new Item(1, 1.1, 1.11, 11, "bird");

    //inserting items
    tester.insert(dog, x -> ((Item) x).getStock());
    tester.insert(cat, x -> ((Item) x).getStock());
    tester.insert(tree, x -> ((Item) x).getStock());
    tester.insert(bird, x -> ((Item) x).getStock());
    
    assertEquals(tester.toLevelOrderString(), "[ dog, cat, tree, bird ]");
  }
  
  //testing the DataWranglers classes
  @Test
  void testGetRatingCount() {
    //before, getRatingCount() did not return the right field 
    Item yep = new Item(1,3 ,2.1,33,"yeee");
    assertEquals(yep.getRatingCount(), 33);

  }  
  
  @Test
  void testCompareTo() {
    RedBlackTree<Item> tester = new RedBlackTree<Item>();
    Item dog = new Item(3, 3.3, 3.33, 33, "dog");
    Item cat = new Item(3, 3.3, 3.33, 33, "cat");
    tester.insert(dog, x -> ((Item) x).getStock());
    tester.insert(cat, x -> ((Item) x).getStock());
//    Comparable compare1 = (Comparable) (new Item(3, 3.3, 3.33, 33, "dog"));
//    Comparable compare2 = (Comparable)(new Item(2, 2.2, 2.22, 22, "cat"));
//    int compare = (compare1).compareTo(compare2);
    assertEquals(tester.root.data, dog);
    assertEquals(tester.root.leftChild.data, cat);
  }

}
