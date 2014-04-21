import java.io.PrintStream;

/**
 * The Red Black Tree is a height balanced tree, invented in the 1970s 
 * it was called a "symmetric binary btree". Search, Predecessor, Successor, 
 * Min, Max, Insert and Delete operations can all be done in O(h) time where h 
 * is the height of the tree Worst case height is 2lg(n + 1), where n is the 
 * number of nodes. Asymptotically, the cost of basic operations is O(log N) 
 * complexity. Note: Items used must be from a class with the comparable 
 * interface.
 * 
 * Red Black Tree algorithm source: Introduction to Algorithms, by Thomas H. 
 * Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein. 
 * 
 * Q: Why use the Cormen et al algorithm / definition of Red Black Tree?
 * A: Based on anecdotal evidence it is the most accepted / canonical algorithm 
 * for a Red Black Tree. We might have used a more efficient, better tested,
 * pre-built Red Black Tree, but for us to do concurrency right, we needed to 
 * understand the methods and have full access to the internals, so we built our 
 * own Red Black Tree.
 * 
 * RBTree Rules:
 * 1-the root is black
 * 2-the NULL leaf nodes (leaves) are considered black
 * 3-Red Property: if a node is red, it can only have black children
 * 4-Black Property: every path from root to leaf must have the same number of 
 * black nodes
 * 5-all nodes must be either red or black.
 * 
 * Other Sources: 
 * https://hkn.eecs.berkeley.edu/~dyoo/python/red_black/red_black.py
 * 
 * @author Jason Feriante
 */
public class RedBlackTree<K extends Comparable <K>> {

	private RedBlackNode<K> root; // The root is black.
	// Define colors (to simplify code)
	private RedBlackNode.Color red;
	private RedBlackNode.Color black;
	
	// Our first insertion key becomes the root.
	public RedBlackTree(K key) { 
		// Rule #1: the root is black
		red = RedBlackNode.Color.red;
		black = RedBlackNode.Color.black;
		root = new RedBlackNode<K>(key);
		root.color = black;
	}
	
	public RedBlackTree() { 
		red = RedBlackNode.Color.red;
		black = RedBlackNode.Color.black;
		root = null;
	}
	
	public void insert(K key) throws DuplicateException {
		root = insert(root, new RedBlackNode<K>(key));
//		root.color = black;
	}
	
	public boolean delete(K key) {
		if(key.equals(null)) return false;
		RedBlackNode<K> n = search(key);
		if(n == null) return false;
		delete(root, n);
		return true; // Assume it worked..
	}
	
	// Identical to a binary search tree lookup
	public boolean contains(K key) {
		return contains(root, key);
	}
	
	// Identical to a binary search tree print
	public void print(){
		print(root, 0);
	}
	
	public int count(){
		return count(root);
	}
	
	public int height(){
		return height(root);
	}
	
	public RedBlackNode<K> search(K key){
		return search(root, key);
	}
	
	public <K> K min(){
		RedBlackNode<K> min = (RedBlackNode<K>) min(root);
		return min.key;
	}
	
	public <K> K max(){
		RedBlackNode<K> max = (RedBlackNode<K>) max(root);
		return max.key;
	}

	/**
	 * Very similar to a Binary Search Tree insert
	 */
	@SuppressWarnings("unused")
	private RedBlackNode<K> insert(RedBlackNode<K> root, RedBlackNode<K> z) 
			throws DuplicateException {
		RedBlackNode<K> y = null;
		RedBlackNode<K> x = root;
		RedBlackNode<K> orig = z;
		while(x != null){
			y = x;
			if(z.key.compareTo(x.key) < 0){ // Case 1: z < x (-1)
				x = x.left;
			} else if(z.key.equals(x.key)) { // Case 2: z == x (o)
				throw new DuplicateException();
			} else { // Case 3: z < x (0)
				x = x.right;
			}
		}	
		z.p = y;
		if(y == null){
			root = z;
		} else if(z.key.compareTo(y.key) < 0){
			y.left = z;
		} else if(z.key.equals(y.key)){
			throw new DuplicateException();
		} else {
			y.right = z;
		}
		z.left = null;
		z.right = null;
		z.color = red;
//		if(((Integer) orig.key) == 10){
//			System.out.println("before insert fixup");
//			print();
//			z.debug();
//		}
		return insertFixup(root, z);
	}
	
