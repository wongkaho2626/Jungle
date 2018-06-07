package main;
public class Player {
	private String name;
	private boolean turn;
	private boolean win;
	
	public Player(String name, boolean turn, boolean win) {
		this.name = name;
		this.turn = turn;
		this.win = win;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}
	
	
}
