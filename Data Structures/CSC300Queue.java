package homework;

import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.Trace;
//	Patrick Markowski
//	CSC 300-501

//  DS I Homework 7     version 1.0
//  A queue is implemented using a singly linked list.
//  the variable: back "points" at the first node in the linked list
//      new elements ( enqueued) are added at the back
//  the variable: front "points" at the last node in the linked list.
//         elements are removed (dequeued) from the front
//   this is the OPPOSITE of the implementation we reviewed in class; the one in the book
//      This is NOT a good way to implement a queue, but makes a good exercise
//      in practicing coding with linked lists
//    
//      You may not use any other Java classes or algorithms in completing the ToDo items
//      You may not add any instance variables to the class
//       You may add your own private instance methods for modularity sake
//
public class CSC300Queue {

	private Node front, back;

	static boolean showMeSuccess = false;  // change to true to also show tests that pass

	static class Node {
		public Node (char item, Node next) { this.item = item; this.next = next; }
		public char item;
		public Node next;
	}

	// isEmpty
	// determine if the queue is empty
	// since back is the first node in the linked list, if back is null - there is no linked list, hence the queue is empty
	public boolean isEmpty() {
		return back==null;
	}

	// frontItem
	// return the item at the front of the queue without removing it
	// precondition: queue is not empty      
	public char frontItem() {
		if ( back == null)   // there are no nodes - so the queue is empty, client failed!
			throw new NoSuchElementException("there is no front item in an empty queue!");
		else if  (front == null)    // this should not happen, if so the programmer failed!
			throw new NoSuchElementException("an internal error has occurred in CSC300Queue: null front pointer");
		else
			return front.item;  
	}

	// backItem
	// return the item at the back of the queue without altering the queue
	// precondition: queue is not empty
	//
	// this example illustrates checking to see if the precondition is satisfied and
	//     handling the error if it is not
	//        
	public char backItem() {
		if ( back == null)   // queue is empty, user failed!
			throw new NoSuchElementException("the queue is empty!");
		else
			return back.item;
	}
	// enqueue
	// add an item to the back of the queue
	public void enqueue( char item) {

		Node  aNode = new Node( item, back);   // create new node with item  AND
		// tack existing queue onto end of new node using constructor

		if ( back == null) {                                            // queue was empty
			back = front = aNode;                                //  so the new node is both front and back  
		}
		else																	// new node is now at the 'back'
			back = aNode;
	}


	// dequeue
	// a function to remove and return the item at the front of the queue
	// precondition:  the queue is not empty
	// You do not need to throw exceptions for precondition violations
	//
	public char dequeue() {
		char result=front.item;
		
		for (Node t = back; t.next!=null;t=t.next) {
			if (t.next==front) {
				t.next=null;
				front=t;
				return result;
		}
		}
		back = front =null;
		
		return result;   
	}

	// sizeIterative
	// Here is an iterative version of a function to compute the size of the queue
	//  an empty list has size 0
	public int sizeIterative () {
		int count =0;
		for (Node tmp = back;  tmp != null; tmp = tmp.next) {
			count++;
		}
		return count;
	}

	// sizeRecursive
	// a function to compute the size of the queue using recursion
	// empty queue has size 0
	// You will want to create a helper function to do the recursion
	public int sizeRecursive () {
		if (isEmpty())
			return 0;
		Node t = back;
		return sizeHelper(t); //TODO 2: fix this
	}
	private int sizeHelper(Node t) {
		if (t==null)
			return 0;
		
		return 1 + sizeHelper(t.next);
		
	}
	// toString
	// the function   toString    returns a single String consisting of all the characters in the queue.
	// the leftmost character in the returned string should be the character at the FRONT of the queue
	// Example;  if the linked list representation is:  back-->  'a'  'b'  'c'  'd'   <-- front
	//  the returned string would be "dcba"
	//  a normal traversal of the linked structure from back to front would give us the string we want REVERSED
	//
	//   You may not use any other Java classes ( e.g.  ArrayList, ) to solve this.
	//   You may use basic java arrays and methods of the String class
	//
	//    Hint1:  you can build a String by starting with the empty string and the concatenating characters as shown below
	//              String x ="";
	//               x = x + 'q';     // adds 'q' onto the right end of String x
	//    Hint2:  recursion is one way to handle the reversal issue

	public String toString() {
		String result ="";
		for (Node t = back; t!=null;t=t.next) {
			result = t.item + result;
		}
		return result;       //    ToDo 3   fix this

	}


