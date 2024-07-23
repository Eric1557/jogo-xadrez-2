package Aplication;

import chess.ChessMatch;

public class program {

	public static void main(String[] args) {

		ChessMatch chessMatch = new ChessMatch(null);
	    UI.PrintBoard(chessMatch.getPieces());
	}

}