	/**
	 * Restore red black tree properties (which restores balance to the tree) 
	 */
	private RedBlackNode<K> insertFixup(RedBlackNode<K> root, RedBlackNode<K> z){
		RedBlackNode<K> y = null;
		RedBlackNode<K> orig = z;
		while(z.p != null && z.p.color == red){
			if(z.p == z.p.p.left){
				y = z.p.p.right;
				if(y != null && y.color == red){ // Case 1: z's uncle y is red
					z.p.color = black;
					y.color = black;
					z.p.p.color = red;
					z = z.p.p;
				} else {
					// Case 2: z's uncle y is black and z is a right child
					if(z == z.p.right){
						z = z.p;
						// Transform Case 2 into Case 3 with a rotateLeft 
						root = rotateLeft(root, z);
					}
					// Case 3: z's uncle y is black and z is a left child
					z.p.color = black; // This terminates while-loop
					z.p.p.color = red;
					root = rotateRight(root, z.p.p);
				}
			} else { 
				y = z.p.p.left;
				if(y != null && y.color == red){ // Case 4: z's uncle y is red
					z.p.color = black;
					y.color = black;
					z.p.p.color = red;
					z = z.p.p;
				} else {
					// Case 5: z's uncle y is black and z is a left child
					if(z == z.p.left){
						z = z.p;
						// Transform Case 2 into Case 3 with a rotateRight
						root = rotateRight(root, z);
					}
					// Case 3: z's uncle y is black and z is a right child
					z.p.color = black; // This terminates while-loop
					z.p.p.color = red;
					root = rotateLeft(root, z.p.p);
				}
			}
		}
	    root.color = black;
//		if(((Integer) orig.key) == 10){
//			System.out.println("before insert fixup");
//			print();
//			z.debug();
//		}
	    return root;
	}
	

	private void delete(RedBlackNode<K> root, RedBlackNode<K> z){
		RedBlackNode<K> x;
		RedBlackNode<K> y = z;
		
		RedBlackNode.Color y_orig_color = y.color;
		if(z.left == null){
			x = z.right;
			transplant(root, z, z.right);
		} else if(z.right == null){
			x = z.left;
			transplant(root, z, z.left);
		} else {
			y = min(z.right);
			y_orig_color = y.color;
			x = y.right;
			if(y.p == z){
				x.p = y;
			} else {
				transplant(root, y, y.right);
				y.right = z.right;
				y.right.p = y;
			}
			transplant(root, z, y);
			y.left = z.left;
			y.left.p = y;
			y.color = z.color;
		}
		if(y_orig_color == black){
			deleteFixup(root, x);
		}
	}
	
	private void deleteFixup(RedBlackNode<K> root, RedBlackNode<K> x){
		RedBlackNode<K> w = null; // sibling
		
		while(x != root && x.color == black){
			if(x == x.p.left){ 
				// Case 1: right sibling is red
				w = x.p.right;
				if(w.color == red){
					w.color = black;
					x.p.color = red;
					root = rotateLeft(root, x.p);
					w = x.p.right;
				}
				if(w.left.color == black && w.right.color == black){ 
					// Case 2: right sibling black w/ two black children
					w.color = red;
					x = x.p;
				} else {
					if(w.right.color == black){ 
						// Case 3: right sibling's right child is black 
						w.left.color = black;
						w.color = red;
						root = rotateRight(root, w);
						w = x.p.right;
					} // Case 4: right sibling's left child is black
					w.color = x.p.color;
					x.p.color = black;
					w.right.color = black;
					root = rotateLeft(root, x.p);
					x = root;
				}
			} else {
				// same as then clause with right & left exchanged
				w = x.p.left;
				// Case 1: left sibling is red
				if(w.color == red){
					w.color = black;
					x.p.color = red;
					root = rotateLeft(root, x.p);
					w = x.p.left;
				}
				if(w.left.color == black && w.right.color == black){ 
					// Case 2: left sibling is black with two black children
					w.color = red;
					x = x.p;
				} else {
					if(w.left.color == black){ 
						// Case 3: left sibling's left child is black
						w.right.color = black;
						w.color = red;
						root = rotateLeft(root, w);
						w = x.p.left;
					} // Case 4: left sibling's right child is black
					w.color = x.p.color;
					x.p.color = black;
					w.left.color = black;
					root = rotateRight(root, x.p);
					x = root;
					
				}
			}
		}
		x.color = black;
	}
	
	/**
	 * @param root
	 * @param u old node we will replace
	 * @param v new node we will replace it with
	 */
	private void transplant(RedBlackNode<K> root, RedBlackNode<K> u, RedBlackNode<K> v){
		if(u.p == null){
			root = v;
		} else if(u == u.p.left){
			u.p.left = v;
		} else {
			u.p.right = v;
		}
		v.p = u.p;
	}
	
