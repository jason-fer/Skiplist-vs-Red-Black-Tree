
public class main {

	public main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		testRBTree();
	}
	
	public static void testRBTree() {
    	RedBlackBST<Integer, Integer> st = new RedBlackBST<Integer, Integer>();
    	
        for (int i = 0;i < 50; i++) { st.put(i, i); }

        for (Integer s : st.keys())
            System.out.println(st.get(s));
        System.out.println();
        
//		System.out.println(rbtree.toString());
		
//		System.out.println("count: "+rbtree.size());
//		System.out.println("height: "+rbtree.height());
//		System.out.println("min: "+rbtree.min());
//		System.out.println("max: "+rbtree.max());
	}

}
