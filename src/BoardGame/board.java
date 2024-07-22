package BoardGame;

public class board {

	private int rows;
	private int colunms;
	private piece[][]pieces;
	
	public board(int rows, int colunms) {
		this.rows = rows;
		this.colunms = colunms;
		pieces = new piece[rows][colunms];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColunms() {
		return colunms;
	}

	public void setColunms(int colunms) {
		this.colunms = colunms;
	}
	
	
	
	
}
