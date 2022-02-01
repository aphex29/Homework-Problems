package homework;
import stdlib.*;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;

/** 
 * DSI_DoublyLinkedList  version 1.0
 * 
 *   Patrick Markowski
 *   CSC 300-501
 *   
 *  this class implements the 'generic' notion of a list using a doubly-linked list representation 
 *      each node has a link to the successor (next) and predecessor (prev) nodes. 
 *      --> the links go both ways
 *  The instance variable  first is used to 'point' at the first node (left-most)
 *  The instance variable  last  is used to 'point' at the last  node (right-most)
 * 
 *  You are to complete the 5 toDos below, implementing various instance methods for this list class
 * 
 *  If you are unable to complete a ToDo, comment out any code you added so the function does not 
 *  create a runtime exception
 * 
 * You may not add any fields to the node or list classes.
 * You may not add any methods to the node class.
 * Do not change any 'Utility' methods that I have included.
 * 
 * You may NOT use any arrays or other Java classes without permission.
 * 
 * Some testing code is provided, called from main.  You can comment these in/out while 
 * you are working on the various methods.  
 * Testing code is not guaranteed to be exhaustive.  You are encouraged to review the 
 * tests and see if there other tests that are warranted.
 * 
 * IMPORTANT!!!!!!
 * The testing code for some functions relies on  insertBefore  working correctly
 * 
 */

public class DSIList {
	Node first,last;        // three instance variables
	int N;					// maintain the size of the list

	static class Node {  // Note the useful Node constructor
		public Node(double item, Node prev, Node next) { this.item = item; this.prev = prev; this.next = next; }
		public double item;
		public Node next;     // the next node       - to the right
		public Node prev;     // the previous node   - to the left
	}

	// constructors
	public DSIList ()  {   // an empty list
		first = last = null;
		N = 0;
	};      
	public DSIList (double item)  {  // a list with one item
		first = last = new Node(item,null,null);
		N = 1;
	};  

	public boolean isEmpty () { return N == 0; }
	public int size ()        { return N; }

	/**
	 * set
	 * 
	 * Change the value at the specified index
	 * the location can be specified by a positive or negative number.
	 * 
	 * zero or positive value is interpreted as an offset from the left (first)
	 * a negative value is interpreted as an offset from the right, with -1 meaning last
	 * 
	 * index values outside the valid range should not change the list 
	 * The function returns true if the set was successful, false if the index is invalid
	 * 
	 * In the examples below the list is delimited by  { }  
	 *     the value on the left would be 'first'
	 *     the value on the right would be 'last'
	 * Examples: 
	 * { 1 2 3 4 5 6 }.set(1, 9)    -->   { 1 9 3 4 5 6 }
	 * { 1 2 3 4 5 6 }.set(5, 9)    -->   { 1 2 3 4 5 9 }
	 * { 1 2 3 4 5 6 }.set(8, 9)    -->   { 1 2 3 4 5 6 }
	 * { 1 2 3 4 5 6 }.set(-1, 9)   -->   { 1 2 3 4 5 9 }
	 * { 1 2 3 4 5 6 }.set(-4, 9)   -->   { 1 2 9 4 5 6 }
	 * { 1 2 3 4 5 6 }.set(-11, 9)  -->   { 1 2 3 4 5 6 }
	 */

	public boolean set(int index, double value) {

		if (index>=0) {
			int ct=0;
			for (Node t = first;t!=null;t=t.next) {
				if (ct==index) {
					t.item = value;
					
					return true;
				}
				ct++;
			}
		}
		else  {
			int ct = -1;
			for (Node t = last; t!=null; t=t.prev) {
				if (ct==index) {
					t.item = value;
					return true;	
				}
				ct--;
			}
		}
		return false;
	}

