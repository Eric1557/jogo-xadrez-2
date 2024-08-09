package chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Rook extends ChessPieces {

	public Rook(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String ToString() {
		return "R";
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
		//below
		p.setValues(position.getRow() + 1, position.getColunm());
		while (getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().PositionExists(p) && isThereOponnentPiece(p)) {
			mat[p.getRow()][p.getColunm()] = true;

		}

		return mat;
	}
}
