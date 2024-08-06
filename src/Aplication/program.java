package Aplication;

import java.util.Scanner;

import BoardGame.Piece;
import chess.ChessMatch;
import chess.ChessPosition;

public class program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		ChessMatch chessMatch = new ChessMatch(null);

		while (true) {
			UI.PrintBoard(chessMatch.getPieces());
            System.out.println();
            System.out.println("source:");
            ChessPosition source = UI.readChessPosition(sc);
          
            System.out.println();
            System.out.println("target:");
            ChessPosition target = UI.readChessPosition(sc);
            
           ChessPieces capturedPiece = chessMatch.performChessMove(source, target);
		}

	}
}