	/** insertBefore
	 * 
	 * add a new node containing item TO THE LEFT of the node in position index
	 * Positions are numbered as offsets from the first node
	 * Precondition:  index is in [0,N-1] and not negative   (i.e. the index is valid)
	 * parameter 1 is the value
	 * parameter 2 is the position index
	 * Examples: 
	 * { 2 }.insertBefore(1, 0)                  -->   { 1 2 }
	 * { 2 3 }.insertBefore(1, 0)                -->   { 1 2 3 }
	 * { 1 3 4 5 6 7 8 }.insertBefore(2, 1)      -->   { 1 2 3 4 5 6 7 8 }
	 * { 1 2 3 5 6 7 8 }.insertBefore(4, 3)      -->   { 1 2 3 4 5 6 7 8}
	 * { 2 }.insertBefore(1, 0)                  -->   { 1 2 }
	 * { 2 }.insertBefore(1, 0)                  -->   { 1 2 }
	 * 
	 */

	public void insertBefore (double item, int index) {
		int ct=0;
		
		Node x = new Node(item,null,null);
		if (index==0) {
			
			x.next=first;
			first.prev=x;
			first=x;
			N++;
			return;
		}
		for (Node t = first;t!=null;t=t.next) {
			
			if (ct==index-1) {
				Node tmp = t.next;
				tmp.prev=x;
				t.next=x;
				x.prev=t;
				x.next=tmp;
				N++;
				return;
			}
			
				
			ct++;	
		}
		

	}
	/** insertAfter
	 * 
	 * add a new node containing item TO THE RIGHT of the node in position index
	 * Positions are numbered as offsets from the last node
	 * They are negative values (similar to Python).  index = -1 would refer to the last node
	 * Precondition:  index is in [-(N), -1]  ( index is valid )
	 * 
	 * parameter 1 is the value
	 * parameter 2 is the position index
	 * 
	 * Examples:
	 * 
	 * { 1 }.insertAfter(2, -1)                  -->   { 1 2 }
	 * { 1 2 }.insertAfter(3, -1)                -->   { 1 2 3 }
	 * { 1 2 3 4 5 6 7 }.insertAfter(8, -1)      -->   { 1 2 3 4 5 6 7 8 }
	 * { 1 2 3 5 6 7 8 }.insertAfter(4, -5)      -->   { 1 2 3 4 5 6 7 8}
	 * 
	 */
	
	public void insertAfter(double item, int negIndex) {
		
		Node x = new Node(item,null,null);
		int ct=-1;
		
		if (negIndex==-1) {
			last.next=x;
			x.prev=last;
			last=x;
			N++;
			return;
		}
		
		for (Node t = last; t!=null;t=t.prev) {
			
			if (ct==negIndex+1) {
				Node tmp = t.prev;
				tmp.next=x;
				x.prev=tmp;
				x.next=t;
				t.prev=x;
				N++;
				return;
			}
			
			ct--;
		}
	}


	/** RemoveFirstOccurence
	 *
	 *  Removes the first node containing the specified value 
	 *  Start at first and go to the right
	 *  precondition: none
	 *  return true if a the value was found and removed, false otherwise
	 *  
	 *  parameter 1 is the key value to search for and delete
	 *  
	 *  Examples:
	 *  
	 * { 1 2 }.removeFirstOccurence(2)                -->   { 1 }
	 * { 1 2 1 2 }.removeFirstOccurence(2)            -->   { 1 1 2}
	 * { 1 2 1 2 }.removeFirstOccurence(1)            -->   { 2 1 2 }
	 * { 1 2 3 4 }.removeFirstOccurence(4)            -->   { 1 2 3 }
	 */
	public boolean removeFirstOccurence (int key) {
		
		if (first==last && first.item==key) {
			first=last=null;
			N--;
			return true;
		}
		if (first.item == key) {
			Node y = first.next;
			y.prev=null;
			first=y;
			y.next=first.next;
			N--;
			return true;
		}
		for (Node t= first; t.next!=null;t=t.next) {
			if(t.next.next!=null && t.next.item == key) {
				Node y = t.next.next;
				t.next=y;
				y.prev=t;
				N--;
				return true;
			}
			else if(t.next.item==key && t.next.next==null) {
				
				t.next=null;
				last=t;
				N--;
				return true;
			}
		}
		
		return false;  //ToDo 4  fix this

	}
	/** Before and After
	 *
	 * find the sum of the nodes before and after the Node x.
	 * Precondition:  x is a valid node.
	 * 
	 * parameter x is a reference to the specific node
	 * 
	 * Note: x could be the first (or last) node 
	 *       in which case simply return the after (or before) value
	 *       
	 *   In the examples below, the specific node is indicated by the value it contains
	 *   THIS IS NOT THE PAREMETER THE FUNCTION IS PASSED
	 *  Examples:
	 * { 1 2 3 4 5 }.beforeAndAfter(3)            -->   6
	 * { 1 2 3 4 5 }.beforeAndAfter(1)            -->   2
	 * { 1 2 3 4 5 }.beforeAndAfter(2)            -->   4
	 */
	
