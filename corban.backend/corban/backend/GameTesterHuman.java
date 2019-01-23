package corban.backend;

import java.util.Scanner;


public class GameTesterHuman {
	
	int boardRowSize = 6;
	int boardColSize = 8;
	int numSpots = boardRowSize * boardColSize;
	
	Board board = new Board("Board", boardRowSize, boardColSize);
	Board boardCheck = new Board("BoardChecker", boardRowSize, boardColSize);
	Scanner scanner = new Scanner(System.in);
	
	boolean playing = true;
	
	public GameTesterHuman(){
		long startTime = System.currentTimeMillis();
		long millis = 0;
		long hour = 0;
		long minutes = 0;
		long seconds = 0;
		long lastPrintStamp = 0;
		
		int numAtStart = (int) (Math.sqrt(boardRowSize * boardColSize));
		for(int i = 0; i < numAtStart; i++){
			addNumber();
		}
		
		int rotation = 1;
		int tries = 0;
		System.out.println("Tries           Hghest          Usd/Tot      Percent    ElpsedTime");
		while(board.areMoreMoves()){
			if(rotation == 1){
				board.shiftDown();
			}else if(rotation == 2){
				board.shiftLeft();
			}else if(rotation == 3){
				board.shiftUp();
			}else if(rotation == 4){
				board.shiftRight();
				rotation = 0;
			}
			rotation++;
			
			if(board.getNumSlotsWithNum(0) - numAtStart > 5){
				for(int i = 0; i < numAtStart; i++){
					addNumber();
				}
			}else{
				addNumber();
			}
			
			
			//printBoard();
			tries++;
			
			millis = System.currentTimeMillis() - startTime;
			seconds = (millis / 1000) % 60;
			
			if((millis % 1000) == 0 && lastPrintStamp != millis){ //TODO Fix this
				System.out.print(String.format("%,-14d  %,-14d  %,3d/%,-3d      %5.3f", tries, board.getHighestSpot(),
						numSpots - board.getNumSlotsWithNum(0), numSpots, 
						((boardRowSize * boardColSize - board.getNumSlotsWithNum(0)) * 100.0 / (double) (boardRowSize * boardColSize * 1.0))) + "%");
				
				
				minutes = (millis / (1000 * 60)) % 60;
				hour = (millis / (1000 * 60 * 60)) % 24;
				System.out.println(String.format("    %02d:%02d:%02d.%01d", hour, minutes, seconds, millis % 1000));
				lastPrintStamp = millis;
			}
		}
		
		millis = System.currentTimeMillis() - startTime;
		seconds = (millis / 1000) % 60;
		minutes = (millis / (1000 * 60)) % 60;
		hour = (millis / (1000 * 60 * 60)) % 24;
		
		System.out.printf("Number of Iterations: %,d\n", tries);
		System.out.printf("Highest Block: %,d\n", board.getHighestSpot());
		System.out.println("Percent of Board Filled: " + ((boardRowSize * boardColSize - board.getNumSlotsWithNum(0)) * 100.0 / (double) (boardRowSize * boardColSize * 1.0)) + "%");
		System.out.println(String.format("Time Taken: %02d:%02d:%02d.%03d", hour, minutes, seconds, millis % 1000));
		
		while(playing){
			if(!board.boardSpotCheck(boardCheck)) addNumber();
			boardCheck = board.boardSpotSet(boardCheck);
			printBoard();
			String input = scanner.next();
			if(input.equals("a")){
				board.shiftLeft();
			}else if(input.equals("d")){
				board.shiftRight();
			}else if(input.equals("w")){
				board.shiftUp();
			}else if(input.equals("s")){
				board.shiftDown();
			}
			if(board.getNumSlotsWithNum(0) == 0 && !board.areMoreMoves()) break;
		}
		System.out.println("Game Over!");
		System.out.println("Highest Block: " + board.getHighestSpot());
	}
	
	private void printBoard(){
		System.out.print("+");
		for(int i = 0; i < board.colLength(); i++){ System.out.print("---------+"); } System.out.println();
		
		for(int r = 0; r < board.rowLength(); r++){
			for(int c = 0; c < board.colLength(); c++){
				if(board.getLocation(r, c) != 0){
					System.out.print(String.format("|%9d", board.getLocation(r, c)));
				}else{
					System.out.print("|         ");
				}
			}
			System.out.println("|");
			System.out.print("+");
			for(int i = 0; i < board.colLength(); i++){ System.out.print("---------+"); } System.out.println();
		}
	}
	
	private void addNumber(){
		boolean foundSpot = false;
		int randRow;
		int randCol;
		do{
			randRow = (int) (Math.random() * (board.rowLength()));
			randCol = (int) (Math.random() * (board.colLength()));
			
			//System.out.println("Trying R:" + randRow + " C:" + randCol + " Found: " + board.getLocation(randRow, randCol));
			
			if(board.getLocation(randRow, randCol) == 0){
				foundSpot = true;
			}
			if(board.getNumSlotsWithNum(0) == 0) break;
		}while(!foundSpot);
		
		if(board.getNumSlotsWithNum(0) == 0){
			//System.out.println("No Empty Spots.");
		}else{
			if(Math.random() < .95){
				board.setSpot(2, randRow, randCol);
			}else{
				board.setSpot(4, randRow, randCol);
			}
		}
	}	
}
