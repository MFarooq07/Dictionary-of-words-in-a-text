package proj5;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 *
 * This class test the various public methods in the BinarySearchTree class in different cases
 *
 * @author Muhammad Farooq Memon
 * @version 03/09/23
 *
 */

public class MuhammadFarooqMemonBSTTest {

    @Rule
    public Timeout timeout = Timeout.millis(10000);

    @Test
    public void testInsertANDToString() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

        bst.insert(5);
        assertEquals("(  5  )", bst.toString());
        bst.insert(7);
        assertEquals("(  5  (  7  ))", bst.toString());
        bst.insert(4);
        assertEquals("((  4  )  5  (  7  ))", bst.toString());
        bst.insert(6);
        assertEquals("((  4  )  5  ((  6  )  7  ))", bst.toString());
        bst.insert(3);
        assertEquals("(((  3  )  4  )  5  ((  6  )  7  ))", bst.toString());
    }

    @Test
    public void testSearch() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        assertFalse(bst.search(5));   //searching in case of empty tree
        bst.insert(5);
        assertTrue(bst.search(5));   //searching in case of root ONLY
        bst.insert(9);
        bst.insert(3);
        bst.insert(6);
        assertTrue(bst.search(6));   //searching in case of multiple numbers
        bst.insert(2);
        bst.insert(100);
        bst.insert(13);
        bst.insert(61);
        assertTrue(bst.search(61));  //searching in more complex tree
    }

    @Test
    public void testDelete() {
        //deleting the only element in the list
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(5);
        bst.delete(5);
        assertEquals("", bst.toString());

        // deleting node that only has a right subtree
        bst = new BinarySearchTree<Integer>();
        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(14);
        bst.delete(10);
        assertEquals("(((1)3((4)6(7)))8(14))", bst.toString().replaceAll("[ ]+", ""));

        //deleting an element that does not exist in a tree
        bst = new BinarySearchTree<Integer>();
        bst.insert(5);
        bst.delete(6);
        assertEquals("(  5  )", bst.toString());

        //deleting a node that only has a left subtree
        bst = new BinarySearchTree<Integer>();
        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(14);
        bst.delete(3);
        assertEquals("((1((4)6(7)))8(10(14)))", bst.toString().replaceAll("[ ]+", ""));

        // deleting a leaf node that is not the root
        bst = new BinarySearchTree<Integer>();
        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(14);
        bst.delete(7);
        assertEquals("(((1)3((4)6))8(10(14)))", bst.toString().replaceAll("[ ]+", ""));

        //deleting the root node which has both the right and left subtrees
        bst = new BinarySearchTree<Integer>();
        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(14);
        bst.delete(8);
        assertEquals("(((1)3((4)6))7(10(14)))", bst.toString().replaceAll("[ ]+", ""));

        // deleting just the first occurrence of the target value
        bst = new BinarySearchTree<Integer>();
        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(14);
        bst.insert(8);
        bst.delete(8);
        assertEquals("(((1)3((4)6(7)))8(10(14)))", bst.toString().replaceAll("[ ]+", ""));

        bst = new BinarySearchTree<Integer>();
        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(14);
        bst.insert(3);
        bst.delete(3);
        assertEquals("(((1)3((4)6(7)))8(10(14)))", bst.toString().replaceAll("[ ]+", ""));
    }
}


