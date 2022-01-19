package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static ArrayList<Integer> playerPos = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPos = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		char [][] board =  {{' ','|',' ', '|', ' '},
							{'-','+','-', '+', '-'},
							{' ','|',' ', '|', ' '},
							{'-','+','-', '+', '-'},
							{' ','|',' ', '|', ' '}};
		
		printBoardGame(board);
		
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter a number from 1-9.");
			int position = scan.nextInt();
			while(playerPos.contains(position) || cpuPos.contains(position)) {
				System.out.println("position taken! Enter new");
				position = scan.nextInt();
			}
			
			placeGameBoard(board, position, "player");
			printBoardGame(board);
			String results = checkWinner();
			if(results.length() > 0 ) {
				System.out.println(results);
				break;
			}
			
			
			Random rand = new Random();
			int cpuPosition = rand.nextInt(9) + 1;
			while(playerPos.contains(cpuPosition) || cpuPos.contains(cpuPosition)) {
				cpuPosition = rand.nextInt(9) + 1;
			}
			
			placeGameBoard(board, cpuPosition, "cpu");
			
			printBoardGame(board);
			
			results = checkWinner();
			if(results.length() > 0 ) {
				System.out.println(results);
				break;
			}
			

		}
		
	}

	public static void printBoardGame(char [][] gameboard) {
		for (char [] row : gameboard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placeGameBoard(char [][] board, int position, String user) {
		
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPos.add(position);
		} else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPos.add(position);
		}
		
		switch(position) {
		
		case 1: board[0][0] = symbol;
		break;
		case 2: board[0][2] = symbol;
		break;
		case 3: board[0][4] = symbol;
		break;
		case 4: board[2][0] = symbol;
		break;
		case 5: board[2][2] = symbol;
		break;
		case 6: board[2][4] = symbol;
		break;
		case 7: board[4][0] = symbol;
		break;
		case 8: board[4][2] = symbol;
		break;
		case 9: board[4][4] = symbol;
		default:
		break;
		
		}
	}
	
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftColumn = Arrays.asList(1,4,7);
		List midColumn = Arrays.asList(2,5,8);
		List rightColumn = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(7,5,3);
	
		List<List> winner = new ArrayList<List>();
		winner.add(topRow);
		winner.add(midRow);
		winner.add(botRow);
		winner.add(leftColumn);
		winner.add(midColumn);
		winner.add(rightColumn);
		winner.add(cross1);
		winner.add(cross2);

		for(List l : winner) {
			if(playerPos.containsAll(l)) {
				return "Congratulations you won!";
			} else if(cpuPos.containsAll(l)) {
				return "CPU wins! Sorry!";
			} else if(playerPos.size() + cpuPos.size() == 9) {
				return "Tie!";
			}
		}
		return "";
	}
}
