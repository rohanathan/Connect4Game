package com.game.connect4; 
import java.util.Scanner;
import java.util.Stack;

public class Grid {
    private char[][] grid;
    private Stack<int[]> moves; // Stack to keep track of row and column of last moves
    private boolean canUndo; // Flag to track if undo is available

    public Grid() {
        grid = new char[6][7];
        moves = new Stack<>();
        canUndo = false; // Initially, no undo is available
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                grid[row][col] = ' ';
            }
        }
    }

    public boolean dropDisc(Disc disc, int column) {
        for (int row = grid.length - 1; row >= 0; row--) {
            if (grid[row][column] == ' ') {
                grid[row][column] = disc.getSymbol();
                disc.setPosition(row, column);
                moves.push(new int[]{row, column}); // Track this move
                canUndo = true; // Set undo available after a valid move
                return true;
            }
        }
        return false; // Column is full
    }

    public boolean undoLastMove() {
        if (moves.isEmpty()) {
            return false; // No move to undo
        }
        int[] lastMove = moves.pop();
        grid[lastMove[0]][lastMove[1]] = ' '; // Undo the move
        canUndo = false; // Reset the undo option after undoing
        return true;
    }

    public boolean offerUndo(Scanner scanner) {
        if (canUndo) { // Only offer if there was a valid move
            String undoInput;
            while (true) {
                System.out.print("Do you want to undo the last move? (y/n): ");
                undoInput = scanner.next().toLowerCase(); // Read and convert to lowercase

                // Check if input is valid
                if (undoInput.equals("y")) {
                    undoLastMove(); // Undo if user agrees
                    return true; // Undo happened
                } else if (undoInput.equals("n")) {
                    return false; // User chose not to undo
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'."); // Prompt for valid input
                }
            }
        }
        return false; // No undo action if there are no valid moves
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
