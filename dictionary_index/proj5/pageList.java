package proj5;

/**
 * Every pageList is a native array implementation.
 * Each pageList is an array of Integers, of size 4.
 *
 * The pageList has the following instance variables: the Integer array as Numbers, String word for input, and the
 * size of the array.
 *
 * The Integer array keeps the record of the pages that a word appears on in the Client. When the word appears
 * on a page, the page number is positioned into the array until the array gets full. The size could be
 * less than and retrieved based on the current number of page numbers put into the array for holding. In addition,
 * the page numbers are all sorted in order from least to greatest in the pageList array. If there's another time
 * that the page number is found beyond the 4 times, it will no longer place add a number or a space. The array
 * remains fixed at size 4.
 *
 * The string word for input is the word that the array carries alongside inside the Index binary search tree. This
 * word is associated with the page numbers that it appears in the input text files, and it's necessary for toString
 * printouts inside the pageList class.
 *
 * The size of the array is the size at which the array is at currently. If the array only has one page number, it's
 * size 1, and while it's added a plus one, with a method that adds page numbers to the array, it keeps the toString
 * and getSize method working.
 */

public class pageList implements Comparable<pageList> {

    private Integer[] Numbers;
    private String word;
    private int size;
    private static final int MAX = 4;
    private static final int GREATER = 1;
    private static final int LESSER = -1;
    private static final int EQUAL = 0;
    private static final int STARTING_SIZE = 0;
    private int index = 0;


    /**
     * Constructor
     * @param newWord the word from .txt files
     */
    public pageList(String newWord) {
        this.Numbers = new Integer[MAX];
        this.word = newWord;
        this.size = STARTING_SIZE;
    }


    /**
     * Comparable method for satisfying the comparable requirement in the interface
     * @param page the object to be compared.
     * @return an int based on larger, smaller, or equal to
     */
    public int compareTo(pageList page) {
        if (this.getWord().compareTo(page.getWord()) > 0){
            return GREATER;
        }
        else if (this.getWord().compareTo(page.getWord()) < 0){
            return LESSER;
        }
        else {
            return EQUAL;
        }
    }


    /**
     * Add pages to the array
     * @param pageNumber the page number
     */
    public void addPages(int pageNumber) {
        addaPages(pageNumber);
    }


    /**
     * method to help addPages above in adding pages to the array
     * @param somePage a page number to add
     */
    private void addaPages(int somePage) {
        if (getSize() > 0 && getSize() < MAX) {
            index += 1;
            this.Numbers[index] = somePage;
        }
        else {
            this.Numbers[STARTING_SIZE] = somePage;
        }
        size++;
    }


    /**
     * Gets a word inside a pageList
     * @return word
     */
    public String getWord() {
        return this.word;
    }


    /**
     * Find if the array is full or not.
     * @return true or false based on whether the array is full or not
     */
    public boolean isFull() {
        return size == MAX;
    }


    /**
     * Provides the size of the array
     * @return the size of the array
     */
    public int getSize() {
        return size;
    }


    /**
     * Searches the array for a specified value
     * @param target the target/specified value to be looked in an array
     * @return true or false
     */
    public boolean searchArray(int target) {
        return presInArray(target);
    }


    /**
     * helper method to find if a target is in the array
     * @param target target value
     * @return true or false based on whether an element is in the array or not
     */
    private boolean presInArray(int target) {
        for (int iter = 0; iter < getSize(); iter++) {
            int someNum = this.Numbers[iter];
            if (someNum == target) {
                return true;
            }
        }
        return false;
    }


    /**
     * Determine if the array is empty or not
     * @return true or false depending on whether it is empty or not
     */
    public boolean isEmpty() {
        if (getSize() == STARTING_SIZE) {
            return true;
        }
        return false;
    }


    /**
     * toString for the pageList
     * @return String version/representation of pageList
     */
    public String toString() {
        if (this.isEmpty()) {
            return this.word + "{}";
        }
        else {
            String ret = "{";
            for (int starter = 0; starter < size; starter++) {
                Integer page = this.Numbers[starter];
                if (page != null) {
                    if (starter == size - 1) {
                        ret += page + "}";
                    }
                    else {
                        ret += page + ", ";
                    }
                }
            }
            return this.word + " " + ret;
        }
    }
}
