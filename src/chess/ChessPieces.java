package chess;

import BoardGame.Piece;
import BoardGame.Position;

public abstract class ChessPieces extends Piece {
	
	private Color color;

	public ChessPieces(BoardGame.Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	
	public String ToString() {
		return "R";
	}

	protected boolean isThereOponnentPiece(Position position) {
		ChessPieces p = (ChessPieces)getBoard().pieces(position);
		return p != null && p.getColor()!= color;
		
		
	}
	
	
	

}
