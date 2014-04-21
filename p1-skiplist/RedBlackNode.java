/**
 * @author Jason Feriante
 */
public class RedBlackNode<K> {
	
	public K key;
	public RedBlackNode<K> left, right;
	public enum Color {red, black};
	public Color color;
	public RedBlackNode<K> p; // Parent
	
	// A new node we know nothing about yet. Default to red
	public RedBlackNode(K key) {
		this.key = key;
		this.left = null;
		this.right = null;
		this.color = Color.red;
		this.p = null;
	}	
	
	// Default to red
	public RedBlackNode(K key, RedBlackNode<K> left, RedBlackNode<K> right, RedBlackNode<K> p) {
		this.key = key;
		this.left = left;
		this.right = right;
		this.color = Color.red;
		this.p = p;
	}
	
	// Constructor with color
	public RedBlackNode(K key, RedBlackNode<K> left, RedBlackNode<K> right, Color color, RedBlackNode<K> p) {
		this.key = key;
		this.left = left;
		this.right = right;
		this.color = color;
		this.p = p;
	}

	public K getKey() { return key; }
	public RedBlackNode<K> getLeft() { return left; }
	public RedBlackNode<K> getRight() { return right; }
	public Color getColor() { return color; }
	
	public void setKey(K newK) { key = newK; }
	public void setLeft(RedBlackNode<K> newL) { left = newL; }
	public void setRight(RedBlackNode<K> newR) { right = newR; }
	public void setColor(Color newColor) { color = newColor; }
	
	public void debug(){
		K left = null;
		K right = null;
		K key = null;
		K pkey = null;
		if(this.left != null){
			left = this.left.key;
		}
		if(this.right != null){
			left = this.right.key;
		}
		if(this.key != null){
			key = this.key;
		}
		if(this.p != null){
			pkey = this.p.key;
		}
		
		System.out.println("RBTNode left:"+left+", right:"+right+
				", key:"+key+", parent val:"+pkey);
	}
}
