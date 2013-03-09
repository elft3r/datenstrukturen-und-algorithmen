package serie03;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	class Heap {
		private int[] heap;

		// index of last element in the heap
		private int lastElIndex = -1;
		
		public Heap(int size) {
			this.heap = new int[size];
		}

		public void insert(int i) {
			heap[++lastElIndex] = i;
			
			heapify(lastElIndex);
		}

		public int extractMin() {
			int min = heap[0];
			
			heap[0] = heap[lastElIndex--];
			
			// make sure that we have the heap condition fulfilled
			siftDown(0, lastElIndex);
			
			return min;
		}

		public int queryLast() {
			return heap[lastElIndex];
		}
		
		private void heapify(int count) {
			int start = (count - 1) / 2;
			
			while(start >= 0) {
				siftDown(start, count);
				start--;
			}
		}
		
		private void siftDown(int start, int end) {
			// as long as we have a left son we can go on
			while(2*start + 1 <= end) {
				// check which child we need for the swap check
				int child = 2 * start + 1;
				if(child + 1 <= end)
					child = heap[child] > heap[child + 1] ? child + 1 : child;
					
				// now swap the values and go to the 'subtree' of the bigger value
				if(heap[start] > heap[child])
					swap(start, child);
				else
					break;
				
				start = child;
			}
		}

		private void swap(int i, int j) {
			int tmp = heap[i];
			heap[i] = heap[j];
			heap[j] = tmp;
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
				// the first int tells us how many elements this line has, which we use for creating
				// a new Heap object with a array of that size
				int count = in.nextInt();
				Heap heap = new Heap(count);
				
				String queryLast = "";
				String extractMin = "";

				// fill the heap and get after every insert the last element in the heap
				for (int i = 0; i < count; i++) {
					heap.insert(in.nextInt());
					queryLast += heap.queryLast() + " ";
				}
				res.add(queryLast.trim());

				// finally get all elements out of the heap again
				for (int i = 0; i < count; i++)
					extractMin += heap.extractMin() + " ";
				
				res.add(extractMin.trim());
			}
		} finally {
			if (in != null)
				in.close();
		}

		return res;
	}
}
