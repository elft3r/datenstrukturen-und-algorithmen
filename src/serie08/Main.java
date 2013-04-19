package serie08;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();

		// call the method which executes the program
		List<String> res = main.doIt(System.in);

		// print the result
		for (String entry : res)
			System.out.println(entry);
	}

	public List<String> doIt(InputStream is) {
		List<String> res = new ArrayList<String>();

		Scanner in = null;
		try {
			in = new Scanner(is);
			// we get the nr of tests
			int nrOfTests = in.nextInt();

			for (int i = 0; i < nrOfTests; i++) {
				// the first line contains two values, n and W
				String a = in.next();
				String b = in.next();

				res.add(longestCommonSubsequence(a, b));
			}
		} finally {
			if (in != null)
				in.close();
		}

		return res;
	}

	public String longestCommonSubsequence(String a, String b) {
		String res = "";
		int aLength = a.length();
		int bLength = b.length();

		int[][] array = new int[aLength + 1][bLength + 1];

		for (int i = 1; i < array.length; i++) {
			for (int j = 1; j < array[i].length; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					array[i][j] = array[i - 1][j - 1] + 1;
				} else {
					array[i][j] = Math.max(array[i - 1][j], array[i][j - 1]);
				}
			}
		}

		int count = array[aLength][bLength];
		res += count;

		// decrease the values of the string pointers by one
		aLength--;
		bLength--;

		// create the array with values
		String subSeq = "";
		for (int i = count - 1; i >= 0; i--) {
			while (a.charAt(aLength) != b.charAt(bLength)) {
				if (aLength > 0 && array[aLength + 1][bLength + 1] == array[aLength][bLength + 1]) {
						 aLength--;
				} else {
						bLength--;
				}
			}
			
			subSeq = a.charAt(aLength) + subSeq;
			aLength--;
			bLength--;
		}

		return (res + " " + subSeq).trim();
	}
}
