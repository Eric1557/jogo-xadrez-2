package chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Queen extends ChessPieces {

	public Queen(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String ToString() {
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColunms()];

		Position p = new Position(0, 0);

		// above
		p.setValues(position.getRow() - 1, position.getColunm());
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}
		
		// left
		p.setValues(position.getRow(), position.getColunm() - 1);
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			p.setColunm(p.getColunm() - 1);

		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}
		// right
		p.setValues(position.getRow(), position.getColunm() + 1);
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			p.setColunm(p.getColunm() + 1);

		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}
		// below
		p.setValues(position.getRow() + 1, position.getColunm());
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}
		
		// nw
		p.setValues(position.getRow() - 1, position.getColunm() - 1);
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;
			p.setValues(p.getRow() - 1, p.getColunm() - 1);
		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}
		// ne
		p.setValues(position.getRow() - 1, position.getColunm() + 1);
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			p.setValues(p.getRow() - 1, p.getColunm() + 1);

		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}
		// se
		p.setValues(position.getRow() + 1, position.getColunm() + 1);
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			p.setValues(p.getRow() + 1, p.getColunm() + 1);

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
