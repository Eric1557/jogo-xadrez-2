package chess.pieces;

import BoardGame.Board;
import chess.ChessPieces;
import chess.Color;

public class Rook extends ChessPieces {

	public Rook(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String ToString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];
		return mat;

	}

}
