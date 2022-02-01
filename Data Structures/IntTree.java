package hw3;

import java.util.LinkedList;

/* ***********************************************************************
 * A simple BST with int keys and no values
 *
 * Complete each function below.
 * Write each function as a separate recursive definition (do not use more than one helper per function).
 * Depth of root==0.
 * Height of leaf==0.
 * Size of empty tree==0.
 * Height of empty tree=-1.
 *
 * TODO: complete the functions in this file.
 * DO NOT change the Node class.
 * DO NOT change the name or type of any function (the first line of the function)
 *
 * Restrictions:
 *  - no loops (you cannot use "while" "for" etc...)
 *  - only one helper function per definition
 *  - no fields (static or non static).  Only local variables
 *************************************************************************/
public class IntTree {
	private Node root;
	private static class Node {
		public final int key;
		public Node left, right;
		public Node(int key) { this.key = key; }
	}

	public void printInOrder() {
		printInOrder(root);
	}
	
	private void printInOrder(Node n) {
		if (n == null)
			return;
		printInOrder(n.left);
		System.out.println(n.key);
		printInOrder(n.right);
	}

	// the number of nodes in the tree
	public int size() {
		// TODO
		return sizeH(root);
	}
	private int sizeH(Node tree) {
		if (tree==null) {
			return 0;
		}
		else {
			int tempL=sizeH(tree.left);
			int tempR=sizeH(tree.right);
			return tempL+tempR+1;
		}
	
		
		
	}

	// Recall the definitions of height and depth.
	// in the BST with level order traversal "41 21 61 11 31",
	//   node 41 has depth 0, height 2
	//   node 21 has depth 1, height 1
	//   node 61 has depth 1, height 0
	//   node 11 has depth 2, height 0
	//   node 31 has depth 2, height 0
	// height of the whole tree is the height of the root

	
	// 20 points
	/* Returns the height of the tree.
	 * For example, the BST with level order traversal 50 25 100 12 37 150 127
	 * should return 3.
	 * 
	 * Note that the height of the empty tree is defined to be -1.
	 */
	public int height() {
		
		return heightH(root);
	}
	private int heightH(Node tree) {
		if (tree==null) {
			return -1;
		}
		else {
			int tempL=heightH(tree.left);
			int tempR=heightH(tree.right);
			if (tempL>tempR) {
				return tempL+1;
			}
			else {
				return tempR+1;
			}
			
		}
	}


	// 20 points
	/* Returns the number of nodes with odd keys.
	 * For example, the BST with level order traversal 50 25 100 12 37 150 127
	 * should return 3  (25, 37, and 127).
	 */
	public int sizeOdd() {
		// TODO
		return sizeOddH(root);
	}
	private int sizeOddH(Node tree) {
		if (tree==null)
			return 0;
		else {
			int tempL=sizeOddH(tree.left);
			int tempR=sizeOddH(tree.right);
			if ((tree.key%2) != 0) {
				return 1+tempL+tempR;
			}
			return tempL+tempR;
		}
	}

	
	// 20 points
	/* Returns true if this tree and that tree "look the same." (i.e. They have
	 * the same keys in the same locations in the tree.)
	 * Note that just having the same keys is NOT enough.  They must also be in
	 * the same positions in the tree.
	 */
	public boolean treeEquals(IntTree that) {
		// TODO
		return treeEqualsH(root,that.root);
	}
	
	private boolean treeEqualsH(Node t1,Node t2) {
		if (t1==null && t2==null) {
			return true;
		}
		else if (t1==null || t2==null)
			return false;
		
		return (t1.key==t2.key && treeEqualsH(t1.left,t2.left) && treeEqualsH(t1.right,t2.right));
	}

	// 10 points
	/* Returns the number of nodes in the tree, at exactly depth k.
	 * For example, given BST with level order traversal 50 25 100 12 37 150 127
	 * the following values should be returned
	 * t.sizeAtDepth(0) == 1		[50]
	 * t.sizeAtDepth(1) == 2		[25, 100]
	 * t.sizeAtDepth(2) == 3		[12, 37, 150]
	 * t.sizeAtDepth(3) == 1		[127]
	 * t.sizeAtDepth(k) == 0 for k >= 4
	 */
	public int sizeAtDepth(int k) {
		// TODO
		return sizeAtH(root,k);
	}

