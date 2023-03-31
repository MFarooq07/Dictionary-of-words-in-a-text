package proj5;

/**
 * Implementation of the binary search tree where we can do various things
 * with it like inserting, deleting and searching.
 * @author Muhammad Farooq Memon
 * @version 3/15/2023
 */

public class BinarySearchTree<T extends Comparable<T>> {


	private BSTNode<T> root;


	/**
	 * Default constructor
	 */
	public BinarySearchTree() {
		root = null;
	}


    /**
	 * inserts a new value into this BST
	 * @param newValue value to insert
	 */
	public void insert(T newValue) {
		root = insert(root,newValue);
	}


	/**
	 * inserts value into tree rooted at subroot
	 * 
	 * @param subroot  subroot of tree to insert into
	 * @param value  the value to insert
	 * @return   root of the subtree I've just finished inserting into
	 */
	private BSTNode<T> insert(BSTNode<T> subroot, T value) {
		if (subroot==null){
			return new BSTNode<T>(value);
		}
		else if (value.compareTo(subroot.key) > 0){
			subroot.rlink = insert(subroot.rlink,value);
			return subroot;
		}
		else {
			subroot.llink = insert(subroot.llink, value);
			return subroot;
		}
	}


	/**
	 * deletes value from tree.  If value not there, do nothing.
	 * @param value  value to delete
	 */
	public void delete(T value) {
		if (search(value)) {
			root = delete(root, value);
		}
	}


	/**
	 * deletes value from tree rooted at subroot
	 * @param subroot  root of tree to be deleted from
	 * @param value  element to delete
	 * @return pointer to tree rooted at subroot that has value removed from it
	 */
	public BSTNode<T> delete(BSTNode<T> subroot, T value) {
		/**
		 * if subroot is an empty tree
		 *
		 *     return null
		 * else if victim is on the left of subroot
		 *     subroot's left link must become what recursion on subroot's left child gives you
		 * else if victim is on the right of subroot
		 *     subroot's right link must become what recursion on subroot's rlink gives you
		 * else
		 *     victim is found!
		 *     case 1) victim is a leaf
		 *         return null
		 *     case 2) victim has exactly one (right) subtree
		 *         return pointer to that right subtree
		 *     (case 2a - take care of just left subtree only)
		 *     case 3) victim has two subtrees
		 *         pick a replacement (largest value in the left subtree)
		 *         move the data from replacement node to victim node
		 *         delete the replacement
		 */

		if (subroot == null) {
			return null;
		}
		else if (value.compareTo(subroot.key) < 0) {
			subroot.llink = delete(subroot.llink, value);
			return subroot;
		}
		else if (value.compareTo(subroot.key) > 0) {
			subroot.rlink = delete(subroot.rlink, value);
			return subroot;
		}
		else {
			if (subroot.llink == null && subroot.rlink == null) {
				return null;
			}
			else if (subroot.llink == null) {
				return subroot.rlink;
			}
			else if (subroot.rlink == null) {
				return subroot.llink;
			}
			else {
				BSTNode<T> replacement = findMax(subroot.llink);
				subroot.key = replacement.key;
				subroot.llink = delete(subroot.llink, replacement.key);
				return subroot;
			}
		}
	}


	/**
	 * The following function finds the node with Maximum value
	 * in a certain part of the tree that is on right of a node
	 * @param node the node which might have nodes bigger than it or not
	 * @return returns the maximum node in a subtree
	 */
	private BSTNode<T> findMax(BSTNode<T> node) {
		while (node.rlink != null) {
			node = node.rlink;
		}
		return node;
	}


    /**
     * checks whether the target value is in the tree
     * @return true or false to indicate whether the target value is in the tree
     */
    public boolean search(T target) {
        return search(root, target);
	}


	/**
	 * recursive helper for search method on line 144
	 * @param subroot the root of the bst
	 * @param value target value to search for
	 * @return true or false if it's in the bst
	 */
	private boolean search(BSTNode<T> subroot, T value) {
		if( subroot == null){
			return false;
		}
		else if (value.compareTo(subroot.key )== 0){
			return true;
		}
		else if(value.compareTo(subroot.key) > 0){
			return search(subroot.rlink, value);
		}
		else if(value.compareTo(subroot.key) < 0){
			return search(subroot.llink, value);
		}
		return false;
	}


	/**
	 * Get the value in a binary search tree
	 * @param target the target value to get the contents of
	 * @return the value
	 */
	public T getElement(T target) {
		return getElement(root, target);
	}


	/**
	 * recursive helper to find the value in a binary search tree
	 * @param rootNode root node in bst
	 * @param target target value to find in bst
	 * @return the target value
	 */
	private T getElement(BSTNode<T> rootNode, T target) {
		if (rootNode == null) {
			return null;
		}
		else if (rootNode.key.compareTo(target) == 0) {
			return rootNode.key;
		}
		else if (target.compareTo(rootNode.key) > 0) {
			return getElement(rootNode.rlink, target);
		}
		else {
			return getElement(rootNode.llink, target);
		}
	}


	/**
	 * returns tree as printable string
	 * @return tree in string format with form (left subtree) value (right subtree)
	 */
	public String toString(){
		return toString(root);
	}


	/**
	 * recursive helper method for toString()
	 *
	 * @param N root of subtree to make into a string
	 * @return string version of tree rooted at N
	 */
	private String toString(BSTNode<T> N){
		String toReturn = "";
		if (N != null){
			toReturn += "(";
			toReturn += toString(N.llink);
			toReturn += "  " + N + "  ";
			toReturn += toString(N.rlink);
			toReturn += ")";
		}
		return toReturn;
	}


	/**
	 * returns tree as printable string without parentheses
	 * @return tree in string format with form (left subtree) value (right subtree)
	 */
	public String toString2() {
		return toString2(root);
	}


	/**
	 * Recursive method for toString for each of the values
	 * @param N
	 * @return the value
	 */
	private String toString2(BSTNode<T> N) {
		String toReturn = "";
		if (N != null) {
			toReturn += toString2(N.llink);
			toReturn += "\n" + N + "";
			toReturn += toString2(N.rlink);
		}
		return toReturn;
	}

}