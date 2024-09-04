package chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessMatch;
import chess.ChessPieces;
import chess.Color;

public class Pawn extends ChessPieces {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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
		} else {
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
			// special move en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColunm() - 1);
				if (getBoard().PositionExists(left) && isThereOponnentPiece(left)
						&& getBoard().pieces(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColunm()] = true;
				}
			}

		}

		return mat;
	}

	public String Tostring() {
		return "p";
	}

}
