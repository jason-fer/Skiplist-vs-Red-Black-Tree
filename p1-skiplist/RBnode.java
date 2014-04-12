/**
 * @author Jason Feriante
 * Adapted from BSTNode as taught in CS367.
 */
public class RBnode<K> {
	
	private K key;
	private RBnode<K> left, right;
	
	public RBnode(K key, RBnode<K> left, RBnode<K> right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}

	public K getKey() { return key; }
	public RBnode<K> getLeft() { return left; }
	public RBnode<K> getRight() { return right; }
	
	public void setKey(K newK) { key = newK; }
	public void setLeft(RBnode<K> newL) { left = newL; }
	public void setRIght(RBnode<K> newR) { right = newR; }
	
	public void debug(){
		System.out.println("RBTnode<K> left:"+left+", right:"+right+", key:"+key);
	}
}
