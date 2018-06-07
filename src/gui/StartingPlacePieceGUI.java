package gui;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.ChessBoard;
import main.Player;
import piece.Cat;
import piece.Dog;
import piece.Elephant;
import piece.Leopard;
import piece.Lion;
import piece.Piece;
import piece.Rat;
import piece.Tiger;
import piece.Wolf;

public class StartingPlacePieceGUI {
	private JungleGameGUI window;
	private ChessBoard chessboard;
	private List<PieceGUI> pieceGUIList;
	private Player playerA, playerB;
	
	public StartingPlacePieceGUI(JungleGameGUI window, ChessBoard chessboard, Player playerA, Player playerB) {
		this.window = window;
		this.chessboard = chessboard;
		this.playerA = playerA;
		this.playerB = playerB;
	}
	
	public void init() {
		 setPieceGUIList();		
		
		for(PieceGUI pieceGUI : pieceGUIList) {			
			BoxGUI boxGUI = window.getChessBoardMapGUI().get(pieceGUI.getPiece().getLocation());
			JLabel Label = boxGUI.getjLabel();
			Label.setIcon(new ImageIcon(pieceGUI.getImageIconLocation()));
			window.getPanel().revalidate();  
			window.getPanel().repaint();
		}
	}
	
	public void setPieceGUIList() {
		pieceGUIList = window.getPieceGUIList();
		for(Piece p : chessboard.getPieceListA()) {
			PieceGUI pieceGUI = new PieceGUI(p, p.getPlayer().getName() + p.getRank());
			pieceGUIList.add(pieceGUI);
		}
		for(Piece p : chessboard.getPieceListB()) {
			PieceGUI pieceGUI = new PieceGUI(p, p.getPlayer().getName() + p.getRank());
			pieceGUIList.add(pieceGUI);
		}
		
		for(PieceGUI pieceGUI : pieceGUIList) {
			if(pieceGUI.getPiece() instanceof Elephant) {
				if(pieceGUI.getPiece().getPlayer().equals(playerA)) {
					pieceGUI.setImageIconLocation("./resource/pieces/red_elephant.png");
				}else if(pieceGUI.getPiece().getPlayer().equals(playerB)){
					pieceGUI.setImageIconLocation("./resource/pieces/black_elephant.png");
				}
			}else if(pieceGUI.getPiece() instanceof Lion) {
				if(pieceGUI.getPiece().getPlayer().equals(playerA)) {
					pieceGUI.setImageIconLocation("./resource/pieces/red_lion.png");
				}else if(pieceGUI.getPiece().getPlayer().equals(playerB)){
					pieceGUI.setImageIconLocation("./resource/pieces/black_lion.png");
				}
			}else if(pieceGUI.getPiece() instanceof Tiger) {
				if(pieceGUI.getPiece().getPlayer().equals(playerA)) {
					pieceGUI.setImageIconLocation("./resource/pieces/red_tiger.png");
				}else if(pieceGUI.getPiece().getPlayer().equals(playerB)){
					pieceGUI.setImageIconLocation("./resource/pieces/black_tiger.png");
				}
			}else if(pieceGUI.getPiece() instanceof Leopard) {
				if(pieceGUI.getPiece().getPlayer().equals(playerA)) {
					pieceGUI.setImageIconLocation("./resource/pieces/red_leopard.png");
				}else if(pieceGUI.getPiece().getPlayer().equals(playerB)){
					pieceGUI.setImageIconLocation("./resource/pieces/black_leopard.png");
				}
			}else if(pieceGUI.getPiece() instanceof Wolf) {
				if(pieceGUI.getPiece().getPlayer().equals(playerA)) {
					pieceGUI.setImageIconLocation("./resource/pieces/red_wolf.png");
				}else if(pieceGUI.getPiece().getPlayer().equals(playerB)){
					pieceGUI.setImageIconLocation("./resource/pieces/black_wolf.png");
				}
			}else if(pieceGUI.getPiece() instanceof Dog) {
				if(pieceGUI.getPiece().getPlayer().equals(playerA)) {
					pieceGUI.setImageIconLocation("./resource/pieces/red_dog.png");
				}else if(pieceGUI.getPiece().getPlayer().equals(playerB)){
					pieceGUI.setImageIconLocation("./resource/pieces/black_dog.png");
				}
			}else if(pieceGUI.getPiece() instanceof Cat) {
				if(pieceGUI.getPiece().getPlayer().equals(playerA)) {
					pieceGUI.setImageIconLocation("./resource/pieces/red_cat.png");
				}else if(pieceGUI.getPiece().getPlayer().equals(playerB)){
					pieceGUI.setImageIconLocation("./resource/pieces/black_cat.png");
				}
			}else if(pieceGUI.getPiece() instanceof Rat) {
				if(pieceGUI.getPiece().getPlayer().equals(playerA)) {
					pieceGUI.setImageIconLocation("./resource/pieces/red_rat.png");
				}else if(pieceGUI.getPiece().getPlayer().equals(playerB)){
					pieceGUI.setImageIconLocation("./resource/pieces/black_rat.png");
				}
			}
		}
		
		for(PieceGUI pieceGUI : pieceGUIList) {
			window.setPieceMapGUI(pieceGUI.getPiece().getPlayer().getName() + pieceGUI.getPiece().getRank(), pieceGUI);
		}
	}
}
