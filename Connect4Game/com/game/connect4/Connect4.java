package com.game.connect4;

import java.util.Scanner;

public class Connect4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Enter Player 1 details
		System.out.print("Enter name of Player 1: ");
		String name1 = scanner.nextLine();
		// Keep asking until valid symbol for Player 1
		char symbol1;
		while (true) {
			System.out.print("Enter symbol of Player 1 (X or O): ");
			String input = scanner.next(); // Read input as a string
			// Validate input length and convert to uppercase
			if (input.length() == 1) {
				symbol1 = Character.toUpperCase(input.charAt(0));
				if (symbol1 == 'X' || symbol1 == 'O') {
					break; // Valid symbol
				}
			}
			System.out.println("Invalid symbol. Please enter either 'X' or 'O'.");
		}

		// Ternary operation to automatically assign the other symbol to Player 2
		char symbol2 = (symbol1 == 'X') ? 'O' : 'X';
		scanner.nextLine(); // Consume the newline

		// Enter Player 2 details and ensure name is different from Player 1
		String name2;
		while (true) {
			System.out.print("Enter name of Player 2: ");
			name2 = scanner.nextLine();
			if (!name1.equals(name2)) {
				break; // Valid name
			} else {
				System.out.println("Player 2 name cannot be the same as Player 1. Please enter a different name.");
			}
		}
		System.out.println(name2 + ", you've been automatically assigned the symbol " + symbol2 + ".\n");

		// Initialize Grid and Players
		Grid grid = new Grid();
		Player player1 = new Player(name1, symbol1, grid);
		Player player2 = new Player(name2, symbol2, grid);

		Player currentPlayer = player1;
		boolean gameWon = false;

		// Main game loop
		while (!grid.isFull() && !gameWon) {
			System.out.println(grid);
			currentPlayer.takeTurn(scanner); // Pass the scanner to the takeTurn method
			// Check if the last disc dropped resulted in a win
			if (grid.isWinner(currentPlayer.getSymbol())) {
				gameWon = true;
				// Display the updated grid after the turn
				System.out.println(grid);
				System.out.println(currentPlayer.getName() + " wins!");
			} else {
				currentPlayer = (currentPlayer == player1) ? player2 : player1; // Switch player
			}
		}

		if (!gameWon) {
			System.out.println("It's a tie!");
		}

		scanner.close();
	}
}
