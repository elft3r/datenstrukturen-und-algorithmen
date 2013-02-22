package serie01;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// call the method which executes the program
		List<Long> res = doIt(System.in);
		
		// print the result
		for(Long entry : res) {
			System.out.println(entry);
		}
	}

	public static List<Long> doIt(InputStream is) {
		List<Long> res = new ArrayList<Long>();
		
		Scanner in = null;
		try {
			in = new Scanner(is);
			// first we need to get rid of the first line
			int nrOfLines = in.nextInt();
			
			while (in.hasNextInt()) {
				// read the values out of the stream
				int i = in.nextInt();
				int a = in.nextInt();
				int b = in.nextInt();
				int c = in.nextInt();
				int d = in.nextInt();

				// store the values in an array
				res.add(calculateR(i, a, b, c, d));
			}
			
			if(nrOfLines != res.size())
				throw new RuntimeException("Number of results do not match number of inputs!");
		} finally {
			if(in != null)
				in.close();
		}
		
		return res;
	}

	public static long calculateR(int i, int a, int b, int c, int d) {
		long[] res = { 0, 0, 0 };

		for (int j = 0; j <= i; j++) {
			// first move all the values of r into the next array field so we
			// have them at the needed index
			res[2] = res[1];
			res[1] = res[0];

			// do the right execution depending on what i is
			switch (j) {
				case 0:
					res[0] = a;
					break;
				case 1:
					res[0] = b;
					break;
				default:
					res[0] = c * res[1] + d * res[2];
					break;
			}
		}

		// return the value that is on index 0, as we store at this position Ri
		return res[0];
	}
}
