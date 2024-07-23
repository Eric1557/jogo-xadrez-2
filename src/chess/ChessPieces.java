package chess;

import BoardGame.Piece;

public class ChessPieces extends Piece {
	
	private Color color;

	public ChessPieces(BoardGame.Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

		
	
	
	

}
