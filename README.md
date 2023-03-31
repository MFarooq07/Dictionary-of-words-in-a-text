<h1>Automatic Index Creator <h1/>
<h2> Introduction </h2>
At the end of many books is an index that tells you on which pages you can find certain keywords. Most modern word processors can automatically create an index for you by scanning the text, picking out significant words, and keeping track of the pages on which those words occur.

  <h2>My Mission</h2>
My goal is to write a program that will automatically create such an index for any given text file. One issue to consider is what to do with words like "an" and "the" that occur very frequently. We'll handle that using two techniques.

First, if a given word is 2 characters or less, we'll ignore it completely. This gets rid of words like "a" and "an" from the index.
Second, we'll keep a dictionary of words that we don't want to place in the index. For every word in the text, we first check the dictionary to see if the word occurs there. If the word is not in the dictionary, we'll place it in the index. Words will be placed into the dictionary if they occur on too many pages. For example, once we've seen "the" on, say, 5 different pages, we'll remove it from the index and add it to the dictionary. All subsequent occurrences of "the" will then be ignored.
Note: this dictionary has the same functionality as the Python construct of the same name, but we're going to implement it differently. Another issue is that for each word in the index, we must keep track of the page(s) on which it occurs. Therefore, each index word will have an associated pagelist, which lists the page numbers on which that word is found. Each pagelist will be able to hold 4 page numbers. Thus, the 5th attempt to insert a page number into a given pagelist will find the list already full. When this happens, we'll know to delete that word from the index and place it into the dictionary. We also want to make sure that if we see two occurrences of the same word on the same page, we don't want to insert the same page number into the word's pagelist twice.

  <h2>The Data Structures</h2>
For this project, both the dictionary and the index will be searched a lot. The dictionary gets searched once for every word scanned, and if the word isn't found, the index will be searched to see if the current word is already in the index. So an ADT with a good running time for searches is preferable. A binary search tree is a good choice since it has an average case running time of O(log n) for the search operation, which is better than the O(n) average search time for an array or linked list.

  <h3>We'll use two binary search trees:</h3> one for the index and one for the dictionary. I was required to use the linked structure implementation of a binary search tree for this project (i.e. not an array).

  <h2>Now for the pagelists</h2>
 At the end of the program, we'll want to print the pagelist for each word with the page numbers in sorted order. But we're already going to encounter the pages in sorted order since we'll be scanning each word starting from the beginning of the text file. Thus we just need to save the pages in the same order that we encounter them.

For this, I have chosen a linked list as an appropriate ADT for the pagelists. A linked list provides good performance for inserting or deleting elements from the list at any position. Since the order of page numbers is already sorted, we don't need to sort them again. However, linked lists can be inefficient for searching, which may not be a problem for the current project since we don't need to search the





