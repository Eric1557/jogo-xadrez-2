package chess.pieces;

import BoardGame.Board;
import chess.ChessPieces;
import chess.Color;

public class King extends ChessPieces{

	public King(Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String ToString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];
		
		return mat;
	}

}
