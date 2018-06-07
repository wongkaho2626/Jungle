package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import piece.Piece;

public class MovePieceGUI {
	private JungleGameGUI window;
	private String fromPosition, toPosition;
	
	public MovePieceGUI(JungleGameGUI window, String fromPosition, String toPosition) {
		this.window = window;
		this.fromPosition = fromPosition;
		this.toPosition = toPosition;
	}

	public void movePiece(Piece piece) {
		BoxGUI boxGUI;
		JLabel Label;
		//remove the piece fromPosition
		boxGUI = window.getChessBoardMapGUI().get(fromPosition);
		Label = boxGUI.getjLabel();
		Label.setIcon(new ImageIcon(boxGUI.getImageIconLocation()));
		
		//add the piece toPosition
		boxGUI = window.getChessBoardMapGUI().get(toPosition);
		Label = boxGUI.getjLabel();
		System.out.println(window.getPieceMapGUI().get(piece.getPlayer().getName() + piece.getRank()));
		Label.setIcon(new ImageIcon(window.getPieceMapGUI().get(piece.getPlayer().getName() + piece.getRank()).getImageIconLocation()));
		
		window.getPanel().revalidate();  
		window.getPanel().repaint();
	}
}
