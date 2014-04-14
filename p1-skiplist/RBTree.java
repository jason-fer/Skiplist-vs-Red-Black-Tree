import java.io.PrintStream;

/**
 * The RBTree is a height balanced tree, invented in the 1970s it was called a 
 * "symmetric binary btree". With binary search trees the average case is 
 * O(Log N) for insert, lookup & delete, where N is the number of nodes in the 
 * tree; worst case times are O(N). The height balanced RBTree allows us to 
 * guarantee O(N) time for all three methods. Adapted from the BST Class as 
 * taught in CS 367. Note: Items used must be from a class with the comparable 
 * interface. 
 * 
 * Derive complexity: n = 2^n - 1, n + 1 = 2^h, log(n+1) = h, O(log(n+1)),
 * ... asymptotically: O(log N)
 * @author Jason Feriante
 */
public class RBTree<K extends Comparable <K>> {

	private RBnode<K> root; // The root is black.
	
	public RBTree() { root = null; }
	
	// Red property: red nodes must have black children
	
	// Black property: every path from root to leaf must have the same number
	// of black nodes
	
	/**
	 * Public interface methods 
	 */
	public void insert(K key) throws DuplicateException {
		root = insert(root, key);
	}
	
	public void delete(K key) {
		root = delete(root, key);
	}
	
	// Identical to a binary search tree lookup
	public boolean lookup(K key) {
		return lookup(root, key);
	}

	// Identical to a binary search tree print
	public void print(PrintStream p){
		print(root, p);
	}
	
	/**
	 * The real work is done by the private methods below  
	 */
	// complete this method
	private RBnode<K> insert(RBnode<K> n, K key) throws DuplicateException {
		return n;
		/**
		 * Goal: insert key value K into tree T and maintain RBT properties.
		 * Case: T is empty -- add a new black leaf node containing K
		 * Case: T is non-empty
		 * 	-search for location to insert as done for BST
		 *  -add K as a red leaf node
		 *  -restore red-black tree properties if red property violation
		 *  	Case 1: K's parent P is black - done
		 *  	Case 2: K's parent P is red (red property violation)
		 *  		a: P's sibling S is black or null - trinode restructure. done.
		 *  		b: P's sibling is red -- recolor, may need to recursively restore. 
		 */
	}

	// complete this method
	private RBnode<K> delete(RBnode<K> n, K key){
		return n;
	}
	
	// complete this method
	private boolean lookup(RBnode<K> n, K key){
		/**
		 * -if N is null, return false / not found
		 * -if N's key equals key, return true / found
		 * -if N is a leaf return false / not found <--improve efficiency
		 * -If k < N's key
		 * 		return lookup(n's left subtree, key)
		 *  else
		 *  	return lookup(n's right subtree, key)
		 */
		return false;
	}
	
	/**
	 * Recursively iterate through list; concise.
	 */
	private void print(RBnode<K> n, PrintStream p){
		if(n == null) return;
		print(n.getLeft(), p); // Left traversal step
		System.out.println(n.getKey()); // Visit
		print(n.getRight(), p); // Right traversal step
	}
}
