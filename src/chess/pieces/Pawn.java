package chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Pawn extends ChessPieces {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WIHTE) {
			p.setValues(position.getRow() - 1, position.getColunm());
			if (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
				mat[p.getRow()][p.getColunm()] = true;
			}
			p.setValues(position.getRow() - 2, position.getColunm());
			Position p2 = new Position(position.getRow() - 1, position.getColunm());
			if (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p) && getBoard().PositionExists(p2)
					&& !getBoard().ThereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColunm()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColunm() - 1);
			if (getBoard().PositionExists(p) && getBoard().ThereIsAPiece(p)) {
				mat[p.getRow()][p.getColunm()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColunm() + 1);
			if (getBoard().PositionExists(p) && getBoard().ThereIsAPiece(p)) {
				mat[p.getRow()][p.getColunm()] = true;
			}
		}
		else {
			p.setValues(position.getRow() + 1, position.getColunm());
			if (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
				mat[p.getRow()][p.getColunm()] = true;
			}
			p.setValues(position.getRow() + 2, position.getColunm());
			Position p2 = new Position(position.getRow() + 1, position.getColunm());
			if (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p) && getBoard().PositionExists(p2)
					&& !getBoard().ThereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColunm()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColunm() - 1);
			if (getBoard().PositionExists(p) && getBoard().ThereIsAPiece(p)) {
				mat[p.getRow()][p.getColunm()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColunm() - 1);
			if (getBoard().PositionExists(p) && getBoard().ThereIsAPiece(p)) {
				mat[p.getRow()][p.getColunm()] = true;
			}
			
		}

		return mat;
	}
	
	public String Tostring() {
		return "p";
	}

}
