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
		// Initial Game State
		System.out.println("Game: Not started\n");

		// Initialize Grid and Players
		Grid grid = new Grid();
		Player player1 = new Player(name1, symbol1, grid);
		Player player2 = new Player(name2, symbol2, grid);

		Player currentPlayer = player1;
		boolean gameWon = false;
		boolean hasPromptedUndo = false; // Flag to ensure undo prompt only once per turn

		// Main game loop
		while (!grid.isFull() && !gameWon) {
			System.out.println(grid);
			currentPlayer.takeTurn(scanner); // Player makes a move
			System.out.println(grid);

			// Offer undo only once per turn
			if (!hasPromptedUndo && grid.offerUndo(scanner)) {
				System.out.println("\nLast move undone.");
				hasPromptedUndo = true; // Set flag to prevent re-prompting in the same turn
				continue; // Skip switching players to let same player take turn again
			}

			hasPromptedUndo = false; // Reset for next player's turn

			// Check if the last disc dropped resulted in a win
			if (grid.isWinner(currentPlayer.getSymbol())) {
				gameWon = true;
				System.out.println(grid);
				System.out.println("\nGame: Completed");
				System.out.println("\n"+currentPlayer.getName() + " wins!");
			} else {

				currentPlayer = (currentPlayer == player1) ? player2 : player1; // Switch player
				System.out.println("\nGame: In Progress");
			}
		}

		if (!gameWon) {
			System.out.println("\nGame: Completed");
			System.out.println("\nIt's a tie!");
		}
		scanner.close();
	}
}


/*Explanation of undo logic:
- `hasPromptedUndo`: A boolean flag used to ensure the player is only prompted once per turn to undo their move.

- `grid.offerUndo(scanner)`: Calls the `offerUndo` method from the Grid class,which prompts the player to undo their last move. 
If the player chooses to undo,the last move is reverted.

- If the player undoes the move, `continue` allows the current player to retry their turn without switching to the next player. 
This makes it clear that the same player should act again after an undo.

- The flag `hasPromptedUndo` is reset to `false` after the player's turn is finalized, 
allowing the next player to be prompted for an undo on their own turn.
*/
