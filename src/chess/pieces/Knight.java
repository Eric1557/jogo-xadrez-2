package chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Knight extends ChessPieces {
	public Knight(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String ToString() {
		return "N";
	}

	private boolean CanMove(Position position) {
		ChessPieces p = (ChessPieces) getBoard().pieces(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position p = new Position(0, 0);

		p.setValues(position.getRow() - 1, position.getColunm() - 2);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColunm() - 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColunm() + 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		p.setValues(position.getRow() - 1, position.getColunm() + 2);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColunm() + 2);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColunm() + 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColunm() - 1);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColunm() - 2);
		if (getBoard().PositionExists(p) && CanMove(p)) {
			mat[p.getRow()][p.getColunm()] = true;
		}

		return mat;
	}
}
