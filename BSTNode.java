package proj5;

/**
 * A node in a BinarySearchTree.
 * 
 * @author Muhammad Farooq Memon
 * @version 3/16/2023
 */
public class BSTNode<T>
{
    public T key;
    public BSTNode<T> llink;
    public BSTNode<T> rlink;
    
    public BSTNode(T data){
    	key=data;
    	llink=null;
    	rlink=null;
    }
    
    public String toString() {
    	return "" + key;
    }
    
    public boolean isLeaf() {  //on the edge of the tree
    	return this.llink == null && this.rlink == null;
    }
    
    public boolean hasRightChildOnly() {
    	return this.llink == null && this.rlink != null;
    }
    
    public boolean hasLeftChildOnly() {
    	return this.llink != null && this.rlink == null;
    }
                              
}
