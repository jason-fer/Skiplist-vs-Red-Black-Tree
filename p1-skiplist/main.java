
public class main {

	public main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Integer a = 10;
//		Integer b = 100;
//		int rs = a.compareTo(b);
//		System.out.println("a:"+a+" vs b:"+b+", result:"+rs);
//		
//		a = 20;
//		b = 10;
//		rs = a.compareTo(b);
//		System.out.println("a:"+a+" vs b:"+b+", result:"+rs);
//		
//		a = 10;
//		b = 10;
//		rs = a.compareTo(b);
//		System.out.println("a:"+a+" vs b:"+b+", result:"+rs);
		
		testRBTree();
	}
	
	public static void testRBTree() {
		// @todo (confirm the red-black tree works correctly....)
		
		RedBlackTree<Integer> rbtree = new RedBlackTree<Integer>();
		try {
			int i = 1;
			while(i <= 10){
				rbtree.insert(i);
//				System.out.println("insert i:"+i);
				i = i + 1;
			}
//			rbtree.insert(50);
//			rbtree.insert(20);
//			rbtree.insert(30);
//			rbtree.insert(40);
//			rbtree.insert(25);
//			rbtree.insert(35);
//			rbtree.insert(90);
//			rbtree.insert(100);
//			rbtree.insert(110);
//			rbtree.insert(11);
//			rbtree.insert(13);
			
//			rbtree.insert(12);
//			rbtree.insert(23);
//			rbtree.insert(24);
		} catch (DuplicateException e) {
			e.printStackTrace();
		}
		// Should show: 10,20,25,30,35
		System.out.println("Legend: [XX] indicates a red node.");
		System.out.println("");
		rbtree.print();
		System.out.println("");
		System.out.println("count: "+rbtree.count());
		System.out.println("height: "+rbtree.height());
		System.out.println("min: "+rbtree.min());
		System.out.println("max: "+rbtree.max());
	}

}
//