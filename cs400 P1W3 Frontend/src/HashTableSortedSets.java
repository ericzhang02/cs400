
import java.util.LinkedList;
import java.util.List;

public class HashTableSortedSets<KeyType, ValueType
extends Comparable<ValueType>>
extends HashtableMap<KeyType, List<ValueType>>
implements IHashTableSortedSets<KeyType,ValueType>
{

  /**
   * Constructor from HashtableMap class
   */
  public HashTableSortedSets() {
    super();
  }

  /**
   * Constructor from HashtableMap class
   * @param capacity is passed in for the size of the map
   */
  public HashTableSortedSets(int capacity) {
    super(capacity);
  }

  /**
   * This add method is different from the put() method in that it appends a
   * single value to the end of the list associated with a given key.  If a
   * key does not yet have a list of values associated with it, then a new
   * one will be created when this method is called.
   * @param key used to later lookup the list containing this value
   * @param value associated with the previous key
   */
  public void add(KeyType key, ValueType value) {
    int hashNum = key.hashCode();
    int num = Math.abs(hashNum) % this.listArrayHash.length;
    if (listArrayHash[num] == null) {
      listArrayHash[num] = new LinkedList<>();
    }
    for (int l = 0; l < listArrayHash[num].size(); l++) {
      if(listArrayHash[num].get(l).key.equals(key)) {
        listArrayHash[num].get(l).value.add(value);
      }
    }
    LinkedList<ValueType> List = new LinkedList<>();
    List.push(value);
    listArrayHash[num].push(new ValueMatch(key, List, hashNum));
    size++;
    this.load = (double) size / (double) capacity;
    if (this.load >= .75) {
      growHash();
    }
  }
}
