package chess.pieces;

import BoardGame.Board;
import chess.ChessPieces;
import chess.Color;

public class Rook extends ChessPieces{

	public Rook(Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String ToString() {
		return "R";
	}
	
	
	

}
