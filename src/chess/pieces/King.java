package chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class King extends ChessPieces {

	public King(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String ToString() {
		return "K";
	}

	private boolean CanMove(Position position) {
		ChessPieces p = (ChessPieces) getBoard().pieces(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position p = new Position(0, 0);

		// above
		p.setValues(position.getRow() - 1, position.getColunm());
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColunm());
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColunm() - 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColunm() + 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		// nw
		p.setValues(position.getRow() - 1, position.getColunm() - 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		// ne
		p.setValues(position.getRow() - 1, position.getColunm() + 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		// sw
		p.setValues(position.getRow() + 1, position.getColunm() - 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		// se
		p.setValues(position.getRow() + 1, position.getColunm() + 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		return mat;
	}

}
