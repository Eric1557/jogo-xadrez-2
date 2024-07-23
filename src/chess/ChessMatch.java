package chess;

import BoardGame.Board;

public class ChessMatch {

	private Board board;

	public ChessMatch(Board board) {
		board = new Board(8, 8);
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

}
