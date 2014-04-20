
public class main {

	public main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testRBTree();
	}
	
	public static void testRBTree() {
		// @todo (confirm the red-black tree works correctly....)
		
		RedBlackTree<Integer> rbtree = new RedBlackTree<Integer>(30);
		try {
			rbtree.insert(50);
//			rbtree.insert(20);
//			rbtree.insert(10);
//			rbtree.insert(40);
//			rbtree.insert(25);
//			rbtree.insert(35);
		} catch (DuplicateException e) {
			e.printStackTrace();
		}
		
		// Should show: 10,20,25,30,35
		rbtree.print();
	}

}
