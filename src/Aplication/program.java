package Aplication;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import BoardGame.Piece;
import chess.ChessExceptions;
import chess.ChessMatch;
import chess.ChessPieces;
import chess.ChessPosition;

public class program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ChessMatch chessMatch = new ChessMatch();
		List<ChessPieces> captured = new ArrayList<>();

		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.PrintMatch(chessMatch,captured);
				System.out.println();
				System.out.println("source:");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.PossibleMoves(source);
				UI.clearScreen();
				UI.PrintBoard(chessMatch.getPieces(),possibleMoves);
				System.out.println();
				System.out.println("target:");
				ChessPosition target = UI.readChessPosition(sc);
	
				Piece capturedPiece = chessMatch.performChessMove(source, target);
				if(capturedPiece == null) {
					captured.add((ChessPieces) capturedPiece);
					
				}
				if(chessMatch.getPromoted() == null) {
					System.out.println("enter piece for promotion (B/N/R/Q):");
					String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
				}
			}
            catch(ChessExceptions e) {
            	System.out.println(e.getMessage());
            	sc.nextLine();
            	
            }
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
            	sc.nextLine();
			}
			catch (NullPointerException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.PrintMatch(chessMatch,captured);

	}
}
