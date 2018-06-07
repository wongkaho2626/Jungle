package main;

import piece.Piece;

public class Box{
	private boolean den;
	private boolean trap;
	private boolean river;
	private Piece piece;
	
	public Box (boolean den, boolean trap, boolean river) {
		this.den = den;
		this.trap = trap;
		this.river = river;
	}
	
	public boolean isDen() {
		return den;
	}
	public void setDen(boolean den) {
		this.den = den;
	}
	public boolean isTrap() {
		return trap;
	}
	public void setTrap(boolean trap) {
		this.trap = trap;
	}
	public boolean isRiver() {
		return river;
	}
	public void setRiver(boolean river) {
		this.river = river;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}