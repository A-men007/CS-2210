/**
 * @author AmanpreetG
 */
import java.util.LinkedList;
public class Evaluate {
		private int inline;
		private char [][] gameBoard;
		private int boardSize;
		private int max_levels; 

		// first two parameters specify the size of the gameboard, the third parameter is the number of
		//adjacent tiles needed to win the game, and the last parameter specifies the playing quality of the
		//program
	    public Evaluate(int boardRows, int boardColumns, int tilesNeeded, int maxLevels) {
	        this.gameBoard = new char[boardRows][boardColumns];
			this.boardSize = (boardRows*boardColumns);
			this.inline = tilesNeeded;
			// set up the board with empty values
			for (int row = 0; row < boardSize; row++) {
				for (int column = 0; column < boardSize; column++)
					this.gameBoard [row][column] = ' ';
			}
	    }

	    //creates dictionary class
	    public Dictionary createDictionary() {
	    	//return a dictionary obj
	        return new Dictionary(7993);
	    }
	    
	    //returns the Data object with given key
	    private String getDataString(){
	        String dataString = "";
	        for (int rows = 0; rows < this.gameBoard.length; rows++) {
	            for (int col = 0; col < this.gameBoard[rows].length; col++) {
	                dataString += this.gameBoard[rows][col];
	            }
	        }
	        return dataString.toString();
	    }

	    public Data repeatedConfig(Dictionary dict) {
	        return dict.get(this.getDataString());
	    }
	    
	    public void insertConfig(Dictionary dict, int score, int level) {
	    	// initialize the string to represent board
			String configString = "";
			// create a string of every character in the game board by going through it
			for (int x  = 0; x < boardSize; x++){
			    for (int i = 0; i < boardSize; i++)
			       configString += this.gameBoard[x][i]; 
			}
			// create a new record of the configuration and its associated score
			Data pair = new Data(configString, score, level);
			// add the record to the dictionary
			try {
				dict.put(pair);
			} catch (DuplicatedKeyException e) {
				//catch block
				e.printStackTrace();
			}
	    }

	    public void storePlay(int row, int col, char symbol) {

	        this.gameBoard[row][col] = symbol;

	    }


	    public boolean squareIsEmpty(int row, int col) {

	        return this.gameBoard[row][col] == 'g';

	    }


	    public boolean tileOfComputer(int row, int col) {

	        return this.gameBoard[row][col] == 'o';

	    }


	    public boolean tileOfHuman(int row, int col) {

	        return this.gameBoard[row][col] == 'b';

	    }


	    public boolean wins(char symbol) {
	    	// Initialize a variable to keep track of the adjacent characters
			int adjacent;
			// CHECK for Row Win********************************************
			// Initialize the win count to 0
			adjacent = 0;
			// for all of the rows
			for (int row = 0; row < boardSize; row ++) {
				// reset the count for every new row
				adjacent = 0;
				// check all the columns of every row
				for (int column = 0; column < boardSize; column ++) {
					// if the board entry is the symbol
					if (this.gameBoard[row][column] == symbol)
						// add the number of adjacentacent characters
						adjacent++;
					else
						// otherwise reset the win count to 0
						adjacent = 0;
					// if there is a row win, return true
					if (adjacent == this.inline)
						return true;
				}
			}
	        // CHECK for Column Win***********************************
			// Initialize the win count to 0
			adjacent = 0;
			// for all of the columns
			for (int column = 0; column < boardSize; column ++) {
				// reset the count for every new column
				adjacent = 0;
				// check all the rows  of every column
				for (int row = 0; row < boardSize; row ++) {
					// if the board entry is the symbol
					if (this.gameBoard[column][row] == symbol)
						// add the number of adjacent characters
						adjacent++;
					else
						// otherwise reset the win count to 0
						adjacent = 0;
					// if there is a row win, return true
					if (adjacent == this.inline)
						return true;
				}		
			}
			//CHECK Top to Bottom Diagonal Win*************************************
			// intialize to 0
			adjacent = 0;
			// for all of the rows
			for (int row = 0; row < boardSize; row++) {
				// check all the columns of every row
				for (int column = 0; column < boardSize; column++) {
					// reset the count for every new column
					adjacent = 0;
					// if the board entry is the symbol
					if (this.gameBoard[row][column] == symbol) {
						// add the number of adjacentacent characters
						adjacent++;
						int r = row+1, c = column+1;
						while (r < boardSize && c < boardSize) {
							if (this.gameBoard[r][c] == symbol) {
								// add the number of adjacentacent characters
								adjacent++;
								if (adjacent >= this.inline)
									return true;
							}
							else
								adjacent = 0;
							r++;
							c++;
						}
					}
				}
			}
			//CHECK Bottom to top Diagonal Win*********************************
			// intialize the win count to 0
			adjacent = 0;
			// for all of the rows
			for (int row = 0; row < boardSize; row++) {
				// check all the columns of every row
				for (int column = 0; column < boardSize; column++) {
					// reset the count for every new column
					adjacent = 0;
					// if the board entry is the symbol
					if (this.gameBoard[row][column] == symbol) {
						// add the number of adjacentacent characters
						adjacent++;
						// initialize the next diagonal position to the board position
						int r = row+1, c = column-1;
						while (r < boardSize && c > -1) {
							// if the board entry is symbol 
							if (this.gameBoard[r][c] == symbol) {
								// add the numb of adjacentacent chars
								adjacent++;
								// if board entry is the symbol
								if (adjacent >= this.inline)
									return true;
							}
							else
								// otherwise reset 
								adjacent = 0;
							// increment the diagonal row/column count by one
							r++;
							c--;
						}
					}
				}
			}
			// otherwise return false
			return false;

	    }
 
	    
	    
	    
	    public boolean isDraw() {

	        for (int i = 0; i < this.gameBoard.length; i++) {
	            for (int j = 0; j < this.gameBoard[i].length; j++) {
	                if (this.gameBoard[i][j] == 'g') {
	                    return true;
	                }
	            }
	        }
	        return false;

	    }


	    public int evalBoard() {

	        final char COMPUTER = 'o';
	        final char HUMAN = 'b';

	        if (wins(COMPUTER)) {// if the computer wins, return 3
	            return 3;
	        } else if (wins(HUMAN)) {// if the human wins, return 3
	            return 0;
	        } else if (isDraw()) {// if there's a draw, return 2
	            return 2;
	        } else {
	            return 1;// otherwise return 1
	        }

	    }

}


