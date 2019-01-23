package corban.backend;

public class Board {

	String name;
	int[][] board;
	
	public Board(String name, int rows, int cols){
		this.name = name;
		this.board = new int[rows][cols];
	}
	
	public int getLocation(int row, int  col){
		return board[row][col];
	}
	
	public void shiftLeft(){
		for(int r = 0; r < board.length; r++){
			//TODO Make this more efficient
			for(int i = 0; i < board.length; i++){
				for(int c = 0; c < board[0].length - 1; c++){ // shift them to the left
					if(board[r][c] == 0){
						board[r][c] = board[r][c + 1];
						board[r][c + 1] = 0;
					}
				}
			}
			//Combines the same ones
			for(int c = 0; c < board[0].length - 1; c++){
				if(board[r][c] == board[r][c + 1]){
					board[r][c] = board[r][c] * 2;
					board[r][c + 1] = 0;
				}
			}
			
			for(int i = 0; i < board.length; i++){
				for(int c = 0; c < board[0].length - 1; c++){ // shift them to the left
					if(board[r][c] == 0){
						board[r][c] = board[r][c + 1];
						board[r][c + 1] = 0;
					}
				}
			}
		}
	}
	
	public void shiftRight(){
		for(int r = 0; r < board.length; r++){
			for(int i = 0; i < board.length; i++){
				for(int c = board[0].length - 1; c > 0; c--){
					if(board[r][c] == 0){
						board[r][c] = board[r][c - 1];
						board[r][c - 1] = 0;
					}
				}
			}
			
			for(int c = board[0].length - 1; c > 0; c--){
				if(board[r][c] == board[r][c - 1]){
					board[r][c] = board[r][c] * 2;
					board[r][c - 1] = 0;
				}
			}
			
			for(int i = 0; i < board.length; i++){
				for(int c = board[0].length - 1; c > 0; c--){
					if(board[r][c] == 0){
						board[r][c] = board[r][c - 1];
						board[r][c - 1] = 0;
					}
				}
			}
		}
	}
	
	public void shiftUp(){
		for(int c = 0; c < board[0].length; c++){
			for(int i = 0; i < board[0].length; i++){
				for(int r = 0; r < board.length - 1; r++){
					if(board[r][c] == 0){
						board[r][c] = board[r + 1][c];
						board[r + 1][c] = 0;
					}
				}
			}
			
			for(int r = 0; r < board.length - 1; r++){
				if(board[r][c] == board[r + 1][c]){
					board[r][c] = board[r][c] * 2;
					board[r + 1][c] = 0;
				}
			}
			
			for(int i = 0; i < board[0].length; i++){
				for(int r = 0; r < board.length - 1; r++){
					if(board[r][c] == 0){
						board[r][c] = board[r + 1][c];
						board[r + 1][c] = 0;
					}
				}
			}
		}
	}
	
	public void shiftDown(){
		for(int c = 0; c < board[0].length; c++){
			for(int i = 0; i < board[0].length; i++){
				for(int r = board.length - 1; r > 0; r--){
					if(board[r][c] == 0){
						board[r][c] = board[r - 1][c];
						board[r - 1][c] = 0;
					}
				}
			}
			
			for(int r = board.length - 1; r > 0; r--){
				if(board[r][c] == board[r - 1][c]){
					board[r][c] = board[r][c] * 2;
					board[r - 1][c] = 0;
				}
			}
			
			for(int i = 0; i < board[0].length; i++){
				for(int r = board.length - 1; r > 0; r--){
					if(board[r][c] == 0){
						board[r][c] = board[r - 1][c];
						board[r - 1][c] = 0;
					}
				}
			}
		}
	}
	
	public void setSpot(int spot, int row, int col){
		board[row][col] = spot;
	}
	
	public int rowLength(){
		return board.length;
	}
	
	public int colLength(){
		return board[0].length;
	}
	
	/**
	 * @param b
	 * @return true if the board spots are the same
	 */
	public boolean boardSpotCheck(Board b){
		if(b.rowLength() != this.board.length) return false;
		if(b.colLength() != this.board[0].length) return false;
		
		for(int r = 0; r < this.board.length; r++){
			for(int c = 0; c < this.board[0].length; c++){
				if(b.getLocation(r, c) != this.board[r][c]) return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Give it a board, and it will set the spots on the board
	 * @param setSpots the board to set the spots
	 * @return returns the board
	 */
	public Board boardSpotSet(Board setSpots){
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[0].length; c++){
				setSpots.setSpot(board[r][c], r, c);
			}
		}

		return setSpots;
	}
	
	/**
	 * Returns the highest value in the board
	 * @return the highest value in the board
	 */
	public int getHighestSpot(){
		int highest = 0;
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[0].length; c++){
				if(board[r][c] > highest){
					highest = board[r][c];
				}
			}
		}
		return highest;
	}
	
	/**
	 * Returns the number of slots
	 * @param num the number to test if it is in the grid
	 * @return the number of slots with the given number
	 */
	public int getNumSlotsWithNum(int num){
		int numberOfSlots = 0;
		
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[0].length; c++){
				if(board[r][c] == num) numberOfSlots++;
			}
		}
		
		return numberOfSlots;
	}
	
	/**
	 * Checks if there are any more moves in the board and returns true if there are.
	 * @return true if there are more moves
	 */
	public boolean areMoreMoves(){
		Board stub = new Board("Stub", board.length, board[0].length);
		stub = boardSpotSet(stub);
		
		stub.shiftLeft();		
		if(!stub.boardSpotCheck(this)) return true;
		stub.shiftUp();
		if(!stub.boardSpotCheck(this)) return true;
		stub.shiftRight();
		if(!stub.boardSpotCheck(this)) return true;
		stub.shiftDown();
		if(!stub.boardSpotCheck(this)) return true;
		return false;
	}
}
