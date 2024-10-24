package com.game.connect4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
	private String name;
	private char symbol;
	private Grid grid; // Reference to the grid where the player plays

	public Player(String name, char symbol, Grid grid) {
		this.name = name;
		this.symbol = symbol;
		this.grid = grid;
	}

	// Method to take a turn
	public void takeTurn(Scanner scanner) {
		boolean validPlay = false;
		int column = -1;

		while (!validPlay) {
			System.out.print(name + " (" + symbol + "), choose a column: ");
			try {
				column = scanner.nextInt();
				if (grid.isColumnValid(column)) {
					validPlay = true;
				} else {
					System.out.println("Invalid move, please try again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number.");
				scanner.next(); // Clear the invalid input
			}
		}
		grid.dropDisc(new Disc(symbol), column);
	}

	public char getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}
}
