package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {

	private int Turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;

	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {

	}

	public ChessMatch(Board board) {
		board = new Board(8, 8);
		Turn = 1;
		currentPlayer = Color.WIHTE;
		InitialSetup();
	}

	public int getTurn() {
		return Turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
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

	public boolean[][] PossibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		ValidateSourcePosition(position);
		return board.pieces(position).possibleMoves();
	}

	public ChessPieces performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		ValidateSourcePosition(source);
		ValidateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessExceptions("you cant put yourself in check ");

		}
		check = (testCheck(Opponnent(currentPlayer))) ? true : false;

		if (testCheckMate(Opponnent(currentPlayer))) {
			checkMate = true;

		} else {
			NextTurn();

		}
		return (ChessPieces) capturedPiece;
	}

	public ChessPieces makeMove(Position source, Position target) {
		ChessPieces p = (ChessPieces) board.RemovePiece(source);
		p.getMoveCount();
		ChessPieces capturedPiece = (ChessPieces) board.RemovePiece(target);
		board.PlacePiece(p, target);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPieces p = (ChessPieces) board.RemovePiece(target);
		p.decreaseMoveCount();
		board.PlacePiece(p, source);

		if (capturedPiece != null) {
			board.PlacePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	private void ValidateSourcePosition(Position position) {
		if (!board.ThereIsAPiece(position)) {
			throw new ChessExceptions("there is no piece on source position");

		}
		if (currentPlayer != ((ChessPieces) board.pieces(position)).getColor()) {
			throw new ChessExceptions("the chosen piece is not yours ");

		}
		if (!board.pieces(position).IsThereAnyPossibleMove()) {
			throw new ChessExceptions("there is no possible moves for the chosen piece");
		}
	}

	private void ValidateTargetPosition(Position source, Position target) {
		if (!board.pieces(source).possibleMoves(target)) {
			throw new ChessExceptions("the chosen piece cant't move to target");
		}

	}

	private void NextTurn() {
		Turn++;
		currentPlayer = (currentPlayer == Color.WIHTE) ? Color.BLACK : Color.WIHTE;
	}

	private Color Opponnent(Color color) {
		return (color == Color.WIHTE) ? Color.BLACK : Color.WIHTE;
	}

	private ChessPieces King(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPieces) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPieces) p;
			}
		}
		throw new IllegalStateException("there is " + color + "king on the board");
	}

	private boolean testCheck(Color color) {
		Position kingPosition = King(color).getChessPosition().toPosition();
		List<Piece> Opponnent = piecesOnTheBoard.stream().filter(x -> ((ChessPieces) x).getColor() == Opponnent(color))
				.collect(Collectors.toList());
		for (Piece p : Opponnent) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColunm()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPieces) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColunms(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPieces) p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}

		return true;

	}

	private void PlaceNewPiece(char colunm, int row, ChessPieces piece) {
		board.PlacePiece(piece, new ChessPosition(colunm, (char) row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	private void InitialSetup() {

		PlaceNewPiece('a', 1, new Rook(board, Color.WIHTE));
		PlaceNewPiece('b', 1, new Knight(board, Color.WIHTE));
		PlaceNewPiece('c', 1, new Bishop(board, Color.WIHTE));
		PlaceNewPiece('e', 1, new King(board, Color.WIHTE));
		PlaceNewPiece('f', 1, new Bishop(board, Color.WIHTE));
		PlaceNewPiece('g', 1, new Knight(board, Color.WIHTE));
        PlaceNewPiece('h', 1, new Rook(board, Color.WIHTE));
		PlaceNewPiece('a', 2, new Pawn(board, Color.WIHTE));
		PlaceNewPiece('b', 2, new Pawn(board, Color.WIHTE));
		PlaceNewPiece('c', 2, new Pawn(board, Color.WIHTE));
		PlaceNewPiece('d', 2, new Pawn(board, Color.WIHTE));
		PlaceNewPiece('e', 2, new Pawn(board, Color.WIHTE));
		PlaceNewPiece('f', 2, new Pawn(board, Color.WIHTE));
		PlaceNewPiece('g', 2, new Pawn(board, Color.WIHTE));
		PlaceNewPiece('h', 2, new Pawn(board, Color.WIHTE));

		PlaceNewPiece('a', 8, new Rook(board, Color.BLACK));
		PlaceNewPiece('b', 8, new Knight(board, Color.BLACK));
        PlaceNewPiece('c', 8, new Bishop(board, Color.BLACK));
		PlaceNewPiece('e', 8, new King(board, Color.BLACK));
		PlaceNewPiece('c', 8, new Bishop(board, Color.BLACK));
        PlaceNewPiece('f', 8, new Rook(board, Color.BLACK));
		PlaceNewPiece('g', 8, new Knight(board, Color.BLACK));
        PlaceNewPiece('b', 7, new Pawn(board, Color.BLACK));
		PlaceNewPiece('c', 7, new Pawn(board, Color.BLACK));
		PlaceNewPiece('d', 7, new Pawn(board, Color.BLACK));
		PlaceNewPiece('e', 7, new Pawn(board, Color.BLACK));
		PlaceNewPiece('f', 7, new Pawn(board, Color.BLACK));
		PlaceNewPiece('g', 7, new Pawn(board, Color.BLACK));
		PlaceNewPiece('h', 7, new Pawn(board, Color.BLACK));
	}

}
