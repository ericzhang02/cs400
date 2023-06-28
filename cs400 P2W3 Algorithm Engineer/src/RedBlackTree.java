  // --== CS400 File Header Information ==--
  // Name: Eric Zhang
  // Email: erzhang2@wisc.edu
  // Team: BI
  // TA: Yuye
  // Lecturer: Gary Dahl
  // Notes to Grader: NONE
  
  import java.util.Iterator;
  import java.util.LinkedList;
  import java.util.NoSuchElementException;
  import java.util.Stack;
  
  interface toCompare{
    public Object get(Object x);  
  }
  
  /**
   * Red-Black Tree implementation with a Node inner class for representing
   * the nodes of the tree. Currently, this implements a Binary Search Tree that
   * we will turn into a red black tree by modifying the insert functionality.
   * In this activity, we will start with implementing rotations for the binary
   * search tree insert algorithm. You can use this class' insert method to build
   * a regular binary search tree, and its toString method to display a level-order
   * traversal of the tree.
   */
  public class RedBlackTree<T>{
  
    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
      public T data;
      public Node<T> parent; // null for root node
      public Node<T> leftChild;
      public Node<T> rightChild;
      public int blackHeight;
      public Node(T data) { 
        this.data = data; 
        blackHeight = 0;
      }
      /**
       * @return true when this node has a parent and is the left child of
       * that parent, otherwise return false
       */
      public boolean isLeftChild() {
        return parent != null && parent.leftChild == this;
      }
  
    }
  
    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree
    protected String sortedBy;
  
    public RedBlackTree(){  }
  
    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    public boolean insert(T item, toCompare getType) throws NullPointerException, IllegalArgumentException {
      if(item instanceof Item) {
        Node<T> newNode = new Node<>(item);
        if(root == null) { 
          root = newNode; size++;
          root.blackHeight = 1;
          return true; 
        } // add first node to an empty tree
        else{
          boolean returnValue = insertHelper(newNode,root, getType); // recursively insert into subtree
          if (returnValue) size++;
          else throw new IllegalArgumentException(
              "This RedBlackTree already contains that value.");
          root.blackHeight = 1;
          return returnValue;
        }
      }
      return false;
    }
  
    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree, toCompare getType) {
      if (newNode.data instanceof Item || subtree.data instanceof Item) {
        Comparable compare1 = (Comparable) (getType.get(newNode.data));
        Comparable compare2 = (Comparable)getType.get(subtree.data);
        int compare = (compare1).compareTo(compare2);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) {
          String compare3 = ((Item) newNode.data).getName();
          String compare4 = ((Item) subtree.data).getName();
          int comparestring = (compare3).compareTo(compare4);
          if(comparestring == 0) {
            return false;
          }
          else{
            return insertHelper(newNode, subtree, x -> ((Item) x).getName()); 
          }
        }
        // store newNode within left subtree of subtree
        else if(compare < 0) {
          if(subtree.leftChild == null) { // left subtree empty, add here
            subtree.leftChild = newNode;
            newNode.parent = subtree;
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
            // otherwise continue recursive search for location to insert
          } else return insertHelper(newNode, subtree.leftChild, getType);
        }
        // store newNode within the right subtree of subtree
        else {
          if(subtree.rightChild == null) { // right subtree empty, add here
            subtree.rightChild = newNode;
            newNode.parent = subtree;
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
            // otherwise continue recursive search for location to insert
          } else return insertHelper(newNode, subtree.rightChild, getType);
        }
      }
      return false;
    }
  
    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
      if(child.parent.leftChild != child && child.parent.rightChild != child) {
        throw new IllegalArgumentException("Nodes are not related");
      }
      Node<T> grandparent = parent.parent;
      //Case for if child node is smaller than parent node (child node is left node)
      if (child.isLeftChild()) {
        //creating the new parent/the child node that was rotated
        Node<T> childCopy = child;
        //setting the original parents leftchild to the original childs rightchild
        parent.leftChild = child.rightChild;
        //case for if original parent is the root node
        if (parent.parent == null) {
          root = childCopy;
        }
        //case for if original parent is a left child
        else if (parent.parent.leftChild == parent) {
          parent.parent.leftChild = childCopy;
        }
        else {
          //case for if original parent is a right child
          parent.parent.rightChild = childCopy;
        }
        //finally setting the childcopy right child as the original parent
        childCopy.rightChild = parent;
        childCopy.parent = grandparent;
        parent.parent = childCopy;
      }
      //Case for if child node is bigger than parent node (child node is right node)
      else if (!child.isLeftChild()) {
        //creating the new parent/the child node that was rotated
        Node<T> childCopy = child;
        //setting the original parents rightchild to the original childs leftchild
        parent.rightChild = child.leftChild;
        //case for if original parent is the root node
        if (parent.parent == null) {
          root = childCopy;
        }
        //case for if original parent is a right child
        else if (parent.parent.rightChild == parent) {
          parent.parent.rightChild = childCopy;
        }
        else {
          //case for if original parent is a left child
          parent.parent.leftChild = childCopy;
        }
        //finally setting the childcopy left child as the original parent
        childCopy.leftChild = parent;
        childCopy.parent = grandparent;
        parent.parent = childCopy;
      }
    }
  
    /**
     * ensures that the node satisfies all RBT requirements. If needed, this method fixes the issue
     * and then recursively calls on itself to continue fixing issues cause by prior issues
     * until all RBT requirement are met. 
     * @param node
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> node) {
      Node<T> parent = node.parent;
      Node<T> uncle = getUncle(node);
  
      //if node is root node
      if(node == root) {
        return;
      }
  
      //if node and parent node follow RBT rules
      if(!(node.blackHeight == 0 && parent.blackHeight == 0)) {
        return;
      }
  
      //if nodes parent is black
      if(node.parent.blackHeight == 1) {
        return;
      }
  
      //if parent is root
      if(node.parent.parent == null) {
        node.parent.blackHeight = 1;
        return;
      }
  
      //case if uncle is red
      if(uncle != null && uncle.blackHeight == 0) {
        node.parent.blackHeight = 1; //recolor
        getUncle(node).blackHeight = 1;
        node.parent.parent.blackHeight = 0;
        if(node.parent.parent != null) {
          enforceRBTreePropertiesAfterInsert(node.parent.parent);//fix tree from grandparent position
        }
        return;
      }
  
      //all cases after this are assuming uncle is black/null
      //case if nodes uncle is null/black & node and parent are opposite side children
      if (node.isLeftChild() != parent.isLeftChild()) {
        rotate(node, node.parent);
        if(node.leftChild != null) {
          enforceRBTreePropertiesAfterInsert(node.leftChild);
        }
        if(node.rightChild != null) {
          enforceRBTreePropertiesAfterInsert(node.rightChild);
        }     
        return;
      }
  
      //case if nodes uncle is null/black & node and parent are same side children
      if (node.isLeftChild() == parent.isLeftChild()) {
        rotate(node.parent, node.parent.parent);
        node.parent.blackHeight = 1;
        node.parent.leftChild.blackHeight = 0;
        node.parent.rightChild.blackHeight = 0;
      }
    }
  
    /**
     * private helper method to find the uncle of a given node
     * @param node
     * @return
     */
    private Node<T> getUncle(Node<T> node){
      Node<T> uncle = null;
      //getting the grandparents of the node
      if (node.parent != null) {
        Node<T> grandparent = node.parent.parent;
        if (grandparent != null) {
          //case for if parent is right node, so uncle is the left node
          if(grandparent.rightChild == node.parent) {
            uncle = grandparent.leftChild;
          }
          //case for if parent is left node, so uncle is the right node
          if(grandparent.leftChild == node.parent) {
            uncle = grandparent.rightChild;
          }
        } 
      }
      //returns null if uncle does not exist
      return uncle;
    }
  
    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
      return size;
    }
  
    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
      return this.size() == 0;
    }
  
    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data, toCompare getType) {
      // null references will not be stored within this tree
      if(data == null) throw new NullPointerException(
          "This RedBlackTree cannot store null references.");
      return this.containsHelper(data, root, getType);
    }
  
    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree, toCompare getType) {
      if (subtree == null) {
        // we are at a null child, value is not in tree
        return false;
      } else {
        if (data instanceof Item || subtree.data instanceof Item) {
          Comparable compare1 = (Comparable) (getType.get(data));
          Comparable compare2 = (Comparable)getType.get(subtree.data);
          int compare = (compare1).compareTo(compare2);
          if (compare < 0) {
            // go left in the tree
            return containsHelper(data, subtree.leftChild, getType);
          } else if (compare > 0) {
            // go right in the tree
            return containsHelper(data, subtree.rightChild, getType);
          } else {
            // we found it :)
            return true;
          }
        }
      }
      return false;
    }
  
    /**
     * Returns an iterator over the values in in-order (sorted) order.
     * @return iterator object that traverses the tree in in-order sequence
     */
    public Iterator<T> iterator() {
      // use an anonymous class here that implements the Iterator interface
      // we create a new on-off object of this class everytime the iterator
      // method is called
      return new Iterator<T>() {
        // a stack and current reference store the progress of the traversal
        // so that we can return one value at a time with the Iterator
        Stack<Node<T>> stack = null;
        Node<T> current = root;
  
        /**
         * The next method is called for each value in the traversal sequence.
         * It returns one value at a time.
         * @return next value in the sequence of the traversal
         * @throws NoSuchElementException if there is no more elements in the sequence
         */
        public T next() {
          // if stack == null, we need to initialize the stack and current element
          if (stack == null) {
            stack = new Stack<Node<T>>();
            current = root;
          }
          // go left as far as possible in the sub tree we are in un8til we hit a null
          // leaf (current is null), pushing all the nodes we fund on our way onto the
          // stack to process later
          while (current != null) {
            stack.push(current);
            current = current.leftChild;
          }
          // as long as the stack is not empty, we haven't finished the traversal yet;
          // take the next element from the stack and return it, then start to step down
          // its right subtree (set its right sub tree to current)
          if (!stack.isEmpty()) {
            Node<T> processedNode = stack.pop();
            current = processedNode.rightChild;
            return processedNode.data;
          } else {
            // if the stack is empty, we are done with our traversal
            throw new NoSuchElementException("There are no more elements in the tree");
          }
  
        }
  
        /**
         * Returns a boolean that indicates if the iterator has more elements (true),
         * or if the traversal has finished (false)
         * @return boolean indicating whether there are more elements / steps for the traversal
         */
        public boolean hasNext() {
          // return true if we either still have a current reference, or the stack
          // is not empty yet
          return !(current == null && (stack == null || stack.isEmpty()) );
        }
  
      };
    }
  
    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
      // use the inorder Iterator that we get by calling the iterator method above
      // to generate a string of all values of the tree in (ordered) in-order
      // traversal sequence
      Iterator<T> treeNodeIterator = this.iterator();
      StringBuffer sb = new StringBuffer();
      sb.append("[ ");
      if (treeNodeIterator.hasNext() || treeNodeIterator.next() instanceof Item)
        sb.append(((Item)treeNodeIterator.next()).getName());
      while (treeNodeIterator.hasNext()) {
        T data = treeNodeIterator.next();
        sb.append(", ");
        if(data instanceof Item) {
          sb.append(((Item)data).getName());
        }
      }
      sb.append(" ]");
      return sb.toString();
    }
  
    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
      String output = "[ ";
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this.root);
      while(!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if(next.leftChild != null) q.add(next.leftChild);
        if(next.rightChild != null) q.add(next.rightChild);
  
        if(next.data instanceof Item) {
          output += ((Item) (next.data)).getName().toString();
        }
        if(!q.isEmpty()) output += ", ";
      }
      return output + " ]";
    }
  
    @Override
    public String toString() {
      return  this.toInOrderString();
    }
  
    /**
     * This returns a Linkedlist containing the sorted Items
     * @return a Linkedlist of sorted Items
     */
    public LinkedList<Item> getList(){
      LinkedList<Item> temp = new LinkedList<>();
      Iterator<T> treeNodeIterator = this.iterator();
      if (treeNodeIterator.hasNext() || treeNodeIterator.next() instanceof Item)
        temp.add(((Item)treeNodeIterator.next()));      
      while (treeNodeIterator.hasNext()) {
        T data = treeNodeIterator.next();
        if(data instanceof Item) {
          temp.add((Item)data);
        }
      }
      return temp;
    }
  
  }