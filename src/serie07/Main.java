package serie07;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
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
				int n = in.nextInt();
				int w = in.nextInt();

				int[][] array = new int[2][n];

				// first we fill in the first line
				for (int j = 0; j < n; j++) {
					array[0][j] = in.nextInt();
				}

				// the we fill in the second line
				for (int j = 0; j < n; j++) {
					array[1][j] = in.nextInt();
				}

				res.add(knapsack(array, w));
			}
		} finally {
			if (in != null)
				in.close();
		}

		return res;
	}

	public String knapsack(int[][] input, int w) {
		String res = "";

		// int array has default values 0, so we do not need to initialize it
		int[][] tmpArray = new int[input[0].length + 1][w + 1];

		// fill the matrix
		for (int i = 1; i < tmpArray.length; i++) {
			for (int j = 1; j < tmpArray[i].length; j++) {
				int val = tmpArray[i - 1][j];
				int vi = input[0][i - 1];
				int wi = input[1][i - 1];
				
				if (wi <= j) {
					int tmpVal = tmpArray[i - 1][j - wi] + vi;
					if(tmpVal >= val) {
						val = tmpVal;
					}
				}

				tmpArray[i][j] = val;
			}
		}
		
		// first we add the max value to the result string
		int n = tmpArray.length - 1;
		res += tmpArray[n][w];
		
		// now we need to find the optimal solution
		List<Integer> list = new ArrayList<Integer>();
		for(; n > 0; n--) {
			int wn = input[1][n - 1];
			int vn = input[0][n - 1];
			
			if(wn <= w && tmpArray[n][w] == tmpArray[n - 1][w - wn] + vn) {
				list.add(n);
				w -= wn;
			}
		}
		
		// now we sort the list, and return it
		Collections.sort(list);
		for(Integer i : list) {
			res += " " + i;
		}

		return res;
	}
}
