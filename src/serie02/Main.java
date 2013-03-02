package serie02;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();

		// call the method which executes the program
		List<Long> res = main.doIt(System.in);

		// print the result
		for (Long entry : res) {
			System.out.println(entry);
		}
	}

	public List<Long> doIt(InputStream is) {
		List<Long> res = new ArrayList<Long>();

		Scanner in = null;
		try {
			in = new Scanner(is);
			// first we need to get rid of the first line
			int nrOfLines = in.nextInt();

			// iterate over all test instances
			while (in.hasNextInt()) {
				// the first int tells us how many elements this line has
				int count = in.nextInt();

				int[] array = new int[count];
				for (int i = 0; i < count; i++) {
					array[i] = in.nextInt();
				}

				// calculate the Maximum-Subarray
				res.add(maxSubarray(array));
			}

			// do a check whether the number of test instances and test results are the same
			if (nrOfLines != res.size())
				throw new RuntimeException("Number of results do not match number of inputs!");
		} finally {
			if (in != null)
				in.close();
		}

		return res;
	}

	private Long maxSubarray(int[] array) {
		// initialize the needed variables with the smallest number they can have
		long scanMax = 0;
		long bisMax = 0;

		// now iterate over the given array
		for (int i = 0; i < array.length; i++) {
			int a = array[i];

			if (scanMax + a > 0)
				scanMax += a;
			else
				scanMax = 0;

			// if the new scanMax is bigger than the current max value, we take it
			bisMax = Math.max(scanMax, bisMax);
		}

		return new Long(bisMax);
	}

}
