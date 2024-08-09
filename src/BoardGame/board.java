package BoardGame;

public class Board {

	private int rows;
	private int colunms;
	private Piece[][] pieces;
	
	public Board(int rows, int colunms) {
		if (rows < 1 || colunms < 1) {
			throw new BoardException("error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.colunms = colunms;
		pieces = new Piece[rows][colunms];
	}
	
	public int getRows() {
		return rows;
	}

	public int getColunms() {
		return colunms;
	}

	public Piece piece(int row, int colunm) {
		if (!PositionExists(row, colunm)) {
			throw new BoardException("Position not on the board ");
		}
		return pieces[row][colunm];
	}

	public Piece RemovePiece(Position position) {
		if (!PositionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if (pieces(position) == null) {
			return null;
		}
		Piece aux = pieces(position);
		aux.position = null;
		pieces[position.getRow()][position.getColunm()] = null;
		return aux;
	}

	public Piece pieces(Position position) {
		if (!PositionExists(position)) {
			throw new BoardException("Position not on the board ");
		}
		return pieces[position.getRow()][position.getColunm()];
	}

	public void PlacePiece(Piece piece, Position position) {
		if (ThereIsAPiece(position)) {
			throw new BoardException("there is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColunm()] = piece;
		piece.position = position;
	}

	private Boolean PositionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < colunms;

	}

	public Boolean PositionExists(Position position) {
		return PositionExists(position.getRow(), position.getColunm());

	}

	public Boolean ThereIsAPiece(Position position) {
		if (!PositionExists(position)) {
			throw new BoardException("Position not on the board ");
		}
		return pieces(position) != null;
	}

}
