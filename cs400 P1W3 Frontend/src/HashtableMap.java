// --== CS400 Project One File Header ==--
// Name: Jack Lipstone
// CSL Username: Lipstone
// Email: JLipstone@Wisc.Edu
// Lecture #: 002 @1:00pm
// Notes to Grader: N/A

import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * This class creates a HashTableMap using methods implemented from MapADT
 *
 * @param <ValueType> is the value passed in to match to key
 * @param <KeyType>   is the key passed into match to value
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  protected int capacity;
  protected int size;
  protected double load;
  protected LinkedList<ValueMatch<KeyType, ValueType>>[] listArrayHash;

  /**
   * This constructor creates the LinkedList with a given capacity
   *
   * @param capacity is the max amount in the LinkedList
   */
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    listArrayHash = (LinkedList<ValueMatch<KeyType, ValueType>>[]) new LinkedList[capacity];
    this.capacity = capacity;
    this.size = 0;
    this.load = size / capacity;

  }

  /**
   * HashtableMap constructor with a default capacity of 20
   */
  @SuppressWarnings("unchecked")
  public HashtableMap() {
    capacity = 20;
    this.size = 0;
    listArrayHash = (LinkedList<ValueMatch<KeyType, ValueType>>[]) new LinkedList[capacity];
    this.load = size / capacity;
  }

  /**
   * This method associates the specified value with the specified key in this map
   *
   * @param key   with which the specified value is to be associated
   * @param value to be associated with the specified key
   * @return previous value associated with key or null (if there was no mapping for this specific
   * key)
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    if (containsKey(key)) {
      return false;
    }

    int hashNum = key.hashCode();
    int num = Math.abs(hashNum) % this.capacity;
    if (listArrayHash[num] == null) {
      listArrayHash[num] = new LinkedList<ValueMatch<KeyType, ValueType>>();
    }
    listArrayHash[num].push(new ValueMatch(key, value, hashNum));
    size++;
    this.load = (double) size / (double) capacity;
    if (this.load >= .75) {
      growHash();
    }
    return true;
  }

  /**
   * This is a helper method that is inserted into the put method to dynamically grow the
   * hashtable
   */
  protected void growHash() {
    capacity *= 2;
    LinkedList<ValueMatch<KeyType, ValueType>>[] growHelper = listArrayHash;
    clear();
    listArrayHash =
        (LinkedList<ValueMatch<KeyType, ValueType>>[]) new LinkedList[listArrayHash.length * 2];
    for (int i = 0; i < growHelper.length; i++) {
      if (growHelper[i] == null || growHelper[i].size() == 0) continue;
      for (int j = 0; j < growHelper[i].size(); j++) {
        ValueMatch<KeyType, ValueType> value = (ValueMatch) growHelper[i].get(j);
        put(value.key, value.value);
      }
      return;
    }
  }


  /**
   * The get method returns the value to which the specified key is mapped
   *
   * @param key is the specified key
   * @return the value to which the specified key is mapped, or null if the map does not contain a
   * map for this particular key
   * @throws NoSuchElementException if the key does not exist
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int hashNum = key.hashCode();
    int num = Math.abs(hashNum) % this.capacity;
    if (listArrayHash[num] == null)
      throw new NoSuchElementException("This key does not exist.");
    for (int i = 0; i < listArrayHash[num].size(); i++) {
      if (listArrayHash[num].get(i).getKey().equals(key)) {
        return (ValueType) listArrayHash[num].get(i).getValue();
      }
    }
    throw new NoSuchElementException("This key does not exist.");
  }


  /**
   * This method removes the mapping for a key from this map if it is present
   *
   * @param key
   * @return the value to which this map previously associated the key, or null if it does not
   * exist
   * @throws NoSuchElementException if the key does not exist
   */
  @Override
  public ValueType remove(KeyType key) {
    ValueType removeValue = null;
    int hashNum = key.hashCode();
    int num = Math.abs(hashNum) % this.capacity;
    for (int i = 0; i < listArrayHash[num].size(); i++) {
      if (listArrayHash[num] == null) continue;
      if (listArrayHash[num].get(i).getKey().equals(key)) {
        ValueMatch removeValue1 = listArrayHash[num].remove(i);
        removeValue = (ValueType) removeValue1.getValue();
        return removeValue;
      }
    }
    return null;
  }


  /**
   * This method returns the number of key-value mappings in this map
   *
   * @return the number of key-value mappings in this map
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * This method checks to see if the key exists
   *
   * @param key is the value being tested in this map
   * @return true if a map contains a mapping for the specified key. True, if and only if this map
   * contains a mapping for a key such that key is == null
   */
  @Override
  public boolean containsKey(KeyType key) {
    int hashNum = key.hashCode();
    int num = Math.abs(hashNum) % this.capacity;
    if (listArrayHash[num] == null) return false;
    for (int i = 0; i < listArrayHash[num].size(); i++) {
      if (listArrayHash[num].get(i) == null) continue;
      if (listArrayHash[num].get(i).getKey().equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This public method is used to help test capacity in the tester class
   *
   * @return capacity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * This clear method is used to clear the LinkedList if there is a corresponding length and
   * values to be cleared
   */
  @Override
  public void clear() {
    for (int i = 0; i < listArrayHash.length; i++) {
      listArrayHash[i] = null;
    }
    size = 0;
  }
}
