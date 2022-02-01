package hw7;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class HW7Test {
	
	private char[][] copyGrid(char[][] orig) {
		char[][] copy = new char[orig.length][];
		for(int i = 0; i < copy.length; i++) {
			copy[i] = Arrays.copyOf(orig[i],  orig[i].length);
		}
		return copy;
	}
	
	private void checkSol(char[][] grid, String path, int distance) {
		int size = grid.length;
		int row = -1;
		int col = -1;
		
		// Start at s;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if (grid[i][j] == 's') {
					row = i;
					col = j;
				}
			}
		}
		
		for(char c : path.toCharArray()) {
			switch(c) {
			case 'U':
				row -= 1;
				break;
			case 'D':
				row += 1;
				break;
			case 'R':
				col += 1;
				break;
			case 'L':
				col -= 1;
				break;
			default:
				fail("Illegal character in solution: " + c);
			}
			// Make sure you haven't moved outside the grid.
			assertTrue(row >= 0 && row < size && col >= 0 && col < size);
			// Make sure you haven't moved into a '*'
			assertTrue(grid[row][col] != '*');
		}
		// Make sure you end at 'f'
		assertTrue(grid[row][col] == 'f');
		
		// Make sure it is shortest.
		assertTrue(path.length() <= distance);
	}

	@Test
	public void test05NoPath() {
		String[] data =
			{
					"                    ",
					"  s                 ",
					"                    ",
					"****************** *",
					"                    ",
					"                    ",
					"**** ***************",
					"                    ",
					"                    ",
					"**************** ***",
					"                    ",
					"                    ",
					"********************",
					"                    ",
					"                    ",
					"******************* ",
					"                    ",
					"                    ",
					" *******************",
					"                   f",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		assertNull(solution);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		assertNull(solution);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		assertNull(solution);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		assertNull(solution);
	}
	
	@Test
	public void test05Empty() {
		String[] data =
			{
					"                    ",
					"  s                 ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                  f ",
					"                    ",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 32);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 32);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 32);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 32);
	}
	
	@Test
	public void test10KeyHole() {
		String[] data =
			{
					"                    ",
					"  s                 ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"  ******** ******** ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                  f ",
					"                    ",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 32);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 32);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 32);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 32);
	}
	
	@Test
	public void test10singleObstacle() {
		String[] data =
			{
					"                    ",
					"  s                 ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					" **************     ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"    f               ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 24);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 24);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 24);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 24);
	}
	
	@Test
	public void test10DoubleObstacle() {
		String[] data =
			{
					"                    ",
					"  s                 ",
					"                    ",
					"                    ",
					" **************     ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					" **************     ",
					"                    ",
					"                    ",
					"                    ",
					"    f               ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 24);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 24);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 24);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 24);
	}
	
	@Test
	public void test10ZigZag() {
		String[] data =
			{
					"       s            ",
					"                    ",
					"                    ",
					"                    ",
					"******************* ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					" *******************",
					"                    ",
					"                    ",
					"                    ",
					"       f            ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 57);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 57);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 57);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 57);
	}
	
	@Test
	public void test10ZigZagZig() {
		String[] data =
			{
					"       s            ",
					"                    ",
					"                    ",
					"                    ",
					"******************* ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					" *******************",
					"                    ",
					"******************* ",
					"                    ",
					"       f            ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 81);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 81);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 81);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 81);
	}
	
	@Test
	public void test10L() {
		String[] data =
			{
					"                    ",
					"      *             ",
					"      *             ",
					"      *             ",
					"   s  *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *           f ",
					"      **************",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 36);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 36);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 36);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 36);
	}
	
	@Test
	public void test10T1() {
		String[] data =
			{
					"                    ",
					"                    ",
					"                    ",
					"   **************   ",
					"         *          ",
					"       s * f        ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 20);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 20);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 20);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 20);
	}
	
	@Test
	public void test10T2() {
		String[] data =
			{
					"                    ",
					"                    ",
					"                    ",
					"     *********      ",
					"         *          ",
					"       s * f        ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 22);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 22);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 22);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 22);
	}
	
	@Test
	public void test10Maze() {
		String[] data =
			{
					"             *       ",
					" *** *** * * * *** **",
					"  s*   * * * *   *   ",
					" ******* ******* ****",
					"   *   * *           ",
					" *** * * * ***** *** ",
					" * * *   * *   * * * ",
					" * * * * * * * * * * ",
					"   * * *   * * * * * ",
					"** * *** *** * * * * ",
					"   * * *     * * * * ",
					" * * * ******* * * * ",
					" * *           *   * ",
					" * ************* ****",
					" *     *   *         ",
					" ******* * * ******* ",
					"   *     *   *     * ",
					" * *** * ***** *** **",
					" *     *   * *   * *f",
					" ********* * *** * * ",
					"         *       *   "
			};
		
		char[][] grid = GridUtilities.fromStringArray(data);
		char[][] copy = copyGrid(grid);
		String solution = Solver.solve(grid);
		checkSol(copy, solution, 62);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 62);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 62);
		grid = GridUtilities.rotateClockwise(copy);
		copy = copyGrid(grid);
		solution = Solver.solve(grid);
		checkSol(copy, solution, 62);
	}

	/*
	@Test
	public void test05Long() {
		int REPS = 400;
		int size = REPS * 4 + 1;
		char[] flushL = new char[size];
		char[] flushR = new char[size];
		char[] spaces = new char[size];
		
		for(int i = 1; i < size-1; i++) {
			flushL[i] = '*';
			flushR[i] = '*';
			spaces[i] = ' ';
		}
		flushL[0] = '*';
		flushL[size-1] = ' ';
		flushR[0] = ' ';
		flushR[size-1] = '*';
		spaces[0] = ' ';
		spaces[size-1] = ' ';
		
		char[][] grid = new char[size][];
		grid[0] = spaces;
		for(int i = 1; i < size; i += 4) {
			grid[i] = flushL;
			grid[i+1] = spaces;
			grid[i+2] = flushR;
			grid[i+3] = spaces;
		}
		grid[0][0] = 's';
		grid[size-1][size-1] = 'f';
		int length = (size - 1) * (size + 1) / 2 + size - 1;
		String solution = Solver.solve(grid);
		checkSol(copy, solution, length);
		grid = GridUtilities.rotateClockwise(copy);
		solution = Solver.solve(grid);
		checkSol(copy, solution, length);
		grid = GridUtilities.rotateClockwise(copy);
		solution = Solver.solve(grid);
		checkSol(copy, solution, length);
		grid = GridUtilities.rotateClockwise(copy);
		solution = Solver.solve(grid);
		checkSol(copy, solution, length);
	}
	*/
}
