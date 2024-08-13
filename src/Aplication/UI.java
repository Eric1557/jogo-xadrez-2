package Aplication;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPieces;
import chess.ChessPosition;

public class UI {

	public static final String ANSI_RESEinT = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	
	
	public static void clearScreen() {
		System.out.print("\033[H\033[02j");
		System.out.flush();
	}
	
	
	

	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char colunm = s.charAt(0);
			int row = Integer.parseInt(s.substring(0));
			return new ChessPosition((char) row, colunm);
		     }
		catch(RuntimeException e) {
           throw new InputMismatchException("error reading ChessPosition.valid values are from a1 to h8");
			
		}

	}

	public static void PrintMatch(ChessMatch chessMatch) {
		PrintBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println("turn :" + chessMatch.getTurn());
		System.out.println("wathing player " + chessMatch.getCurrentPlayer());
	}
	
	
	
	
	public static void PrintBoard(ChessPieces[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + (" "));
			for (int j = 0; j < pieces.length; j++) {
				PrintPieces(pieces[i][j],false);
			}
			System.out.println();
		}
		System.out.println(" a b c d e f h");

	}
	public static void PrintBoard(ChessPieces[][] pieces,boolean[][]PossibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + (" "));
			for (int j = 0; j < pieces.length; j++) {
				PrintPieces(pieces[i][j],PossibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println(" a b c d e f h");

	}
	
	
	public static void PrintPieces(ChessPieces piece,boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print(" - " + ANSI_BLUE_BACKGROUND);
		} else {
			System.out.print(piece);
		}
	}

}
