package chess;

import BoardGame.Board;
import BoardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch(Board board) {
		board = new Board(8, 8);
		InitialSetup();
	}

	public ChessPieces[][] getPieces() {
		ChessPieces[][] mat = new ChessPieces[board.getRows()][board.getColunms()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColunms(); j++) {
                 mat[i][j]= (ChessPieces)board.pieces(i, j);
			}
		}
        return mat;
	}
	private void  InitialSetup() {
		board.PlacePiece(new Rook(board,Color.WIHTE),new Position(2,1));
		board.PlacePiece(new King(board,Color.WIHTE),new Position(5,1));
       //exemplo de posiçao peça vai modificar
	}

}