	private boolean contains(RedBlackNode<K> n, K key){
		// If n is null, return false / not found.
		if(n.key.equals(null)) return false;
		// Did we find our key?
		if(n.key.equals(key)) return true;
		// Is this a leaf?
		if(n.left == null && n.right == null) return false;
		if(n.key.compareTo(key) < 0){ // Less than n's key
			return contains(n.left, key);
		} else { // Greater than n's key
			return contains(n.right, key);
		}
	}
	
	private RedBlackNode<K> search(RedBlackNode<K> n, K key){
		// If n is null, return false / not found.
		if(n.key.equals(null)) return null;
		// Did we find our key?
		if(n.key.equals(key)) return n;
		// Is this a leaf?
		if(n.left == null && n.right == null) return null;
		if(n.key.compareTo(key) < 0){ // Less than n's key
			return search(n.left, key);
		} else { // Greater than n's key
			return search(n.right, key);
		}
	}
	
	private void print(RedBlackNode<K> n, int indent) {
	    if (n == null) {
	        System.out.print("Empty RBTree!");
	        return;
	    }
	    
	    if (n.right != null) {
	        print(n.right, indent + 4);
	    }
	    
	    for (int i = 0; i < indent; i++){ 
	    	System.out.print(" "); 
    	}
	    
	    if (n.color == RedBlackNode.Color.black){
	    	if(root == n){
	    		// Make it clear when we display the root
	    		System.out.println("*" + n.key + "*"); 
	    		} else {
	    		System.out.println(n.key);
	    	}
	    } else {
	    	System.out.println("[" + n.key + "]");
	    }
	    if (n.left != null) {
	        print(n.left, indent + 4);
	    }
	}
	
	/**
	  root = rotateLeft:
		from:
		   x
		  / \
		 a   y
		    / \
		   b   c
	
		to:
	       y
	      / \
	     x   c
	    / \
	   a   b
	*/
	private RedBlackNode<K> rotateLeft(RedBlackNode<K> root, RedBlackNode<K> x){
		if(x.right == null) throw new NullPointerException();
		RedBlackNode<K> y;
		y = x.right;
		x.right = y.left;
		if(y.left != null){
			y.left.p = x;
		}
		y.p = x.p;
		if(x.p == null){
			root = y;
		} else if(x == x.p.left){
			x.p.left = y;
		} else {
			x.p.right = y;
		}
		y.left = x;
		x.p = y;
		return root;
	}
	
	/**
	 root = rotateRight:
		from:
		       n
		      / \
		     t   c
		    / \
		   a   b
		
		to:
		   t
		  / \
		 a   n
		    / \
		   b   c
	 */
	private RedBlackNode<K> rotateRight(RedBlackNode<K> root, RedBlackNode<K> node){
		if(node.left == null) throw new NullPointerException();
		RedBlackNode<K> tmp;
		tmp = node.left;
		node.left = tmp.right;
		if(tmp.right != null){
			tmp.right.p = node;
		}
		tmp.p = node.p;
		if(node.p == null){
			root = tmp;
		} else if(node == node.p.right){
			node.p.right = tmp;
		} else {
			node.p.left = tmp;
		}
		tmp.right = node;
		node.p = tmp;
		
		return root;
	}
	
	private int height(RedBlackNode<K> n){
		if(n == null) return 0;
		return Math.max(1 + height(n.left), 1 + height(n.right));
	}
	
	private int count(RedBlackNode<K> n){
		if(n == null) return 0;
		return 1 + count(n.left) + count(n.right);
	}

	private RedBlackNode<K> min(RedBlackNode<K> n){
		while(n.left != null){
			n = n.left;
		}
		return n;
	}
	
	private RedBlackNode<K> max(RedBlackNode<K> n){
		while(n.right != null){
			n = n.right;
		}
		return n;
	}
	
	@SuppressWarnings("unused")
	private RedBlackNode<K> predecessor(RedBlackNode<K> n){
		if(n.left != null){ 
			return max(n.left); 
		}
		RedBlackNode<K> y = n.p;
		while(y != null && n == y.left){ 
			n = y; 
			y = y.p; 
		}
		return y;
	}
	
	@SuppressWarnings("unused")
	private RedBlackNode<K> successor(RedBlackNode<K> n){
		if(n.right != null){
			return min(n.right);
		}
		RedBlackNode<K> y = n.p;
		while(y != null && n == y.right){
			n = y;
			y = y.p;
		}
		return y;
	}
}
