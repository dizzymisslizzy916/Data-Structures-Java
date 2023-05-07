// Liz Crittenden
// Project 4 CSC 103

// File: TreeBag.java 

// The implementation of most methods in this file is left as a student
// exercise from Section 9.5 of "Data Structures and Other Objects Using Java"


/******************************************************************************
* This class is a homework assignment;
* An <CODE>TreeBag</CODE> is a collection of int numbers.
*
* <dl><dt><b>Limitations:</b> <dd>
*   Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
*   and <CODE>size</CODE> are wrong. 
*
* <dt><b>Note:</b><dd>
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @version
*   Jan 24, 2016
******************************************************************************/
public class TreeBag<E extends Comparable> implements Cloneable
{
   // The Term E extends Comparable is letting the compiler know that any type
   // used to instantiate E must implement Comparable. i. e. that means that whatever
   // type E is must have a compareTo method so that elements can be compared against one another
   // This is required becuase we are doing comparisons in our methods


   // Invariant of the TreeBag class:
   //   1. The elements in the bag are stored in a binary search tree.
   //   2. The instance variable root is a reference to the root of the
   //      binary search tree (or null for an empty tree).
   private BTNode<E> root;   


   /**
   * Insert a new element into this bag.
   * @param <CODE>element</CODE>
   *   the new element that is being inserted
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this bag.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory a new BTNode.
   **/
   public void add(E element)
   {  
      BTNode <E> cursor;
      BTNode <E> newNode;
      boolean done = false;
      if(root==null){  
         root = new BTNode(element, null, null); //If root does not exist, create root with element as data
      } else {
         cursor = root;//set cursor to root
         while(!done){
            if(element.compareTo(cursor.getData()) <= 0){ //Move cursor left within the tree
               if(cursor.getLeft()==null){ //If cursor has no left child
                  cursor.setLeft(new BTNode<E>(element, null, null)); // create node with element as data
                  done = true; //End while loop
               } else {
                  cursor = cursor.getLeft(); //Keep advancing left
               }
            } else { //Move cursor right within the tree
               if(cursor.getRight()==null){ 
                  cursor.setRight(new BTNode<E>(element, null, null));
                  done = true;
               } else {
                  cursor = cursor.getRight();
               }
            }
        }
     } 
   }

   /**
   * Retrieve location of a specified element from this bag.
   * @param <CODE>target</CODE>
   *   the element to locate in the bag
   * @return 
   *  the return value is a reference to the found element in the tree
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then method returns
   *   a reference to a comparable element. If the target was not found then
   *   the method returns null.
   *   The bag remains unchanged.
   **/
   public E retrieve(E target)
   {
      BTNode <E> cursor;
      boolean flag = true;
      cursor = root;
      while(flag == true && cursor != null){
         if(target.compareTo(cursor.getData())==0){ //Use compareTo method to find node containing target element
            flag = false;
            return cursor.getData();
         }else if(target.compareTo(cursor.getData()) < 0){ //If not found, advance left
            cursor = cursor.getLeft();
         }else if(target.compareTo(cursor.getData()) > 0){ //If not found, advance right
            cursor = cursor.getRight();
         }
     }      
     return null;
   }

   
   /**
   * Remove one copy of a specified element from this bag.
   * @param <CODE>target</CODE>
   *   the element to remove from the bag
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then one copy of
   *   <CODE>target</CODE> has been removed and the method returns true. 
   *   Otherwise the bag remains unchanged and the method returns false. 
   **/
   public boolean remove(E target)
   {
      BTNode <E> cursor;
      BTNode <E> parentOfCursor;
      cursor = root;
      parentOfCursor = null;
      if(cursor==null || target == null){ //If target is not found
         System.out.println("The player you entered isn't within this database.");
         return false;
      }
      while(cursor != null){
         if(target.compareTo(cursor.getData()) < 0 ){ //Advance cursor left
            parentOfCursor = cursor; //Set parent to previous node
            cursor = cursor.getLeft();
         } else if(target.compareTo(cursor.getData()) > 0 ){ //Advance cursor right
            parentOfCursor = cursor;
            cursor = cursor.getRight();
         } else if(target.compareTo(cursor.getData())==0){//Indicates target has been found
            if(cursor.isLeaf()){//If target is a leaf
               parentOfCursor.setLeft(null);//Removes links to target
               parentOfCursor.setRight(null);
            } else if(cursor.getLeft()==null){ //If target has no left child
               if(cursor== parentOfCursor.getLeft()) 
                  parentOfCursor.setLeft(cursor.getRight());//Sets link of remaining child to parent
            } else if (cursor.getRight()==null){ //If target has no right child
                if(cursor== parentOfCursor.getRight())
                  parentOfCursor.setRight(cursor.getLeft());
            } else if(cursor.getLeft()!=null && cursor.getRight() != null){//If target has two children
               cursor.setData(cursor.getLeft().getRightmostData());//Set links of both children to parent
               cursor.setLeft(cursor.getLeft().removeRightmost());
            }
            return true;
         }
      }
      return false;
   }
   
