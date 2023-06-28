public class Item implements IItem {
  String name;
  double price;
  double averageRating;
  int ratingCount;
  int stock;

  public Item(int stock, double price, double averageRating, int ratingCount, String name) {
    this.ratingCount = ratingCount;
    this.stock = stock;
    this.price = price;
    this.name = name;
    this.averageRating = averageRating;
  }

  /**
   * Obtains the name of the given item.
   *
   * @return the name of the item
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Obtains the average rating (scoping up, not necessarily needed) of the product. The average
   * rating is the sum of all ratings divided by the number of ratings.
   *
   * @return the average rating of the product
   */
  @Override
  public double getAverageRating() {
    return averageRating;
  }

  /**
   * Obtains the number of ratings for the given product.
   *
   * @return the number of ratings
   */
  @Override
  public int getRatingCount() {
    return ratingCount;
  }
  /**
   * Obtains the stock of the item (how many are in stock)
   *
   * @return the amount of this item that is in stock
   */
  @Override
  public int getStock() {
    return stock;
  }

  /**
   * Obtains the price of the item.
   *
   * @return the cost of this item
   */
  @Override
  public double getPrice() {
    return price;
  }

  /**
   * Compares the two items by a specific factor (name, ratings, etc).
   *
   * @param o
   * @return a negative integer if this item is smaller than o, 0 if the items are equal, and a
   * positive integer if this item is larger than o.
   */
  @Override
  public int compareTo(IItem o) {
    return  compareToByPrice(o);
  }

  private int compareToByPrice(IItem other) {
    return (int) (this.price - other.getPrice());
  }

  public int compareToByName(Item other) {
    return this.name.compareTo(other.getName());
  }

  public int compareToByRatingCount(Item other) {
    return this.ratingCount - other.getRatingCount();
  }

  public int compareToByAverageRating(Item other) {
    return (int) (this.averageRating - other.getAverageRating());
  }
  
  public String toString() {
    return "Item Name: " + name + "\nAverage Rating: " + averageRating + "\nRating Count: " + ratingCount + "\nStock: " + stock + "\nPrice: $" + price;
  }
}
