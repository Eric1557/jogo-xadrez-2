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
				mat[i][j] = (ChessPieces) board.pieces(i, j);
			}
		}
		return mat;
	}

	private void PlaceNewPiece(char colunm, int row, ChessPieces piece) {
		board.PlacePiece(piece, new ChessPosition(colunm, (char) row).toPosition());
	}

	private void InitialSetup() {
		PlaceNewPiece('b', 6, new Rook(board, Color.WIHTE));
		PlaceNewPiece('e', 8, new King(board, Color.BLACK));
		PlaceNewPiece('e', 1, new King(board, Color.WIHTE));
	}

}
