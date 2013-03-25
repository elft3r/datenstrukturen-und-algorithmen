package serie05;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	class Pair<S, T> {
		S fst;
		T snd;
	}
	
	class BinarySearchTree {
		Node root;

		public void add(int value) {
			if (root == null) {
				root = new Node(value);
			} else {
				add(root, value);
			}
		}

		public void add(Node node, int value) {
			while (true) {
				if (value <= node.value) {
					// first we check whether to traverse left
					if (node.leftChild == null) {
						node.leftChild = new Node(value);
						break;
					} else {
						node = node.leftChild;
					}
				} else {
					// here we take the right subtree
					if (node.rightChild == null) {
						node.rightChild = new Node(value);
						break;
					} else {
						node = node.rightChild;
					}
				}
			}
		}

		public String visit() {
			return visitRec(root).fst.trim();
		}

		private Pair<String, Integer> visitRec(Node node) {
			Pair<String, Integer> tmp = null;
			
			// standard value for left and right depth, wenn the childs are null
			int leftDepth = 0;
			int rightDepth = 0;
			
			// String we want to return
			String resString = "";
			
			if (node.leftChild != null) {
				// for the left child we recursively drill down
				tmp = visitRec(node.leftChild);
				
				// store the values for the left subtree
				leftDepth = tmp.snd;
				resString = tmp.fst;
			}
			
			if(node.rightChild != null) {
				// for the right child we also recursively drill down
				tmp = visitRec(node.rightChild);
				
				// store the values for the right subtree
				rightDepth = tmp.snd;
				resString += tmp.fst;
			}
			
			resString += " " + node.value + " " + leftDepth + " " + rightDepth;

			Pair<String, Integer> res = new Pair<String, Integer>();
			res.fst = resString;
			res.snd = Math.max(++leftDepth, ++rightDepth);
			
			return res;
		}
	}

	class Node {
		int value;
		Node leftChild;
		Node rightChild;

		Node(int value) {
			this.value = value;
			leftChild = null;
			rightChild = null;
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
				// for each test instance we create a new tree
				BinarySearchTree tree = new BinarySearchTree();
				
				// get the number of the values we need to add to the tree and add all to the tree
				int nrOfKeys = in.nextInt();
				for (int j = 0; j < nrOfKeys; j++) {
					tree.add(in.nextInt());
				}
				
				res.add(tree.visit());
			}
		} finally {
			if (in != null)
				in.close();
		}

		return res;
	}
}
