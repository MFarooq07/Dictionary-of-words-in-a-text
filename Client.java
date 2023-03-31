package proj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver for the index maker project
 * Name: Muhammad Farooq Memon
 * @version 3/15/2023
 */
public class Client {


    private static final int IGNORABLE_LENGTH = 2;
    /**
     * main function
     * @param args default parameter
     */
    public static void main(String[] args) {
    	makeIndex("input.txt"); //replace with "C:/Users/mfaro/Project_5/src/proj5/input.txt" correct path
    }


    /**
     * Makes an index out of fileName.
     * @param fileName path to text file e.g C:/Users/mfaro/Project_5/src/proj5/input.txt
     */
    public static void makeIndex(String fileName) {
        BinarySearchTree<String> Dictionary = new BinarySearchTree<String>();
        BinarySearchTree<pageList> Index = new BinarySearchTree<pageList>();

        try {
            Scanner myReader;
            myReader = new Scanner(new File(fileName));
            myReader.useDelimiter("[^a-zA-Z#]+");

            int pageTracker = 1;

            while (myReader.hasNext()) {
                String nextExpression = myReader.next();
                if (nextExpression.equals("#")) {
                    pageTracker++;
                }

                if (nextExpression.length() > IGNORABLE_LENGTH) {
                    if (! Dictionary.search(nextExpression)) {
                        pageList arrayPgNums = new pageList(nextExpression);
                        pageList someWordPages = Index.getElement(arrayPgNums);

                        if (someWordPages != null) {
                            if (!someWordPages.searchArray(pageTracker)) {
                                if (!someWordPages.isFull()) {
                                    someWordPages.addPages(pageTracker);
                                }
                                else {
                                    String wordAndPageList = someWordPages.toString();
                                    System.out.println(wordAndPageList);

                                    Index.delete(someWordPages);
                                    String deletingString = "Deleting '" + someWordPages + "' from index.";
                                    System.out.println(deletingString);

                                    Dictionary.insert(nextExpression);
                                }
                            }
                        }
                        else {
                            arrayPgNums.addPages(pageTracker);
                            Index.insert(arrayPgNums);
                        }
                    }
                }
            }
            String indexString = Index.toString2();
            System.out.println("Index:" + indexString);

            String dictionaryString = Dictionary.toString2();
            System.out.println("Dictionary:" + dictionaryString);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
