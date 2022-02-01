package hw7;

import static org.junit.Assert.*;

import org.junit.Test;

public class ToyTest {
	
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
		
		
		// Move according to path
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
		
		// Make sure it is not longer than shortest distance.
		assertTrue(path.length() <= distance);
	}

	@Test
	public void toyTest1() {
		
		String[] data = 
			{
					"s         ",
					"          ",
					"  *       ",
					"  *  *****",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *      f"
			};
			
		
		char[][] grid;
		/* Solution to this grid is hardcoded */
		grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		
		checkSol(grid, solution, 18);
		
		/* Hardcoded solution does not solve this grid */
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 18);
	}
	
	@Test
	public void toyTest2() {
		String[] data = 
			{
					"*s  ",
					" ** ",
					"    ",
					" * f",
					
			};
		char[][] grid;
		
		grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		
		checkSol(grid, solution, 7);
		
		
	}
	@Test
	public void toyTest3() {
		String[] data =
			{
					"s***",
					"****",
					"**  ",
					"f   ",
			};
			
		char[][] grid;
		grid = GridUtilities.fromStringArray(data);
		
		String solution = Solver.solve(grid);
		
		assertNull(solution);
		grid=GridUtilities.rotateClockwise(grid);
		solution=Solver.solve(grid);
		assertNull(solution);
}
	
	@Test
	public void toyTest4() {
		String[] data =
			{
					"*s***",
					"* *  ",
					"  *  ",
					" *   ",
					"   *f",
					
			};
			
		char[][] grid;
		grid = GridUtilities.fromStringArray(data);
		
		String solution = Solver.solve(grid);
		
		checkSol(grid,solution,11);
		grid=GridUtilities.rotateClockwise(grid);
		solution=Solver.solve(grid);
		checkSol(grid,solution,11);
	}
	
	@Test
	public void toyTest5() {
		String[] data =
			{
					"*s***",
					"* *  ",
					"  *  ",
					" *  *",
					"   *f",
					
			};
			
		char[][] grid;
		grid = GridUtilities.fromStringArray(data);
		
		String solution = Solver.solve(grid);
		
		assertNull(solution);
		grid=GridUtilities.rotateClockwise(grid);
		solution=Solver.solve(grid);
		assertNull(solution);
	}
}