	public double beforeAndAfter (Node x) {
		
		if (first==last && first==x)
			return 0;
		if (first==x)
			return first.next.item;
		if (last==x)
			return last.prev.item;
		
		for (Node t = first; t!=null;t=t.next) {
			
			if (x==t)
				return (t.next.item + t.prev.item);
		}
		return 0;	 
	}

	public static void main (String args[]) {
		
		//Trace.drawStepsOfMethod("setTests");
		//Trace.drawStepsOfMethod("insertBeforeTests");
		
		//Trace.drawStepsOfMethod("insertAfterTests");
		//Trace.drawStepsOfMethod("removeFirstOccurrenceTests");
		//Trace.drawStepsOfMethod("beforeAndAfterTests");      // add more Traces if you like
		Trace.run ();
		
		setTests();
		insertBeforeTests();
		insertAfterTests();
		removeFirstOccurrenceTests();
		beforeAndAfterTests();
	}
	////////////////////////////////////////////////////////////////////
	// tests
	////////////////////////////////////////////////////////////////////
	public static void setTests() {
		DSIList d1;
		d1 = new DSIList ("9");						// Initial list -> 99
		d1.set (0,3);					            // change list item 0 to 3
		check ("set #1", d1, "[ 3 ]");				// answer.  list -> 3

		d1 = new DSIList ("2 3");					// Initial list -> 2  3
		d1.set (1,9);								// change list item 1 to 9
		check ("set #2", d1, "[ 2 9 ]");			// answer.  list -> 2 9
		
		d1 = new DSIList ("0 1 2 3 4 5 6 7 8");					
		d1.set (1,9);								
		check ("set #3", d1, "[ 0 9 2 3 4 5 6 7 8 ]");			
		
		d1 = new DSIList ("0 1 2 3 4 5 6 7 8");					
		d1.set (10,9);								
		check ("set #4", d1, "[ 0 1 2 3 4 5 6 7 8 ]");
		
		d1 = new DSIList ("9");					
		d1.set (-1,3);								
		check ("set #5", d1, "[ 3 ]");	
		
		d1 = new DSIList ("2 3");					
		d1.set (-2,9);								
		check ("set #6", d1, "[ 9 3 ]");
		
		d1 = new DSIList ("0 1 2 3 4 5 6 7 8 ");					
		d1.set (-4,9);								
		check ("set #7", d1, "[ 0 1 2 3 4 9 6 7 8 ]");	
		
		d1 = new DSIList ("0 1 2 3 4 5 6 7 8 ");					
		d1.set (-9,9);								
		check ("set #8", d1, "[ 9 1 2 3 4 5 6 7 8 ]");
		
		d1 = new DSIList ("0 1 2 3 4 5 6 7 8 ");					
		d1.set (-10,9);								
		check ("set #8", d1, "[ 0 1 2 3 4 5 6 7 8 ]");
		
		StdOut.println ("Finished set tests");
	}
	public static void beforeAndAfterTests() {

		testBeforeAndAfter (6.0, 3, "1 2 3 4 5");           // before&after 3
		testBeforeAndAfter (2.0, 1, "1 2 3 4 5");			// before&after 1
		testBeforeAndAfter (4.0, 2, "1 2 3 4 5");			// before&after 2
		testBeforeAndAfter (7.0, 8, "1 2 3 4 5 6 7 8");		// before&after 8
		testBeforeAndAfter (14.0, 7, "1 2 3 4 5 6 7 8");	// before&after 7

		StdOut.println ("Finished before and after tests");
	}

