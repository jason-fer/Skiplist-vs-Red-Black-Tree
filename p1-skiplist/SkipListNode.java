/*Skip List Node structure*/
public class SkipListNode {
	public String key; //each node has a key, the numeric value of which
					   //is same as the value of the node, except for 
					   //the negInf and posInf
	public Integer value; //each node has a value
	public int pos;      // Only for printing the Skip List
	 
	public SkipListNode up; //up link
	public SkipListNode down; //down link
	public SkipListNode left; //left link
	public SkipListNode right; //right link
	
	public static String negInf = "negInf"; //key of negative infinity
	public static String posInf = "posInf"; //key of positive infinity
	
	/*The constructor constructs a single Skip List Node
	 *with a string as key and an integer as value
	 */
	public SkipListNode(String k, Integer v) {
		this.key = k;
		this.value = v;
		this.up = this.down = this.left = this.right = null;
	}
	
	 public Integer getValue() 
	  { 
	    return value; 
	  }

	  public String getKey() 
	  { return key; 
	  }

	  public void setValue(Integer val) 
	  {
	    value = val;
	  }

	  public boolean equals(Object o) 
	  {
	    SkipListNode e;

	    try 
	    { 
	      e = (SkipListNode) o;    // Test if o is a SkipListEntry...
	    }
	    catch (ClassCastException ex) 
	    { 
		return false; 
	    }

	    return (e.getKey() == key) && (e.getValue() == value);
	  }

	  public String toString() 
	  {
	    return "(" + key + "," + value + ")";
	  }
}
