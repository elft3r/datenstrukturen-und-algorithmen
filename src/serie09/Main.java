package serie09;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	private class Kruskal {
		private Map<Integer, Node> vertices;
		PriorityQueue<Edge> edges;

		public Kruskal(Scanner in) {
			int n = in.nextInt();
			int m = in.nextInt();

			initVertices(n);
			initPriorityQueue(m, in);
		}

		/**
		 * We create for each vertex an entry in the map to keep track whether or not they are
		 * already completely merged
		 */
		private void initVertices(int n) {
			vertices = new HashMap<Integer, Node>();

			for (int i = 1; i <= n; i++) {
				vertices.put(new Integer(i), makeSet(i));
			}
		}

		/**
		 * We store all edges in a priority queue so that we always retrieve the one with the lowest costs
		 */
		private void initPriorityQueue(int m, Scanner in) {
			edges = new PriorityQueue<Edge>();

			for (int i = 0; i < m; i++) {
				Edge edge = new Edge();
				edge.u = vertices.get(in.nextInt());
				edge.v = vertices.get(in.nextInt());
				edge.cost = in.nextInt();

				edges.add(edge);
			}
		}

		private int minSpanningTreeCost() {
			int res = Integer.MIN_VALUE;

			// merge the single vertices until we have one entry in the map
			while (vertices.size() > 1) {
				Edge e = edges.poll();
				Node u = find(e.u);
				Node v = find(e.v);

				if (u != v) {
					union(e);
				}
			}

			// make sure that we have only one entry and return the costs calculated
			if (vertices.size() == 1) {
				for (Entry<Integer, Node> entry : vertices.entrySet()) {
					res = entry.getValue().cost;
					break;
				}
			}

			return res;
		}

		private Node makeSet(int i) {
			Node n = new Node();
			n.parent = n;
			n.value = new Integer(i);
			n.rank = 0;
			n.cost = 0;

			return n;
		}

		private Node find(Node n) {
			if (!n.parent.equals(n)) {
				n.parent = find(n.parent);
			}

			return n.parent;
		}

		private void union(Edge e) {
			Node uRoot = find(e.u);
			Node vRoot = find(e.v);

			if (uRoot != vRoot) {
				if (uRoot.rank < vRoot.rank) {
					uRoot.parent = vRoot;
					vRoot.cost += uRoot.cost + e.cost;
					vertices.remove(uRoot.value);
				} else if (uRoot.rank > vRoot.rank) {
					vRoot.parent = uRoot;
					uRoot.cost += vRoot.cost + e.cost;
					vertices.remove(vRoot.value);
				} else {
					vRoot.parent = uRoot;
					uRoot.cost += vRoot.cost + e.cost;
					vRoot.rank++;
					vertices.remove(vRoot.value);
				}
			}
		}
	}

	private class Node {
		private Node parent;

		private Integer value;
		private Integer rank;
		private Integer cost;
	}

	private class Edge implements Comparable<Edge> {
		private Node u = null;
		private Node v = null;
		private Integer cost = Integer.MIN_VALUE;

		/**
		 * We need this so that the {@link PriorityQueue} can sort the single edges properly
		 */
		@Override
		public int compareTo(Edge edge) {
			int thisCost = this.cost;
			int edgeCost = edge.cost;

			return (thisCost < edgeCost ? -1 : (thisCost == edgeCost ? 0 : 1));
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
			// we get the nr of tests
			int nrOfTests = in.nextInt();

			for (int i = 0; i < nrOfTests; i++) {
				// init the datastructures we need to calculate the min spanning tree
				Kruskal kk = new Kruskal(in);
				res.add(Integer.toString(kk.minSpanningTreeCost()));
			}
		} finally {
			if (in != null)
				in.close();
		}

		return res;
	}
}
