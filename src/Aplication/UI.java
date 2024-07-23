package Aplication;

import chess.ChessPieces;

public class UI {

	public static void PrintBoard(ChessPieces[][]pieces) {
		for(int i = 0;i < pieces.length;i++) {
			System.out.print((8 - i) + (" "));
			for(int j = 0 ; j < pieces.length;j++) {
				PrintPieces(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.println(" a b c d e f h");
		
	}
	public static void PrintPieces(ChessPieces piece) {
		if (piece == null) {
			System.out.print(" - ");
		}
		else {
			System.out.print(piece);
		}
	}
	
	
}
