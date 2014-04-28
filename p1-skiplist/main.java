import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		// testSkipList();
		// testRBTree();

//		long startTime = System.nanoTime();
//		try {
//			testZipCodeDataSet();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		long endTime = System.nanoTime();
//		long duration = endTime - startTime;
//		System.out.println("zip-code test took " + (double) (duration / 100000000.00) + "seconds");
		skipInsertionTests();
	}

	public static void testRBTree() {
		RedBlackBST<Integer, Integer> st = new RedBlackBST<Integer, Integer>();
		
		for (int i = 0; i < 50; i++) {
			st.put(i, i);
		}

		for (Integer s : st.keys())
			System.out.println(st.get(s));
		System.out.println();

		// System.out.println(rbtree.toString());
		// System.out.println("count: "+rbtree.size());
		// System.out.println("height: "+rbtree.height());
		// System.out.println("min: "+rbtree.min());
		// System.out.println("max: "+rbtree.max());
	}

	// Zip Code Skip List Tests
	public static void testZipCodeDataSet() throws IOException {
		String file = "zipcodes";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		int count = 0;
		int zipcode;
		int[] zips = new int[42151];
		SkipList S = new SkipList();
		while ((line = br.readLine()) != null) {
			// System.out.println(line);
			zipcode = Integer.parseInt(line.toString());
			zips[count] = zipcode;
//			S.insert("" + zipcode, zipcode);
			count++;
		}
		
		// Insert zip codes descending:
//		for (int i = zips.length - 1; i > 0; i--) {
//			S.insert("" + zips[i], zips[i]);
//		}

		// Randomize zip codes:
		Collections.shuffle(Arrays.asList(zips));
		
		// insert zip-codes randomly:
		for (int i = 0; i < zips.length; i++) {
			S.insert("" + zips[i], zips[i]);
		}
		
//		S.printHorizontal();
		System.out.println("Height:" + S.getHeight() + ", # Nodes:"
				+ S.getNodeCount());
//		System.out.println("total zips processed:" + count);
	}
	
	public static void skipInsertionTests(){
		Random r = new Random();
		SkipList S = new SkipList();
		int x = 0;
		for (int i = 0; i < 50; i++) {
			x = r.nextInt(50) + 1;
			if(r.nextInt(10) == r.nextInt(10)){
				// do a cluster of repeats!!
//				for (int j = 0; j < 1000; j++) {
//					S.insert("" + x, x);
//				}
//				System.out.println("repeated!!!!!!!!!!!!!!!!!! "+x);
				S.insert("" + x, x);
			} else {
				// insert once..
				S.insert("" + x, x);
			}
		}
//		S.printHorizontal();
		S.printVertical();

		System.out.println("\n\nHeight:" + S.getHeight() + ", # Nodes:"
				+ S.getNodeCount());
	}

	// General Skip List Tests; NOTE: THIS TAKES A WHILE!!!!!!!!!!
	public static void testSkipList() {
		Random r = new Random();

		double all_heights = 0;

		int j;
		for (j = 0; j < 1000; j++) {

			SkipList S = new SkipList();

			int i;
			for (i = 0; i < 100000; i++) {
				S.insert("" + i, i);
			}
			all_heights += S.getHeight();
		}
		System.out
				.println("average height of skiplist with 100,000 nodes after 1000 tests:"
						+ all_heights / j);
		// S.printHorizontal();
		// S.printVertical();
		// System.out.println("");
		// System.out.println("Height:" + S.getHeight() + ", # Nodes:"
		// + S.getNodeCount());
	}

}