	// removeEveryKthNode
	// 
	//  this function will remove every kth element in the original queue 
	//
	//  your function should start at the BACK of the queue and work toward the front.
	//  here are some conceptual examples ( they also mirror what your linked lists will look like)
	//  the back is on the left and the front is on the right.
	//  Example 1.    A  B  C  D  E  F  G  H  I J,  k = 2   
	//     Result:   original queue is now:   A C E G I
	// Example 2.    A  B  C  D  E  F  G  H  I J,  k = 3 
	//     Result:   original queue is now:   A B D E G H J
	//  
	//  Your solution must work directly on the underlying linked structure
	//  You may not use any other containers, arrays or Java classes to solve the problem
	//  precondition:  k >= 1

	public void removeEveryKthNode (int k) {
		int ct=0;
		if (isEmpty())
			return;
		
		if (k==1) {
			back = front =null;
			return;
		}
		
		for (Node t=back;t.next!=null;t=t.next) {
			ct++;
			if (t.next.next==null && ct==k-1) {
				t.next=null;
				return;
			}
			if(ct==k-1) {
			t.next=t.next.next;
			ct=0;
			
			}
				
			
			
		}
		}
		// toDo 4   remove every kth element from the invoking queue 
	
	// isOrdered   
	// 
	//  return true if the queue contents are in increasing order (back to front)  
	//  Queue may be empty ; in which case the function should return true
	//  You must solve this USING RECURSION; you will need to use a helper function
	//   You may not use:
	//             any other Java classes, algorithms, 
	//             the toString instance method
	//    You may not alter the invoking queue
	// 
	// here are some conceptual examples ( they also mirror what your linked lists will look like)
	//  the back is on the left and the front is on the right.
	//  Example 1.    A  B  C  D  E  F  G  H  I J    -->  answer  true   
	//  Example 2.    C B A                          -->  answer  false
	//  Example 3.    C                              -->  answer  true
	//  
	public  boolean isOrdered ( ) {
		if (back.next==null)
			return true;
		char z = back.item;
		Node t = back.next;
		
		boolean result =orderedRecur(t,z) ;   //TODO  5


		return result; //TODO 5: fix this
	}
	private boolean orderedRecur(Node t, char z) {
		if (t==null)
			return true;
		if (z>t.item)
			return false;
		z=t.item;
		return orderedRecur(t.next,z);
	}

	


	// nothing for you to change below here except debugging options

	// for debugging purposes, you may comment/uncomment the two calls in main below 
	// you should restore the call to mainRunTests as below when you submit your solution
	public static void main( String[] args) {
		//mainDebug();
		mainRunTests();
	}
	private static void mainDebug () {
		// Use this for debugging!
		// Add the names of helper functions if you use them.
		Trace.drawStepsOfMethod ("sizeRecursive");
		Trace.drawStepsOfMethod ("toString");
		Trace.drawStepsOfMethod ("removeEveryKthNode");
		Trace.drawStepsOfMethod ("dequeue");
		Trace.run (); 
		// To Use:  Put the test here you want to debug:
		testRemoveEveryKthNode( "abcdef",3,"abde"); // source queue data, value of k

	}
	private static void mainRunTests () {

		testDequeue("abcd",'d','c');    //  source data, dequeued value, new front value
		testDequeue("abc",'c','b');
		testDequeue("ab",'b','a');
		testDequeue("a",'a',' ');

		testSizeRecursive (0, "");       // expected queue size,  queue source data
		testSizeRecursive (1, "a");
		testSizeRecursive (2, "ab");	
		testSizeRecursive (4, "abcd");

		testToString("a","a");   		 	// queue source , expected value of toString
		testToString("ab","ba");
		testToString("abc","cba");
		testToString("abcd","dcba");

		testRemoveEveryKthNode( "abcdef",2,"ace");             // source queue data, value of k, expected queue
		testRemoveEveryKthNode( "abcdef",3,"abde");
		testRemoveEveryKthNode( "abcdefg",2,"aceg"); 
		testRemoveEveryKthNode( "abcdefgh",3,"abdegh"); 
		testRemoveEveryKthNode( "abcdefghijkl",4,"abcefgijk"); 
		testRemoveEveryKthNode("abcdef",1,"");

		testIsOrdered("abcde",true);											 // source queue data, expected answer
		testIsOrdered("eabcd",false);
		testIsOrdered("aebcd",false);
		testIsOrdered("abecd",false);
		testIsOrdered("abced",false);
		testIsOrdered("a",true);
		testIsOrdered("ba",false);
	}

