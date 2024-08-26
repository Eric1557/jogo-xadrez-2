package chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Bishop extends ChessPieces {

	public Bishop(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String ToString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position p = new Position(0, 0);

		// nw
		p.setValues(position.getRow() - 1, position.getColunm() - 1);
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;
			p.setValues(p.getRow() -1 , p.getColunm()- 1);
		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}
		// ne
		p.setValues(position.getRow() - 1, position.getColunm() + 1);
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			p.setValues(p.getRow() - 1,p.getColunm() + 1);

		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}
		// se
		p.setValues(position.getRow() + 1, position.getColunm() + 1);
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			p.setValues(p.getRow() + 1,p.getColunm() + 1);

		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}
		// sw
		p.setValues(position.getRow() + 1, position.getColunm() - 1);
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;
			p.setValues(p.getRow() + 1, p.getColunm() - 1);
		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}

		return mat;
	}
}