	private int sizeAtH(Node tree, int k) {
		if (tree==null)
			return 0;
		if (k==0)
			return 1;
		
		int sizeL=sizeAtH(tree.left,k-1);
		int sizeR=sizeAtH(tree.right,k-1);
		return sizeL + sizeR;
	}
	// 10 points
	/*
	 * Returns the number of nodes in the tree "above" depth k. 
	 * Do not include nodes at depth k.
	 * For example, given BST with level order traversal 50 25 100 12 37 150 127
	 * the following values should be returned 
	 * t.sizeAboveDepth(0) == 0
	 * t.sizeAboveDepth(1) == 1					[50]
	 * t.sizeAboveDepth(2) == 3					[50, 25, 100]		
	 * t.sizeAboveDepth(3) == 6					[50, 25, 100, 12, 37, 150] 
	 * t.sizeAboveDepth(k) == 7 for k >= 4		[50, 25, 100, 12, 37, 150, 127] 
	 */
	public int sizeAboveDepth(int k) {
		// TODO
		return sizeAboveH(root,k);
	}
	private int sizeAboveH(Node tree, int k) {
		if (tree==null)
			return 0;
		if (k==0)
			return 0;
		
		int sizeL=sizeAboveH(tree.left,k-1);
		int sizeR=sizeAboveH(tree.right,k-1);
		return sizeL + sizeR + 1;
	}

	// 10 points
	/* Returns true if for every node in the tree has the same number of nodes
	 * to its left as to its right.
	 * 	For example, the BST with level order traversal 50 25 100 12 37 150 127
	 * is NOT perfectly balanced.  Although most of the nodes (including the root) have the same number of nodes
	 * to the left as to the right, the nodes with 100 and 150 do not and so the tree is not perfeclty balanced.
	 * 
	 * HINT: In the helper function, change the return type to int and return -1 if the tree is not perfectly balanced
	 *       otherwise return the size of the tree.  If a recursive call returns the size of the subtree, this will help
	 *       you when you need to determine if the tree at the current node is balanced.
	 */
	public boolean isPerfectlyBalanced() {
		// TODO
		if (balancedH(root)==-1)
			return false;
		else
			return true;
	}

	private int balancedH(Node tree) {
		if (tree==null)
			return 0;
		else {
			int sizeL=balancedH(tree.left);
			int sizeR=balancedH(tree.right);
			if (sizeL==sizeR)
				return sizeL+sizeR+1;
			else
				return -1;
		}
	}
	
	/*
	 * Mutator functions
	 * HINT: This is easier to write if the helper function returns Node, rather than void
	 * similar to recursive mutator methods on lists.
	 */

	// 10 points
	/* Removes all subtrees with odd roots (if node is odd, remove it and its descendants)
	 * A node is odd if it has an odd key.
	 * If the root is odd, then you should end up with the empty tree.
	 * For example, when called on a BST
	 * with level order traversal 50 25 100 12 37 150 127
	 * the tree will be changed to have level order traversal 50 100 150
	 */
	
	public void removeOddSubtrees () {
		// TODO
		
		root=removeOddH(root);
	}
	private Node removeOddH(Node tree) {
		
		if (tree==null || tree.key%2 != 0)
			return null;
		
		if (tree.left!=null && tree.left.key%2 != 0) {
			tree.left=null;
			
		}
		if (tree.right!=null && tree.right.key%2 != 0) {
			tree.right=null;
			
		}
		tree.left=removeOddH(tree.left);
		tree.right=removeOddH(tree.right);
		return tree;
		
	}


	/* ***************************************************************************
	 * Some methods to create and display trees
	 *****************************************************************************/

	// Do not modify "put"
	public void put(int key) { root = put(root, key); }
	private Node put(Node n, int key) {
		if (n == null) return new Node(key);
		if      (key < n.key) n.left  = put(n.left,  key);
		else if (key > n.key) n.right = put(n.right, key);
		return n;
	}
	// This is what contains looks like
	public boolean contains(int key) { return contains(root, key); }
	private boolean contains(Node n, int key) {
		if (n == null) return false;
		if      (key < n.key) return contains(n.left,  key);
		else if (key > n.key) return contains(n.right, key);
		return true;
	}
	// Do not modify "copy"
	public IntTree copy () {
		IntTree that = new IntTree ();
		for (int key : levelOrder())
			that.put (key);
		return that;
	}
	// Do not modify "levelOrder"
	public Iterable<Integer> levelOrder() {
		LinkedList<Integer> keys = new LinkedList<Integer>();
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node n = queue.remove();
			if (n == null) continue;
			keys.add(n.key);
			queue.add(n.left);
			queue.add(n.right);
		}
		return keys;
	}
	// Do not modify "toString"
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int key: levelOrder())
			sb.append (key + " ");
		return sb.toString ();
	}
	
	public void prettyPrint() {
		if (root == null)
			System.out.println("<EMPTY>");
		else
			prettyPrintHelper(root, "");
	}
	
	private void prettyPrintHelper(Node n, String indent) {
		if (n != null) {
			System.out.println(indent + n.key);
			indent += "    ";
			prettyPrintHelper(n.left, indent);
			prettyPrintHelper(n.right, indent);
		}
	}
	
	public static void main(String[] args) {
		IntTree s = new IntTree();
		s.put(15);
		s.put(11);
		s.put(21);
		s.put(8);
		s.put(13);
		s.put(16);
		s.put(18);
		s.printInOrder();
	}	
}