	public static void insertBeforeTests() {
		
		DSIList d1;
		
		d1 = new DSIList ("2");							// Initial list -> 2
		d1.insertBefore (1,0);					        // Insert 1 before location 0 (the 2)
		check ("insertBefore #1", d1, "[ 1 2 ]");		// answer.  list -> 1 2

		
		d1 = new DSIList ("2 3");
		d1.insertBefore (1,0);
		check ("insertBefore #2", d1, "[ 1 2 3 ]");

		d1 = new DSIList ("1 3 4 5 6 7 8");
		d1.insertBefore (2,1);
		check ("insertBefore #3", d1, "[ 1 2 3 4 5 6 7 8 ]");

		d1 = new DSIList ("1 2 3 5 6 7 8");
		d1.insertBefore (4,3);
		check ("insertBefore #4", d1, "[ 1 2 3 4 5 6 7 8 ]");

		d1 = new DSIList ("1 2 3 4 5 6 8");
		d1.insertBefore (7,6);
		check ("insertBefore #5", d1, "[ 1 2 3 4 5 6 7 8 ]");
		
		StdOut.println ("Finished insert before tests");
	}

	public static void insertAfterTests() {

		DSIList d1;
		
		d1 = new DSIList ("1");							// Initial list -> 1
		d1.insertAfter (2,-1);							// Insert 2 after location -1 (the 1)
		check ("InsertAfter #1", d1, "[ 1 2 ]");		// answer.  list -> 1 2

		d1 = new DSIList ("1 2");
		d1.insertAfter (3,-1);
		check ("InsertAfter #2", d1, "[ 1 2 3 ]");

		d1 = new DSIList ("1 2 3 4 5 6 7");
		d1.insertAfter (8 ,-1);
		check ("InsertAfter #3", d1, "[ 1 2 3 4 5 6 7 8 ]");

		d1 = new DSIList ("1 2 3 5 6 7 8");
		d1.insertAfter (4,-5);
		check ("InsertAfter #4", d1, "[ 1 2 3 4 5 6 7 8 ]");

		d1 = new DSIList ("1 2 3 4 5 6 8");
		d1.insertAfter (7,-2);
		check ("InsertAfter #5", d1, "[ 1 2 3 4 5 6 7 8 ]");
		
		StdOut.println ("Finished insert after tests");
	}

	public static void removeFirstOccurrenceTests() {
		DSIList d1;
		
		d1 = new DSIList ("1 2 ");							// list -> 1 2
		d1.removeFirstOccurence(2);							// remove the first 2
		check ("removeFirstOccurrence #1", d1, "[ 1 ]");	// answer. list -> 1

		d1 = new DSIList ("1 2 1 2");						// list -> 1 2 1 2
		d1.removeFirstOccurence (2);						// remove the first 2
		check ("removeFirstOccurrence #2", d1, "[ 1 1 2 ]");// answer. list -> 1 1 2

		d1 = new DSIList ("1 2 1 2");
		d1.removeFirstOccurence (1);
		check ("removeFirstOccurrence #3", d1, "[ 2 1 2 ]");

		d1 = new DSIList ("1 2 3 4");
		d1.removeFirstOccurence (4);
		check ("removeFirstOccurrence #4", d1, "[ 1 2 3 ]");

		d1 = new DSIList ("1 2 3 4 5");
		d1.removeFirstOccurence (4);
		check ("removeFirstOccurence #5", d1, "[ 1 2 3 5 ]");

		d1 = new DSIList ("1 2 3 4 5");
		d1.removeFirstOccurence (6);
		check ("removeFirstOccurence #6", d1, "[ 1 2 3 4 5 ]");

		d1 = new DSIList ("1");
		d1.removeFirstOccurence (6);
		check ("removeFirstOccurence #7", d1, "[ 1 ]");

		StdOut.println ("Finished removeFirstOccurence tests");
	}

