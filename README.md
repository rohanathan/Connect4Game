Let's walk through the game flow and the roles of the classes involved:

### 1. **Connect4 Class (Main Class)**

- **Purpose**: The main class where the game starts and controls the flow of the game.
- **Key Elements**:
    - Prompts players to input their names and symbols.
    - It ensures that player symbols are either 'X' or 'O' and names are distinct.
    - Alternates turns between two players until the game is won or the grid is full.
    - Checks if a player has won using `grid.isWinner()`.
    - Declares the winner or a tie.
    - Undo Functionality (Enhancement): After each move, players have the option to undo their last move, the prompt to undo appears only once per turn. If they opt to undo, the same player gets to take the turn again.


### 2. **Grid Class**

- **Purpose**: Manages the game board (grid) where players drop their discs.
- **Key Elements**:
    - Initializes a 6x7 grid with empty spaces.
    - Provides a method to drop a disc into a valid column.
    - Checks for a valid column, win conditions, and whether the grid is full.
    - `isColumnValid()` to verify valid columns before dropping discs.
- **Game Mechanics**: The grid checks four possible winning conditions: horizontal, vertical, and two diagonal alignments of four same-symbol discs.

### 3. **Disc Class**

- **Purpose**: Represents the discs players drop on the grid.
- **Key Elements**:
    - Holds the player's symbol and the disc's position (row and column) in the grid.
    - It simply tracks the symbol and position.

### 4. **Player Class**

- **Purpose**: Manages player actions during the game.
- **Key Elements**:
    - Players take turns selecting a column.
    - Ensures the column chosen is valid before dropping the disc.
    - Scanner is passed as a parameter, and error handling was added for invalid inputs (non-integer entries).

### 5. Game Flow

1. **Player Setup**: Players input names and symbols. Validation ensures that Player 1 and Player 2 have distinct names, and symbols are either 'X' or 'O'.
2. **Grid Initialization**: The game grid is created, and both players are assigned to it.
3. **Turn-taking**: The players take turns selecting a column where they want to drop their discs.
4. **Win Check**: After each move, the grid checks if the player has won by forming a line of 4 identical symbols either horizontally, vertically, or diagonally.
5. **Game End**: If a player wins, the game ends. If the grid is full and no winner is found, the game results in a tie.
