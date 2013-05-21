package serie11;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	private class Point implements Comparable<Point> {
		int x;
		int y;

		private Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return this.x != o.x ? this.x - o.x : this.y - o.y;
		}
		
		@Override
		public String toString() {
			return this.x + " " + this.y;
		}
	}

	private class MonotonChain {
		
		private List<Point> convexHull = new ArrayList<Point>();

		public void calculateConvexHull(List<Point> points) {
			/** First sort the points */
			Collections.sort(points);

			/** Calculate the upper part of the hull */
			List<Point> upper = new ArrayList<Point>();
			for (Point p : points) {
				while ((upper.size() > 1) && (ccw(upper.get(upper.size() - 2), upper.get(upper.size() - 1), p) > -1)) {
					upper.remove(upper.size() - 1);
				}
				
				upper.add(p);
			}
			
			// the last item of the upper hull will be the first of the lower hull
			if(upper.size() > 0) {
				upper.remove(upper.size() - 1);
			}
			
			/** Calculate the lower part of the hull */
			List<Point> lower = new ArrayList<Point>();
			for(int i = points.size() - 1; i >= 0; i--) {
				Point p = points.get(i);
				while((lower.size() > 1) && (ccw(lower.get(lower.size() - 2), lower.get(lower.size() - 1), p) > -1)) {
					lower.remove(lower.size() - 1);
				}
				
				lower.add(p);
			}
			
			// the last item of the lower hull will be the first of the upper hull
			if(lower.size() > 0) {
				lower.remove(lower.size() - 1);
			}

			convexHull.addAll(upper);
			convexHull.addAll(lower);
		}

		/**
		 * The points are:
		 * <ul>
		 * <li>clockwise: if ccw < 0</li>
		 * <li>collinear: if ccw = 0</li>
		 * <li>counter-clockwise: if ccw > 0</li>
		 * </ul>
		 */
		private int ccw(Point p1, Point p2, Point p3) {
			return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
		}

		public String getConvexHullAsString() {
			String res = "";

			for(Point p : convexHull) {
				res += p + " ";
			}
			
			return res.trim();
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
				MonotonChain gs = new MonotonChain();

				// get the points and calculate the convex hull
				List<Point> points = getPoints(in);
				gs.calculateConvexHull(points);

				// get the convex hull as string and add it to the result
				res.add(gs.getConvexHullAsString());
			}
		} finally {
			if (in != null)
				in.close();
		}

		return res;
	}

	private List<Point> getPoints(Scanner in) {
		List<Point> res = new ArrayList<Main.Point>();

		int pointsCount = in.nextInt();
		for (int i = 0; i < pointsCount; i++) {
			int x = in.nextInt();
			int y = in.nextInt();

			res.add(new Point(x, y));
		}

		return res;
	}
}
