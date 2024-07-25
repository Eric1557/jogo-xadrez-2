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

}
