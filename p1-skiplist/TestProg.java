import java.util.*;

public class TestProg {

	// Tianbo's test
	public static void main(String[] args) {

		Random r = new Random();
		SkipList S = new SkipList();

		int i;

		for (i = 0; i < 12; i++) {
			S.insert("" + i, i);
		}
		S.remove("4", 4);
		S.remove("7", 7);
		// S.printHorizontal();
		// System.out.println("------");
		S.printVertical();

	}

}