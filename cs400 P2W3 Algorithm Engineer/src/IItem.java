/**
 * Represents an item in the online shop.
 *
 * @author Dong-Yon Kim and Jack
 *
 */
public interface IItem extends Comparable<IItem> {
  /**
   * Obtains the name of the given item.
   *
   * @return the name of the item
   */
  String getName();

  /**
   * Obtains the average rating (scoping up, not necessarily needed) of the product. The average
   * rating is the sum of all ratings divided by the number of ratings.
   *
   * @return the average rating of the product
   */
  double getAverageRating();

  /**
   * Obtains the number of ratings for the given product.
   *
   * @return the number of ratings
   */
  int getRatingCount();

  /**
   * Creates a String representation of this item.
   *
   * @return a String representation
   */
  @Override
  String toString();

  /**
   * Obtains the stock of the item (how many are in stock)
   *
   * @return the amount of this item that is in stock
   */
  int getStock();
  /**
   * Obtains the price of the item.
   *
   * @return the cost of this item
   */
  double getPrice();
  /*
   * void decrementStock(); - scoping up, if we were to consider changing the amount of this item
   * available to be sold
   */
  /**
   * Compares the two items by a specific factor (name, ratings, etc).
   * @return a negative integer if this item is smaller than o, 0 if the items are equal, and
   * a positive integer if this item is larger than o.
   */
  @Override
  int compareTo(IItem o);

}
