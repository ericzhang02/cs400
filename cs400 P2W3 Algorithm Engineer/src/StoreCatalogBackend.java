
import java.util.ArrayList;

public class StoreCatalogBackend implements IStoreCatalogBackend {
  public RedBlackTree<Item> ratingTree;
  public RedBlackTree<Item> alphabeticalTree;
  public RedBlackTree<Item> priceTree;
  public ArrayList<Item> items;


  public StoreCatalogBackend() {
    ItemFinder finder = new ItemFinder();
    // Will change slightly when using actual ItemFinder
    ArrayList<Item> items = finder.loadItems();//"./shop_items.xml");
    this.ratingTree = new RedBlackTree<Item>();
    this.alphabeticalTree = new RedBlackTree<Item>();
    this.priceTree = new RedBlackTree<Item>();
  }

  /**
   * Returns a array of strings that contain the inputed word.
   * @param word
   * @return array of items
   */
  public ArrayList<Item> filterByWord(String word) {
    ArrayList<Item> filteredItems = new ArrayList<Item>();
    for (Item item : this.items) {
      if (item.getName().contains(word)) {
        filteredItems.add(item);
      }
    }
    return filteredItems;
  }

  /**
   * Takes a list of items from filterByWord() and inserts it into a red-black tree
   * putting the item with the highest rating at the top.
   */
  public void sortByRating(ArrayList<Item> items) {
    for (Item item : items) {ratingTree.insert(item, x -> ((Item) x).getAverageRating());}
  }

  /**
   * Takes a list of items from filterByWord() and inserts it into a red-black tree
   * putting names in in alphabetical order.
   */
  public void sortByName(ArrayList<Item> items) {
    for (Item item : items) {alphabeticalTree.insert(item, x -> ((Item) x).getName());}
  }

  /**
   * Takes a list of items from filterByWord() and inserts it into a red-black tree
   * putting the item with the lowest price at the top.
   */
  public void sortByPrice(ArrayList<Item> items) {
    for (Item item : items) {priceTree.insert(item, x -> ((Item) x).getPrice());}
  }

}
