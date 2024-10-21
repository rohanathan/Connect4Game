package com.game.connect4;

public class Grid {
	private char[][] grid;

	// Constructor initializes a 6x7 grid
	public Grid() {
		grid = new char[6][7];
		// Initialize the grid with empty spaces
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				grid[row][col] = ' ';
			}
		}
	}

	// Method to drop a disc in a specified column
	public boolean dropDisc(Disc disc, int column) {
		for (int row = grid.length - 1; row >= 0; row--) {
			if (grid[row][column] == ' ') {
				grid[row][column] = disc.getSymbol();
				disc.setPosition(row, column);
				return true;
			}
		}
		return false; // Column is full
	}

	// Check if the grid is full
	public boolean isFull() {
		for (int col = 0; col < 7; col++) {
			if (grid[0][col] == ' ') {
				return false;
			}
		}
		return true;
	}

	// Check if the column is valid
	public boolean isColumnValid(int column) {
		return column >= 0 && column < 7 && grid[0][column] == ' ';
	}

	// Check for a win condition
	public boolean isWinner(char player) {
		// Check for 4 across
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length - 3; col++) {
				if (grid[row][col] == player && grid[row][col + 1] == player && grid[row][col + 2] == player
						&& grid[row][col + 3] == player) {
					return true;
				}
			}
		}

		// Check for 4 up and down
		for (int row = 0; row < grid.length - 3; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == player && grid[row + 1][col] == player && grid[row + 2][col] == player
						&& grid[row + 3][col] == player) {
					return true;
				}
			}
		}

		// Check upward diagonal
		for (int row = 3; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length - 3; col++) {
				if (grid[row][col] == player && grid[row - 1][col + 1] == player && grid[row - 2][col + 2] == player
						&& grid[row - 3][col + 3] == player) {
					return true;
				}
			}
		}

		// Check downward diagonal
		for (int row = 0; row < grid.length - 3; row++) {
			for (int col = 0; col < grid[0].length - 3; col++) {
				if (grid[row][col] == player && grid[row + 1][col + 1] == player && grid[row + 2][col + 2] == player
						&& grid[row + 3][col + 3] == player) {
					return true;
				}
			}
		}

		return false;
	}

	// Method to convert the grid to a string for display
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  0   1   2   3   4   5   6\n");
		sb.append("-----------------------------\n");

		for (int row = 0; row < 6; row++) {
			sb.append("|");
			for (int col = 0; col < 7; col++) {
				sb.append(String.format(" %c |", grid[row][col]));
			}
			sb.append("\n");
			sb.append("-----------------------------\n");
		}

		sb.append("  0   1   2   3   4   5   6\n");
		return sb.toString();
	}
}