	// lots of copy and paste in these tests!

	private static void testSizeRecursive (int expected, String qData) {
		CSC300Queue aQueue = CSC300Queue.of (qData);  // create queue from string data using *of* function

		int actual = aQueue.sizeRecursive();   // invoke function to be tested
		boolean status = true;
		if (expected != actual) {	// compare actual to expected answer
			StdOut.format ("Failed sizeRecursive(): SourceData [%s] Expecting [%d] Actual [%d]\n", qData, expected, actual);
			status = false;
		}

		if ( status && showMeSuccess)  
			StdOut.format ("Success sizeRecursive(): SourceData [%s] Result: %d  \n", qData,actual);
	}	

	private static void testToString ( String qData, String expected) {
		CSC300Queue aQueue = CSC300Queue.of (qData);  // create queue from string data using *of* function

		String actual = aQueue.toString ();   	// invoke function to be tested

		boolean status = true;
		if (!actual.equals(expected)) {	         // compare actual to expected answer
			StdOut.format ("Failed   toString: Expecting [%s] Actual [%s]\n", expected, actual);
			status = false;  
		}
		if ( status && showMeSuccess)  
			StdOut.format ("Success  toString:   Expecting [%s] Actual [%s]\n", expected, actual);
	}	

	//  testDequeue
	//  check 1:  length decreases by 1
	//  check 2:  dequeued character is correct
	//  check 3:   new front char is correct
	private static void testDequeue ( String qData, char expectedRemoved,  char expectedNewFront) {
		CSC300Queue aQueue = CSC300Queue.of (qData);  

		char actualRemoved = aQueue.dequeue();	// invoke function to be tested
		boolean status = true;
		int expectedSize = qData.length()-1;
		int actualSize = aQueue.sizeIterative();

		if ( actualSize != expectedSize) {	// compare actual to expected answer
			StdOut.format ("Failed   dequeue: Expected size [%d] Actual size [%d]\n", expectedSize, actualSize);
			status = false;  
		}
		if ( actualRemoved != expectedRemoved) {  // compare actual to expected answer
			StdOut.format ("Failed   dequeue: Expected char [%c] Actual char [%c]\n", expectedRemoved, actualRemoved);
			status = false;  
		}
		if (  actualSize  >=1 ) {  
			char actualFront = aQueue.frontItem();   // check the front
			if( expectedNewFront != actualFront ) {
				StdOut.format ("Failed   dequeue: Expected front [%c] Actual char [%c]\n", expectedNewFront, actualFront);
				status = false;  
			}
		}
		if ( status && showMeSuccess)  
			StdOut.format ("Success  dequeue:   Source: [%s], dequeued value [%c] \n", qData, actualRemoved);
	}	

	private static void testRemoveEveryKthNode( String qData, int k, String expected) {
		CSC300Queue aQueue = CSC300Queue.of (qData);  

		aQueue.removeEveryKthNode(k);   // invoke function to be tested
		String r = extra(aQueue);

		boolean status = true;
		if (!r.equals(expected)) {
			StdOut.format ("Failed   removeEveryKthNode: Expecting [%s] Actual [%s]\n", expected, r);
			status = false;  
		}
		if ( status && showMeSuccess)  
			StdOut.format ("Success  removeEveryKthNode:   Source= [%s]  k= [%d]  Result= [%s] \n", qData,k, r);
	}	

	private static void testIsOrdered( String qData, boolean expected) {
		CSC300Queue aQueue = CSC300Queue.of (qData);  

		boolean status = true;
		if ( aQueue.isOrdered() != expected) {  // invoke function to be tested
			StdOut.format ("Failed   isOrdered: Source= [%s] Expecting [%b] \n",qData, expected);
			status = false;  
		}
		if ( status && showMeSuccess)  
			StdOut.format ("Success   isOrdered: Source= [%s] Expecting [%b] \n",qData, expected);

	}	

	/* Method to create populate queues */
	public static CSC300Queue of(String s) {
		Node first = null;
		CSC300Queue result = new CSC300Queue ();

		for (int i = s.length()-1; i >= 0; i--) {
			first = new Node (s.charAt(i), first);  
			if ( i==s.length()-1 ) result.front=first;
		}
		result.back =first;
		return result;
	}
	// extra method
	public static String extra( CSC300Queue q) {
		String t = "";
		for ( Node tmp=q.back; tmp != null; tmp = tmp.next)
			t += tmp.item;
		return t;
	}
}
