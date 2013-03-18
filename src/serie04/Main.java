package serie04;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	abstract class Hash {
		int collisions = 0;
		int[] hashtable;

		Hash(int count) {
			hashtable = new int[count];
			Arrays.fill(hashtable, 0);
		}

		void insert(int val) {
			int tmp = val % hashtable.length;
			
			if (hashtable[tmp] == 0)
				hashtable[tmp] = val;
			else {
				for(int cc = 1;; cc++) {
					collisions++;
					int index = (tmp - getNextIndex(cc, val)) % hashtable.length;
					
					if(index < 0)
						index = hashtable.length + index;
					
					if (hashtable[index] == 0) {
						hashtable[index] = val;
						break;
					}
				}
			}
		}

		String createOutput() {
			String res = collisions + " ";

			for (int i = 0; i < hashtable.length; i++)
				res += hashtable[i] + " ";

			return res.trim();
		}

		abstract int getNextIndex(int cc, int val);
	}

	class LinearProbing extends Hash {

		LinearProbing(int count) {
			super(count);
		}

		@Override
		int getNextIndex(int cc, int val) {
			return cc;
		}
	}

	class QuadraticProbing extends Hash {

		QuadraticProbing(int count) {
			super(count);
		}

		@Override
		int getNextIndex(int cc, int val) {
			return (int) (Math.pow((int)Math.round(cc / 2.0), 2.0) * Math.pow(-1, cc));
		}
	}

	class DoubleHashing extends Hash {

		DoubleHashing(int count) {
			super(count);
		}

		@Override
		int getNextIndex(int cc, int val) {
			return cc * (1 + (val % (hashtable.length - 2)));
		}
	}

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
			// first we need to get rid of the first line
			in.nextInt();

			// iterate over all test instances
			while (in.hasNextInt()) {
				int count = in.nextInt();
				int n = in.nextInt();

				Hash lp = new LinearProbing(count);
				Hash qp = new QuadraticProbing(count);
				Hash dh = new DoubleHashing(count);

				// first we store the values so that we can reuse them later
				for (int i = 0; i < n; i++) {
					int newVal = in.nextInt();
					lp.insert(newVal);
					qp.insert(newVal);
					dh.insert(newVal);
				}

				res.add(lp.createOutput());
				res.add(qp.createOutput());
				res.add(dh.createOutput());
			}
		} finally {
			if (in != null)
				in.close();
		}

		return res;
	}
}
