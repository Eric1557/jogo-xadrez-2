package chess;


import BoardGame.Position;

public class ChessPosition {

	private char colunm;
	private char row;
	
	public ChessPosition(char colunm, char row) {
		if(colunm < 'a' || colunm > 'h'|| row < 1 || row > 8) {
			throw new ChessExceptions("Error instanting ChessPosition.valid values are from a1 to h8");
		}
		this.colunm = colunm;
		this.row = row;
	}
	

	public char getColunm() {
		return colunm;
	}


	public char getRow() {
		return row;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.FromPosition(toPosition());
	}
	
	
	protected Position toPosition() {
		return new Position(8 - row,colunm - 'a');
	}
	
	protected static ChessPosition FromPosition(Position position){
		return new ChessPosition((char)('a' + position.getColunm()),(char) (8 - position.getRow()));
	}
	
	@Override
	public String toString() {
		return " " + colunm + row;
		
	}
	
	
}
