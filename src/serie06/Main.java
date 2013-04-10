package serie06;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
				// get the number of the values and create an array of that size
				int nrOfKeys = in.nextInt();
				int[] array = new int[nrOfKeys];

				for (int j = 0; j < nrOfKeys; j++) {
					array[j] = in.nextInt();
				}

				res.add(blum(array, 0, array.length));
			}
		} finally {
			if (in != null)
				in.close();
		}

		return res;
	}

	private String blum(int[] array, int left, int right) {
		if (left == right)
			return "" + array[left];

		String res = "";

		// get the medians of the given array
		int[] medians = getMedians(array, right, left);
		for (int m : medians) {
			res += m + " ";
		}

		// get the median of the medians
		int pivot = getMedianOfArray(medians);
		res += pivot + " ";

		// now we sort the part of the array
		int pivotIdx = sortSubArray(array, left, right - 1, pivot);

		// get the median position in the array
		int medianIdx = getMedianPosition(array);

		// figure out whether we already have the pivot on the median position, otherwise we
		// recursively iterate until we have it
		if (pivotIdx == medianIdx) {
			res += pivot;
		} else if (medianIdx > pivotIdx) {
			blum(array, pivotIdx + 1, right);
			res += array[medianIdx];
		} else if (medianIdx < pivotIdx) {
			blum(array, left, pivotIdx);
			res += array[medianIdx];
		}

		return res.trim();
	}

	private int[] getMedians(int[] array, int right, int left) {
		// create an array in which we can store the medians
		int medians[] = new int[(int) Math.ceil((right - left) / 5.0)];

		// now extract the medians of each group of 5
		int j = 0;
		for (int i = left; i < right; i += 5) {
			int r = i + 5;
			if (r > right) {
				r = right;
			}

			medians[j++] = getMedianOfArray(Arrays.copyOfRange(array, i, r));
		}

		return medians;
	}

	private int getMedianOfArray(int[] copyOfRange) {
		Arrays.sort(copyOfRange);
		return copyOfRange[getMedianPosition(copyOfRange)];
	}

	private int getMedianPosition(int[] copyOfRange) {
		int medianPos = (int) Math.floor(copyOfRange.length / 2.0);

		// for the even numbers we need to use the lower value
		if (copyOfRange.length % 2 == 0 && copyOfRange.length != 0) {
			medianPos--;
		}

		return medianPos;
	}

	private int sortSubArray(int[] array, int left, int right, int pivot) {
		int pivotIdx = -1;

		// get the index of the pivot element
		for (int i = 0; i < array.length; i++) {
			if (array[i] == pivot) {
				pivotIdx = i;
				break;
			}
		}

		swap(array, pivotIdx, right);
		int storeIdx = left;

		for (int i = left; i < right; i++) {
			if (array[i] <= pivot) {
				swap(array, i, storeIdx);
				storeIdx++;
			}
		}

		swap(array, storeIdx, right);

		return storeIdx;
	}

	private void swap(int[] array, int a, int b) {
		int tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
}
