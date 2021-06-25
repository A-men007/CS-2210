/* Author: Amanpreet Gill	   
 * File: a tic-tac-toe class that works with several other classes to create a game of (n,k)-tic-tac-toe
 * 		 where a human faces an AI. The game is designed so that you can pass different arg to change
 * 	     size of the gameboard and how many symbols in a row are required to win. Also, the 
 * 		 difficulty of the AI can be increased by increasing the number of levels of the game tree the AI 
 * 		 is able to traverse.
 * 
 */

import java.util.LinkedList; 

public class nk_TicTacToe 
{
	
	private int inline;
	private char [][] gameBoard;
	private int boardSize;
	private int max_levels; 

	public nk_TicTacToe (int boardSize, int inline, int max_levels)
	/*
	 * constructor
	 */
	{
		this.boardSize = boardSize;
		this.inline = inline;
		this.max_levels = max_levels;	
		gameBoard = new char [boardSize][boardSize];
		
		for (int x = 0; x < boardSize; x++) // sets the gameboard to  empty 
		{
			for (int i = 0; i < boardSize; i++)
			{
				gameBoard[x][i] = ' ';
			}
		}
	}
	
	public Dictionary createDictionary()
	/*
	 * returns a dictionary with a size of 7993
	 */
	{
		
		Dictionary gameTreeDict = new Dictionary(7993);
		return gameTreeDict; 
				
	}

	public int repeatedConfig(Dictionary configurations)
	/*
	 * represents gameboard as a string then checks if it is in the dictionary.
	 * if the gameboard is in dictionary return its score, 
	 * otherwise  return -1.
	 * param is a dictionary
	 */
	{
		
		int score;
		String boardconfig = boardConfig();
		
		try
		{
			
			score = (int) configurations.get(boardconfig);
			return score;
		
		}
		
		catch (Exception notInDict)
		{
			
			return -1;
		
		}
	}
	
	public void insertConfig(Dictionary configurations, int score) 
	/*
	 * parameters are dictionary and score
	 * represents gameboard as a string then inserts its configuration + score into the dictionary
	 * 
	 */
	
	{
		
		String boardconfig = boardConfig();
		Record newPair = new Record(boardconfig, score);
		
		try
		{
		
			configurations.insert(newPair);
		
		}
		
		catch (DictionaryException error)
		{
		
			System.out.println("Error: config is already present in dictionary");
		
		}
	
	}
	
	public void storePlay(int row, int col, char symbol)
	/*
	 * parameters are the row of the gameboard, column of the gameboard, and the symbol to be inserted.
	 * stores either X or O on the gameboard
	 * 
	 */
	{
	
		gameBoard[row][col] = symbol;
	
	}
	
	public boolean squareIsEmpty (int row, int col) //gucci
	/*
	 * parameters are the row and column of the gameboard that the function is looking at.
	 * returns true if the spot on the gameboard is blank, and false if there is an X or O.
	 * 
	 */
	{

		return (gameBoard[row][col] == ' ');
		
	}
	
	public boolean wins(char symbol)
	/*
	 * checks the gameboard to see if there are enough consecutive occurrences of a symbol to win.
	 * returns true if there are enough occurrences, and false otherwise.
	 * param is X, O, or " ".
	 */
	{		
		if (winRow(symbol))
		{		
			return true;		
		}		
		if (winCol(symbol))
		{		
			return true; 		
		}		
		if (winLeftDiagUp(symbol))
		{
			return true; 		
		}		
		if (winLeftDiagDn(symbol))
		{
			return true; 		
		}		
		if (winRightDiagUp(symbol))
		{		
			return true; 		
		}			
		if (winRightDiagDn(symbol))
		{
			return true; 		
		}
		return false;	
		
	}
	
	public boolean isDraw()
	/*
	 * returns true if the gameboard has no empty squares remaining and neither player has won
	 * returns false if the gameboard is not filled.
	 */
	{		
		int squaresFilled = 0;
		int count = 0;
		
		for(int x = 0; x < boardSize; x++)
		{		
			for (int z = 0; z < boardSize; z++)
			{				
				if((gameBoard[x][z] == 'X') || (gameBoard[x][z] == 'O'))
				{				
					squaresFilled++;
				}				
				count++;				
			}	
		}
		boolean bool = (count == squaresFilled);
		return bool;		
	}	
	
	public int evalBoard()
	/*
	 * evaluates the board and returns scores for different moves based on whether the human wins (0),
	 * the computer wins(3), if its a draw(2), or if the game is still undecided (1)
	 */
	{		
		if(this.wins('X') == true)
		{		
			return 0;		
		}		
		else if(this.wins('O') == true)
		{		
			return 3;			
		}		
		else if (this.isDraw() == true)
		{		
			return 2;		
		}		
		else
		{		
			return 1; 		
		}	
	}
	
