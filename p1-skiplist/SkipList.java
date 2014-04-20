import java.util.Random;
public class SkipList {
	/*Usage of head and tail allows us
	 *to easily add a new layer above the top layer
	 */
	public SkipListNode head; //first element of the top level
	public SkipListNode tail; //last element of the top level
	
	public int n; //number of nodes in the Skip List
	public int h; //Height of the Skip List
	public Random r; //Coin toss to determine the height of a newly added Node
	
	/*the constructor will construct an empty Skip List with one layer*/
	public SkipList() {
		SkipListNode p1, p2;
		/*create negInf and posInf objects as head and tail*/
		p1 = new SkipListNode(SkipListNode.negInf, null);
		p2 = new SkipListNode(SkipListNode.posInf, null);
		p1.right = p2;
		p2.left = p1;
		this.head = p1;
		this.tail = p2;
		/*Other initializations*/
		n = 0; //no nodes in Skip List
		h = 0; //Height is 0;
		r = new Random(); //Make random object to simulate coin toss
	}
	
	/*findNode(v): find the largest value x <= v on
	 * the lowest level of the Skip List
	 */
	public SkipListNode findNode(Integer v) {
		SkipListNode p;
		p = head; //start at the head node
		while(true){
			/*1.Search right until we find a Node with value larger than k*/
			while((p.right.key) != SkipListNode.posInf &&
					(p.right.value <= v)){
				p = p.right; //Move the right
			}
			/*2.After 1, go down one level if you can*/
			if(p.down != null){
				p = p.down; //Go downwards
			}
			/*Repeat 1 & 2 until we reach the lowest level, then exit*/
			else{
				break;
			}
		}
		return(p); //Note: p.value <= k
	}
	
	/*insert(k,v): insert a Skip List Node*/
	public Integer insert(String k, Integer v){
		SkipListNode p, q;
		int i;
		/*1. Try find the node, if found, update the value and return*/
		p = findNode(v);
		if (v == p.value){
			return(v);
		}
		/*2. If the value is not found, p is now the largest node that
		 *   has a value <= v and is at the lowest level. Insert q=(k,v)
		 */
		q = new SkipListNode(k,v);
		q.left = p;
		q.right = p.right;
		p.right.left = q;
		p.right = q;
		/*3. Make a "tower" of the node inserted with a random height*/
		i = 0; //current level = 0;
		while (r.nextDouble() < 0.5){ //toss the coin and get head
			//build one more level
			/*check if we need to add one more layer*/
			if (i >= h) {//we reached the top level
				/*Create a top layer*/
				SkipListNode l1, l2;
				l1 = new SkipListNode(SkipListNode.negInf, null);
				l2 = new SkipListNode(SkipListNode.posInf, null);
				l1.right = l2;
				l1.down = head;
				l2.left = l1;
				l2.down = tail;
				head.up = l1;
				tail.up = l2;
				head = l1;
				tail = l2;
				h = h + 1;
			}
			/*Find the first on p's left with an up-link*/
			while (p.up == null){
				p = p.left;
			}
			p = p.up; //make p point to this up node
			/*Add one more node with key k and value v to the column*/
			SkipListNode e;
			e = new SkipListNode(k, v);
			e.left = p;
			e.right = p.right;
			e.down = q;
			p.right.left = e;
			p.right = e;
			q.up = e;
			q = e; //set q up for the next iteration(if there is one)
			i = i + 1;
		}
		n = n + 1; //one more distinct node in the Skip List
		return(null); //we inserted it, there's no old value
	}
	/*remove(k,v): Remove a Skip List Node*/
	public Integer remove(String k, Integer v){
		/*Locate it at the bottom level*/
		SkipListNode d = findNode(v);
		if (d.value != v){
			return(null); //Not found, don't remove
		}
		/*Starting from the bottom, travel up and remove each node*/
		while( d != null ){
			d.left.right = d.right;
			d.right.left = d.left;
			d = d.up;
		}
		return(v);
	}
	
	
	
	/*
	 * The source of printing methods below:
	 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
	 */
	
	public void printHorizontal()
	  {
	     String s = "";
	     int i;

	     SkipListNode p;

	     /* ----------------------------------
		Record the position of each Node
		---------------------------------- */
	     p = head;

	     while ( p.down != null )
	     {
	        p = p.down;
	     }

	     i = 0;
	     while ( p != null )
	     {
	        p.pos = i++;
	        p = p.right;
	     }

	     /* -------------------
		Print...
		------------------- */
	     p = head;

	     while ( p != null )
	     {
	        s = getOneRow( p );
		System.out.println(s);

	        p = p.down;
	     }
	  }

	  public String getOneRow( SkipListNode p )
	  {
	     String s;
	     int a, b, i;

	     a = 0;

	     s = "" + p.key;
	     p = p.right;


	     while ( p != null )
	     {
	        SkipListNode q;

	        q = p;
	        while (q.down != null)
		   q = q.down;
	        b = q.pos;

	        s = s + " <-";


	        for (i = a+1; i < b; i++)
	           s = s + "--------";
	 
	        s = s + "> " + p.key;

	        a = b;

	        p = p.right;
	     }

	     return(s);
	  }

	  public void printVertical()
	  {
	     String s = "";

	     SkipListNode p;

	     p = head;

	     while ( p.down != null )
	        p = p.down;

	     while ( p != null )
	     {
	        s = getOneColumn( p );
		System.out.println(s);

	        p = p.right;
	     }
	  }


	  public String getOneColumn( SkipListNode p )
	  {
	     String s = "";

	     while ( p != null )
	     {
	        s = s + " " + p.key;

	        p = p.up;
	     }

	     return(s);
	  }
}