   /**
   * Displays the entire tree of Node elements in a order specified
   * by the elements compareTo method
   * 
   * @param 
   *   none
   * <dt><b>Postcondition:</b><dd>
   *   Outputs all elements in the tree to Screen.
   *   Does not change the structure 
   **/
   public void display()
   {
      if(root != null)
         root.inorderPrint();
   } 
     
   /**
   * Displays the entire tree of Node elements using the
   * built in print method of BTNode
   * which displays the entire tree in tree format
   * 
   * @param 
   *   none
   * <dt><b>Postcondition:</b><dd>
   *   Outputs all elements in the tree to Screen.
   *   Does not change the structure 
   **/   
   public void displayAsTree() {
      if (root == null)
         throw new IllegalArgumentException("The tree is empty");
      root.print(0);
   }
      
   
   
   /**
   * Generate a copy of this bag.
   * @param - none
   * @return
   *   The return value is a copy of this bag. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>TreeBag</CODE> before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public TreeBag<E> clone( )
   {  // Clone an IntTreeBag object.
      // Student will replace this return statement with their own code:
      return null; 
   } 

   /**
   * Accessor method to count the number of occurrences of a particular element
   * in this bag.
   * @param <CODE>target</CODE>
   *   the element that needs to be counted
   * @return
   *   the number of times that <CODE>target</CODE> occurs in this bag
   **/
   public int countOccurrences(E target)
   {
      // Student will replace this return statement with their own code:
      return 0;
   }
   
       
   /**
   * Determine the number of elements in this bag.
   * @param - none
   * @return
   *   the number of elements in this bag
   **/                           
   public int size( )
   {
      return BTNode.treeSize(root);
   }




   /**
   * Add the contents of another bag to this bag.
   * @param <CODE>addend</CODE>
   *   a bag whose contents will be added to this bag
   * <dt><b>Precondition:</b><dd>
   *   The parameter, <CODE>addend</CODE>, is not null.
   * <dt><b>Postcondition:</b><dd>
   *   The elements from <CODE>addend</CODE> have been added to this bag.
   * @exception IllegalArgumentException
   *   Indicates that <CODE>addend</CODE> is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of the bag.
   **/
   public void addAll(TreeBag<E> addend)
   {
      // Implemented by student.
   }
   
   /**
   * Create a new bag that contains all the elements from two other bags.
   * @param <CODE>b1</CODE>
   *   the first of two bags
   * @param <CODE>b2</CODE>
   *   the second of two bags
   * <dt><b>Precondition:</b><dd>
   *   Neither b1 nor b2 is null.
   * @return
   *   the union of b1 and b2
   * @exception IllegalArgumentException
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new bag.
   **/   
   public static TreeBag union(TreeBag b1, TreeBag b2)
   {
      // Student will replace this return statement with their own code:
      return null;
   }
     
  /**
  * Accessor for root of the treeBag
  * @param none
  * @return
  *   the root node of the treeBag
  **/
   public BTNode setRoot(){
      return root; 
   }
}
           