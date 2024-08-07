package chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		
	}
	
	public ChessMatch(Board board) {
		board = new Board(8, 8);
		InitialSetup();
	}

	public ChessPieces[][] getPieces() {
		ChessPieces[][] mat = new ChessPieces[board.getRows()][board.getColunms()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColunms(); j++) {
				mat[i][j] = (ChessPieces) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public ChessPieces performChessMove(ChessPosition sourcePosition,ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		ValidateSourcePosition(source);
		Piece capturedPiece = makeMove(source ,target);
		return (ChessPieces) capturedPiece;
		
	}
	
	public ChessPieces makeMove(Position source ,Position target) {
		Piece p = board.RemovePiece(source);
		ChessPieces capturedPiece = (ChessPieces) board.RemovePiece(target);
		board.PlacePiece(p, target);
		return capturedPiece;
	}
	
	private void ValidateSourcePosition(Position position) {
		if(!board.ThereIsAPiece(position)) {
			throw new ChessExceptions("there is no piece on source position");
			
		}
	}
	
	

	private void PlaceNewPiece(char colunm, int row, ChessPieces piece) {
		board.PlacePiece(piece, new ChessPosition(colunm, (char) row).toPosition());
	}

	private void InitialSetup() {

		PlaceNewPiece('c', 1, new Rook(board, Color.WIHTE));
		PlaceNewPiece('c', 2, new Rook(board, Color.WIHTE));
		PlaceNewPiece('d', 2, new Rook(board, Color.WIHTE));
		PlaceNewPiece('e', 2, new Rook(board, Color.WIHTE));
		PlaceNewPiece('e', 1, new Rook(board, Color.WIHTE));
		PlaceNewPiece('d', 1, new King(board, Color.WIHTE));

		PlaceNewPiece('c', 7, new Rook(board, Color.BLACK));
		PlaceNewPiece('c', 8, new Rook(board, Color.BLACK));
		PlaceNewPiece('d', 7, new Rook(board, Color.BLACK));
		PlaceNewPiece('e', 7, new Rook(board, Color.BLACK));
		PlaceNewPiece('e', 8, new Rook(board, Color.BLACK));
		PlaceNewPiece('d', 8, new King(board, Color.BLACK));

	}

}
