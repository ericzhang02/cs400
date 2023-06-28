public interface IRedBlackTree<T>{
    // Note that the provided iterators step through the data within this
    // collection in sorted order, as defined by their compareTo() method.

    public boolean insert(T item) throws NullPointerException, IllegalArgumentException;

    public boolean contains(T data);

    public int size();

    public boolean isEmpty();


}