	private static void testBeforeAndAfter (double expected, double key, String sList) {
		DSIList ds1 = new DSIList (sList);
		Node theNode = ds1.find(key);
		double actual = ds1.beforeAndAfter(theNode);
		if (expected != actual) {
			StdOut.format ("Failed [\"%s\"].beforeAndAfter(): Expecting: %5.2f  Actual: %5.2f\n", sList, expected, actual);
		}
	}

	/**   *********************************** Utility routines   ***************************************
	 * 
	 */
	public DSIList (String s) {

		String[] nums = s.split (" ");
		double fitem = Double.parseDouble (nums[nums.length-1]);
		DSIList t = new DSIList(fitem);
		first = t.first;
		last=t.last;
		N=1;
		for (int i = nums.length-2; i >= 0; i--) {
			try { 
				insertBefore (Double.parseDouble (nums[i]),0);			
			} catch (NumberFormatException e) {	}
		}
	}

	public Node find( double item) {
		for ( Node tmp = first; tmp != null; tmp = tmp.next )
			if ( tmp.item == item)
				return tmp;
		return null;
	}
	public String toString () { 
		DecimalFormat format = new DecimalFormat ("#.###");
		StringBuilder result = new StringBuilder ("[ ");
		for (Node x = first; x != null; x = x.next) {
			result.append (format.format (x.item));
			result.append (" ");
		}
		result.append ("]");
		return result.toString ();
	}

	static void showError (String message) {
		Trace.draw ();
		StdOut.println (message);
		//throw new Error (); // stops execution
	}
	public static void checkInvariants (String message, DSIList that) {
		int N = that.N;
		DSIList.Node left = that.first;
		DSIList.Node right = that.last;

		if (N < 0) throw new Error ();
		if (N == 0) {
			if (left != null || right != null) {
				showError (String.format ("%s: Expected first,last == null.", message));
			}
		} else {
			if (left == null || right == null) {
				showError (String.format ("%s: Expected first,last != null.", message));
			}
		}
		if (N > 0) {
			DSIList.Node prev = null;
			DSIList.Node current = left;
			for (int i = 0; i < N; i++) {
				if (current == null) {
					showError (String.format ("%s: Expected %d next nodes, but got less.", message, N));
				}
				if (current.prev != prev) { 
					showError (String.format ("%s: Broken prev link.", message));
				}
				prev = current;
				current = current.next;
			}
			if (current != null) {
				showError (String.format ("%s: Expected %d next nodes, but got more.", message, N));
			}
			DSIList.Node next = null;
			current = right;
			for (int i = 0; i < N; i++) {
				if (current == null) {
					showError (String.format ("%s: Expected %d prev nodes, but got less.", message, N));
				}
				if (current.next != next) {
					showError (String.format ("%s: Broken next link.", message));
				}
				next = current;
				current = current.prev;
			}
			if (current != null) {
				showError (String.format ("%s: Expected %d prev nodes, but got more.", message, N));
			}
		}
	}
	private static void check (String message, DSIList actual, String expected) {
		checkInvariants (message, actual);
		if (expected != null) {
			if (!expected.equals (actual.toString ())) {
				showError (message + " Expected \"" + expected + "\", got \"" + actual + "\"");
			}
		}
	}
	private static void check (String message, DSIList actual, String expected, double dActual, double dExpected) {
		if (dExpected != dActual) {
			showError (message + " Expected \"" + dExpected + "\", got \"" + dActual + "\"");
		}
		check (message, actual, expected);
	}


}
