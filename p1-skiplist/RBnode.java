/**
 * @author Jason Feriante
 */
public class RBnode<K> {
	
	private K key;
	private RBnode<K> left, right;
	private enum Color {RED, BLACK};
	private Color color;
	private RBnode<K> p; // Parent
	
	// Default to red
	public RBnode(K key, RBnode<K> left, RBnode<K> right, RBnode<K> p) {
		this.key = key;
		this.left = left;
		this.right = right;
		this.color = Color.RED;
		this.p = p;
	}
	
	// Constructor with color
	public RBnode(K key, RBnode<K> left, RBnode<K> right, Color color, RBnode<K> p) {
		this.key = key;
		this.left = left;
		this.right = right;
		this.color = color;
		this.p = p;
	}

	public K getKey() { return key; }
	public RBnode<K> getLeft() { return left; }
	public RBnode<K> getRight() { return right; }
	public Color getColor() { return color; }
	
	public void setKey(K newK) { key = newK; }
	public void setLeft(RBnode<K> newL) { left = newL; }
	public void setRIght(RBnode<K> newR) { right = newR; }
	public void setColor(Color newColor) { color = newColor; }
	
	public void debug(){
		System.out.println("RBTnode<K> left:"+left+", right:"+right+", key:"+key);
	}
}
