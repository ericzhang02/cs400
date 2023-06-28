// --== CS400 Project One File Header ==--
// Name: Eric Zhang
// CSL Username: ericz
// Email: erzhang2@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: 

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{

  private int capacity;
  private int size;
  protected LinkedList<HashNode<KeyType, ValueType>>[] hashtable;
  final double loadFactor = .75;

  //constructors
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    this.hashtable = (LinkedList<HashNode<KeyType, ValueType>>[]) new LinkedList[capacity];
    this.size = 0;
    this.capacity = capacity;
  }

  @SuppressWarnings("unchecked")
  public HashtableMap() {
    this.hashtable = (LinkedList<HashNode<KeyType, ValueType>>[]) new LinkedList[20];
    this.size = 0;
    this.capacity = 20;
    // with default capacity = 20
  }

  
  @Override
  /**
   * returns true if key-value pair is added
   * @param key, value
   * @return true if key-value pair is added, return false is key is already there or null
   */
  public boolean put(KeyType key, ValueType value) {
    if(key == null) { //checks if key is null, if null then returns false
      return false;
    }
    else if(containsKey(key)==true) { //checks is given key already exists in hashtable
      return false;
    }
    else{ //actually adding key
      LinkedList<HashNode<KeyType, ValueType>> tempList = 
          new LinkedList<HashNode<KeyType, ValueType>>();
      tempList = this.hashtable[calculateHashKey(key)]; //selects the corrects linkedlist to add to
      if(tempList == null) { //checks for a linked list, if non-existent then creates one
        tempList = new LinkedList<HashNode<KeyType, ValueType>>();
      }
      tempList.add(new HashNode<KeyType, ValueType>(key, value)); 
      //adds key-value pair to temp linkedlist
      this.hashtable[calculateHashKey(key)] = tempList;
      //inserts the new linked list into the hashtable
      size++;//increases size
      if (size() >= loadFactor*capacity) { //checking if hashtable needs resizing
        resizeTable();
      }
    }
    return true;//returns true since key-value pair was added
  }

  @Override
  /**
   * returns
   * @param key
   * @throws NoSuchElementException
   * @return
   */
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (key == null) {
      throw new NoSuchElementException("element given is null"); 
    }
    int hashKey = calculateHashKey(key); //calculates hashkey
    if (hashtable[hashKey] != null) { //makes sure targeted linkedlist is not empty
      for(HashNode<KeyType, ValueType> node:hashtable[hashKey]) { //loops through linkedlist
        if(node.getKeyType().equals(key)) { //checks for matching key
          return node.getValueType(); //returns the value of matching key
        }
      }
    }
    throw new NoSuchElementException("no element for given key"); 
    //throws exception if no key is found
  }

  @Override
  /**
   * to get size of hashtable or num of elements
   * @return the size of hashtable
   */
  public int size() {
    return this.size;
  }

  /**
   * method to check if hashtable contains given key
   * @param key
   * @return false if hashtable contains key, returns true if key is in hashtable
   */
  @Override
  public boolean containsKey(KeyType key) {
    if(key == null) { //returns false if given key is null
      return false;
    }
    int hashKey = calculateHashKey(key); //calculates hashkey
    if(hashtable[hashKey] ==null) { //if linkedlist is non-existent, return false
      return false;
    }
    for(HashNode<KeyType, ValueType> node:hashtable[hashKey]) { //if linkedlist does exist
      if(node.getKeyType().equals(key)) { //return true is matching key is found
        return true;
      }
    } 
    return false; //returns false if no matching key is found
  }
  
  /**
   * removes the given key in the hashtable
   * @param key
   * @return the node that was removed or null
   */
  @Override
  public ValueType remove(KeyType key) {
    int hashKey = calculateHashKey(key); // calculates the hashkey
    for(HashNode<KeyType, ValueType> node:hashtable[hashKey]) { //loops over the linkedlist
      if(node.getKeyType() == key) { //checks for matching keys
        hashtable[hashKey].remove(node); //removes node from hashtable
        size--; //decreases size
        return node.getValueType(); //returns removed node
      }
    }   
    return null; //returns null if not matching node is found
  }

  @SuppressWarnings("unchecked")
  @Override
  public void clear() {
    this.hashtable = (LinkedList<HashNode<KeyType, ValueType>>[]) new LinkedList[capacity];  
    this.size = 0;
  }
  
  /**
   * helper method to calculate the hashkey
   * @param key
   * @return returns the value of the givens keys hashkey
   */
  private int calculateHashKey(KeyType key) {
    int temp = (Math.abs(key.hashCode()))%capacity;
    return temp;
  }
  
  /**
   * helper method to resize the hashtable and insert its elements back in
   */
  @SuppressWarnings("unchecked")
  private void resizeTable() {
    this.capacity = capacity*2; //double capacity
    LinkedList<HashNode<KeyType, ValueType>> tempHashtable[]  
        = (LinkedList<HashNode<KeyType, ValueType>>[]) new LinkedList[capacity]; // created temp array
    for(int i = 0; i< hashtable.length; i++) { //loop through array
      if (hashtable[i] != null) { //check if elements are present
        for(HashNode<KeyType, ValueType> node: hashtable[i]) { 
          //if presents loop through linkedlist
          int hashKey = calculateHashKey(node.getKeyType()); //creates new hashkey in doubled loop
          LinkedList<HashNode<KeyType, ValueType>> templist = tempHashtable[hashKey];
          if (templist == null) { // check for existing linked list
            templist = new LinkedList<HashNode<KeyType, ValueType>>();
            //creates a new one if one is not found
          }
          templist.add(node); //adds node to temp linked list
          tempHashtable[hashKey] = templist; //inserts temp linked list into new hashtable map
          tempHashtable[calculateHashKey(node.getKeyType())].add(node); //add node to temp array
        }
      }
    }
    this.hashtable = tempHashtable; //set the main hashtable to it's new version
  }

}
