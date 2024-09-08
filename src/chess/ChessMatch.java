package chess;

import java.security.InvalidParameterException;
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
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {

	private int Turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	private ChessPieces enPassantVulnerable;
	private ChessPieces promoted;

	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
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

	public ChessPieces getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

	public ChessPieces getPromoted() {
		return promoted;
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

		ChessPieces movedPiece = (ChessPieces) board.pieces(target);

		// specialmove promotion
		promoted = null;
		if (movedPiece instanceof Pawn) {
			if (movedPiece.getColor() == Color.WIHTE && target.getRow() == 0
					|| movedPiece.getColor() == Color.BLACK && target.getRow() == 7) {
				promoted = (ChessPieces) board.pieces(target);
				promoted = replacePromotedPiece("Q");
			}
		}

		check = (testCheck(Opponnent(currentPlayer))) ? true : false;

		if (testCheckMate(Opponnent(currentPlayer))) {
			checkMate = true;

		} else {
			NextTurn();

		}
		// special moved piece en passant
		if (movedPiece instanceof Pawn
				&& (target.getRow() - 2 == source.getRow() || target.getRow() + 2 == source.getRow())) {
			enPassantVulnerable = movedPiece;
		} else {
			enPassantVulnerable = null;
		}

		return (ChessPieces) capturedPiece;
	}

	public ChessPieces replacePromotedPiece(String type) {
		if (promoted == null) {
			throw new IllegalStateException("there is no piece to be promoted");
		}
		if (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
			throw new InvalidParameterException("invalid type for promotion");
		}

		Position pos = promoted.getChessPosition().toPosition();
		Piece p = board.RemovePiece(pos);
		piecesOnTheBoard.remove(p);

		ChessPieces newPiece = newPiece(type, promoted.getColor());
		board.PlacePiece(newPiece, pos);
		piecesOnTheBoard.add(newPiece);

		return newPiece;
	}

	private ChessPieces newPiece(String type, Color color) {
		if (type.equals("B"))
			return new Bishop(board, color);
		if (type.equals("N"))
			return new Knight(board, color);
		if (type.equals("Q"))
			return new Queen(board, color);
		return new Rook(board, color);
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

		// specialMove castling Kingside rook
		if (p instanceof King && target.getColunm() == source.getColunm() + 2) {
			Position targetT = new Position(source.getRow(), target.getColunm() + 3);
			Position sourceT = new Position(source.getRow(), target.getColunm() + 1);
			ChessPieces rook = (ChessPieces) board.RemovePiece(sourceT);
			board.PlacePiece(rook, targetT);
			rook.increaseMoveCount();
		}

		// specialMove castling queenside rook
		if (p instanceof King && target.getColunm() == source.getColunm() - 2) {
			Position targetT = new Position(source.getRow(), target.getColunm() - 4);
			Position sourceT = new Position(source.getRow(), target.getColunm() - 1);
			ChessPieces rook = (ChessPieces) board.RemovePiece(sourceT);
			board.PlacePiece(rook, targetT);
			rook.increaseMoveCount();
		}

		// specialMove en passant
		if (p instanceof Pawn) {
			if (source.getColunm() != target.getColunm() && capturedPieces == null) {
				Position pawnPosition;
				if (p.getColor() == Color.WIHTE) {
					pawnPosition = new Position(target.getRow() + 1, target.getColunm());
				} else {
					pawnPosition = new Position(target.getRow() - 1, target.getColunm());

				}
				capturedPiece = (ChessPieces) board.RemovePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);

			}
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
		if (p instanceof King && target.getColunm() == source.getColunm() + 2) {
			Position targetT = new Position(source.getRow(), target.getColunm() + 3);
			Position sourceT = new Position(source.getRow(), target.getColunm() + 1);
			ChessPieces rook = (ChessPieces) board.RemovePiece(targetT);
			board.PlacePiece(rook, sourceT);
			rook.decreaseMoveCount();
		}

		// specialMove castling queenside rook
		if (p instanceof King && target.getColunm() == source.getColunm() - 2) {
			Position targetT = new Position(source.getRow(), target.getColunm() - 4);
			Position sourceT = new Position(source.getRow(), target.getColunm() - 1);
			ChessPieces rook = (ChessPieces) board.RemovePiece(targetT);
			board.PlacePiece(rook, sourceT);
			rook.decreaseMoveCount();
		}

		if (p instanceof Pawn) {
			if (source.getColunm() != target.getColunm() && capturedPieces == enPassantVulnerable) {
				ChessPieces pawn = (ChessPieces) board.RemovePiece(target);
				Position pawnPosition;
				if (p.getColor() == Color.WIHTE) {
					pawnPosition = new Position(target.getRow() + 1, target.getColunm());
				} else {
					pawnPosition = new Position(target.getRow() - 1, target.getColunm());

				}
				board.PlacePiece(pawn, pawnPosition);

			}

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
		PlaceNewPiece('d', 1, new Queen(board, Color.WIHTE));
		PlaceNewPiece('e', 1, new King(board, Color.WIHTE, this));
		PlaceNewPiece('f', 1, new Bishop(board, Color.WIHTE));
		PlaceNewPiece('g', 1, new Knight(board, Color.WIHTE));
		PlaceNewPiece('h', 1, new Rook(board, Color.WIHTE));
		PlaceNewPiece('a', 2, new Pawn(board, Color.WIHTE, this));
		PlaceNewPiece('b', 2, new Pawn(board, Color.WIHTE, this));
		PlaceNewPiece('c', 2, new Pawn(board, Color.WIHTE, this));
		PlaceNewPiece('d', 2, new Pawn(board, Color.WIHTE, this));
		PlaceNewPiece('e', 2, new Pawn(board, Color.WIHTE, this));
		PlaceNewPiece('f', 2, new Pawn(board, Color.WIHTE, this));
		PlaceNewPiece('g', 2, new Pawn(board, Color.WIHTE, this));
		PlaceNewPiece('h', 2, new Pawn(board, Color.WIHTE, this));

		PlaceNewPiece('a', 8, new Rook(board, Color.BLACK));
		PlaceNewPiece('b', 8, new Knight(board, Color.BLACK));
		PlaceNewPiece('c', 8, new Bishop(board, Color.BLACK));
		PlaceNewPiece('d', 8, new Queen(board, Color.BLACK));
		PlaceNewPiece('e', 8, new King(board, Color.BLACK, this));
		PlaceNewPiece('c', 8, new Bishop(board, Color.BLACK));
		PlaceNewPiece('f', 8, new Rook(board, Color.BLACK));
		PlaceNewPiece('g', 8, new Knight(board, Color.BLACK));
		PlaceNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		PlaceNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		PlaceNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		PlaceNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		PlaceNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		PlaceNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		PlaceNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}
}
