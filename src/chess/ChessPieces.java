package chess;

import BoardGame.Piece;
import BoardGame.Position;

public abstract class ChessPieces extends Piece {
	
	private Color color;
	private int moveCount;

	public ChessPieces(BoardGame.Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}

	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.FromPosition(position);
		
	}
	
	
	
	
	public String ToString() {
		return "R";
	}

	protected boolean isThereOponnentPiece(Position position) {
		ChessPieces p = (ChessPieces)getBoard().pieces(position);
		return p != null && p.getColor()!= color;
		
		
	}

	
	
	

}
