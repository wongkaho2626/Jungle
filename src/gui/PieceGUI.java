package gui;

import piece.Piece;

public class PieceGUI{
	private Piece piece;
	private String id;
	private String ImageIconLocation;
	
	public PieceGUI(Piece piece, String id) {
		this.piece = piece;
		this.id = id;
	}

	public Piece getPiece() {
		return piece;
	}
	
	public String getId() {
		return id;
	}

	public String getImageIconLocation() {
		return ImageIconLocation;
	}

	public void setImageIconLocation(String imageIconLocation) {
		ImageIconLocation = imageIconLocation;
	}

}
