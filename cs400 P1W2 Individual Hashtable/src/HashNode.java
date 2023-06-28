// --== CS400 Project One File Header ==--
// Name: Eric Zhang
// CSL Username: ericz
// Email: erzhang2@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: 
public class HashNode<KeyType, ValueType> {
  
  private KeyType key;
  private ValueType value;
  
  public HashNode(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }
  
  public ValueType getValueType(){
    return value;
  }

  public KeyType getKeyType() {
    return key;
  }
}