	private String boardConfig()
	/*
	 * helper function that returns the current game board configuration. 
	 * Used in tandem with repeatedConfig and insertConfig.
	 */
	{			
		String boardconfig = "";
		int x = 0;
		int z = 0;
		
		while (x < boardSize)
		{			
			while (z < boardSize)
			{
				
				boardconfig += gameBoard[x][z];
				x++;
				z++;			
			}
		
		}		
		return boardconfig;
	
	}
	
	/*
	 * The following 6 helper functions are used in tandem with the 'wins' function to determine
	 * whether either player has won.
	 * these functions differ in which direction (vertical, horizontal, diagonal) they look at 
	 * when determining whether a player has enough occurrences of the same symbol to win
	 * params for all 6 of them is either X, O, or " ".
	 */
	
	private boolean winRow(char symbol)
	{		
		for (int x = 0; x < boardSize; x++)
		{			
			for (int z = 0; z < boardSize; z++)
			{				
				int count = 0;
				
				if (gameBoard[x][z] == symbol)
				{					
					count++;
					
					int rowCount = 0;					
					while ((count<inline) && ((x+rowCount)<boardSize-1))
					{					
						rowCount++;
						
						if (gameBoard[x+rowCount][z] == symbol)
						{
							
							count++;						
						}
					
						else
					
						{
							
							break;						
						}					
					}
					
					if (count == inline)
					{					
						return true;					
					}
				}
			}
		}	
		return false;
	}
	
	private boolean winCol(char symbol)
	{
		for (int x = 0; x < boardSize; x++)
		{			
			for (int z = 0; z < boardSize; z++)
			{
				
				int count = 0;				
				if (gameBoard[x][z] == symbol)
				{					
					count++;					
					int colCount = 0;					
					while ((count<inline) && ((z+colCount)<(boardSize-1)))
					{					
						colCount++;						
						if (gameBoard[x][z+colCount] == symbol)
						{							
							count++;						
						}					
						else
						{							
							count = 1;
							break;						
						}	
					}
					
					if (count == inline)
					{					
						return true;					
					}			
				}
			}
		}		
		return false;	
	}	
	
	private boolean winLeftDiagUp(char symbol)
	{
		for (int x = 0; x < boardSize; x++)
		{			
			for (int z = 0; z < boardSize; z++)
			{				
				int count = 0;
				
				if (gameBoard[x][z] == symbol)
				{
					count++;
					
					int rowCount = 0;
					int colCount = 0;
					
					while ((count<inline) && (((x-rowCount)>0) && ((z-colCount)>0)))
					{					
						rowCount++;
						colCount++;
						
						if (gameBoard[x-rowCount][z-rowCount] == symbol)
						{							
							count++;						
						}					
						else
						{							
							count = 1;
							break;						
						}
					}					
					if (count == inline)				
					{					
						return true;					
					}
				}
			}
		}	
		return false;	
	}	
	
	private boolean winLeftDiagDn(char symbol)
	{		
		for (int x = 0; x < boardSize; x++)
		{			
			for (int z = 0; z < boardSize; z++)
			{				
				int count = 0;
				
				if (gameBoard[x][z] == symbol)
				{
					count++;
					
					int rowCount = 0;
					int colCount = 0;
					
					while ((count<inline) && (((x+rowCount)<boardSize-1) && ((z+colCount)< boardSize-1)))
					{					
						rowCount++;
						colCount++;
						
						if (gameBoard[x+rowCount][z+rowCount] == symbol)
						{							
							count++;						
						}					
						else
						{							
							count = 1;
							break;						
						}
					}
					
					if (count == inline)
					{					
						return true;
					
					}
				}
			}
		}	
		return false;	
	}	


	
	private boolean winRightDiagUp(char symbol)
	{
		for (int x = 0; x < boardSize; x++)
		{			
			for (int z = 0; z < boardSize; z++)
			{				
				int count = 0;
				
				if (gameBoard[x][z] == symbol)
				{
					count++;
					
					int rowCount = 0;
					int colCount = 0;
					
					while ((count<inline) && ((x-rowCount)>0) && ((z+colCount)<boardSize-1))
					{					
						rowCount++;
						colCount++;
						
						if (gameBoard[x-rowCount][z+rowCount] == symbol)
						{							
							count++;
						
						}
					
						else
						{							
							count = 1;
							break;						
						}
					}					
					if (count == inline)
					
					{					
						return true;
					
					}
				}
			}
		}
		return false;
	}	

	
	private boolean winRightDiagDn(char symbol)
	{
		for (int x = 0; x < boardSize; x++)
		{
			
			for (int z = 0; z < boardSize; z++)
			{
				
				int count = 0;
				
				if (gameBoard[x][z] == symbol)
				{
					count++;
					
					int rowCount = 0;
					int colCount = 0;
					
					while ((count<inline) && ((x+rowCount)<boardSize-1) && ((z-colCount)> 0))
					{
					
						rowCount++;
						colCount++;
						
						if (gameBoard[x+rowCount][z-rowCount] == symbol)
						{							
							count++;						
						}					
						else
						{							
							count = 1;
							break;						
						}
					}					
					if (count == inline)
					{					
						return true;
					}
				}
			}
		}
		return false;	
	}	
}




