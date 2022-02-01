package hw7;
/*
 * Using Breadth First Search, this program is designed to find the shortest path of least resistance, using a grid, 
 * from a starting point to an end point.
 * Appends the instructions to a string for navigation (e.g. U = up, L = left) and tests grids defined in HW7Test
 * against my solution.
 * Testing functions and GridUtilities was given by professor
 */
import java.util.Iterator;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.BreadthFirstPaths;

public class Solver {
	public static String solve(char[][] grid) {
		// TODO
		/*
		 * 1. Construct a graph using grid
		 * 2. Use BFS to find shortest path from start to finish
		 * 3. Return the sequence of moves to get from start to finish
		 */
		
		int size=grid.length;
		Graph t = new Graph(size*size);
		
		int c = 0; //vertex number
		int s = 0; //start
		int f = 0; //finish
		for (int i=0;i<size;i++) {
			for (int j =0;j<size;j++) {
				if (grid[i][j]=='s') {
					s = c;
				}
				if (grid[i][j]=='f') {
					f=c;
				}
				
				if (i+1 != size && grid[i][j]!='*' && grid[i+1][j]!='*') {
					t.addEdge(c, c+size);
				}
				if (j+1 != size && grid[i][j]!='*' && grid[i][j+1]!='*') {
					t.addEdge(c, c+1);
				}
				c++;
			}
		}
		
		BreadthFirstPaths bfs = new BreadthFirstPaths(t,s);
		if (bfs.pathTo(f)==null) {
			return null;
		}
		
		String path ="";
		int prev=-1;
		for (int w:bfs.pathTo(f)) {
			if (prev!=-1) {
				if (w-prev==1) {
					path+="R";
				}
				else if (prev-w==1) {
					path+="L";
				}
				else if (prev==w-size) {
					path+="D";
				}
				else {
					path+="U";
				}
			}
			prev=w;
		}
		
		
		
		return path;
		
	}
}
