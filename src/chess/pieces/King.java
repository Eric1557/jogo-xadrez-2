package chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessMatch;
import chess.ChessPieces;
import chess.Color;

public class King extends ChessPieces {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String ToString() {
		return "K";
	}

	private boolean CanMove(Position position) {
		ChessPieces p = (ChessPieces) getBoard().pieces(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		ChessPieces p = (ChessPieces) getBoard().pieces(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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

		// specialmove castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			//specialmove castling Kingside rook
			Position posT1 = new Position(position.getRow(), position.getColunm() + 3);
			if (testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColunm() + 1);
				Position p2 = new Position(position.getRow(), position.getColunm() + 2);
				if (getBoard().pieces(p1) == null && getBoard().pieces(p2) == null) {
                    mat[position.getRow()][position.getColunm() + 2]= true;
				}
			}
		}
		//specialmove castling Queenside rook
		Position posT2 = new Position(position.getRow(), position.getColunm() - 4);
		if (testRookCastling(posT2)) {
			Position p1 = new Position(position.getRow(), position.getColunm() - 1);
			Position p2 = new Position(position.getRow(), position.getColunm() - 2);
			Position p3 = new Position(position.getRow(), position.getColunm() - 2);
			if (getBoard().pieces(p1) == null && getBoard().pieces(p2) == null && getBoard().pieces(p3) == null){
                mat[position.getRow()][position.getColunm() - 2]= true;
			}
		}

		return mat;
	}

}
