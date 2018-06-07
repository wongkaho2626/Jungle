package movement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.JungleGameGUI;
import gui.MovePieceGUI;
import main.ChessBoard;
import main.Den;
import main.Player;
import piece.Piece;

public class DefaultMove implements Movement {
	Map<String, List<String>> jumbStepMap;
	
	public DefaultMove(){
		jumbStepMap = new HashMap<String, List<String>>();
		setJumbStepMap();
	}

	@Override
	public boolean move (JungleGameGUI window, Player player, String fromPosition, String toPosition, ChessBoard chessboard) {
		Piece piece = chessboard.getChessBoardMap().get(fromPosition).getPiece();
		String piecePlayerName = piece.getPlayer().getName();
		
		//check piece belong to the player
		if(!piecePlayerName.equals(player.getName())) {
			return false;
		}
		
		//check whether the fromPosition and toPosition are on the chessboard
		if(chessboard.getChessBoardMap().get(fromPosition) == null || chessboard.getChessBoardMap().get(toPosition) == null) {
			return false;
		}
		
		//check whether piece move to its own den
		for(Den den : chessboard.getDenArray()) {
			if(den.getPlayer().getName().equals(piecePlayerName) && chessboard.getChessBoardMap().get(toPosition).isDen() && den.getLocation().equals(toPosition)) {
				return false;
			}
		}
		
		//check whether piece is alive
		if(!piece.isAlive()) {
			return false;
		}
		
		char fromPositionX = fromPosition.charAt(0);
		int fromPositionY = Integer.valueOf(fromPosition.substring(1));
		char toPositionX = toPosition.charAt(0);
		int toPositionY = Integer.valueOf(toPosition.substring(1));
		int diffX = Math.abs(fromPositionX - toPositionX);
		int diffY = Math.abs(fromPositionY - toPositionY); 
		
		//check whether normal move or jump move
		if(diffX + diffY == 1) {
			//check whether toPosition have piece in water or vise versa
			if(isSwimable()) {
				if(chessboard.getChessBoardMap().get(fromPosition).isRiver() && !chessboard.getChessBoardMap().get(toPosition).isRiver() && chessboard.getChessBoardMap().get(toPosition).getPiece() != null) {
					return false;
				}
				if(!chessboard.getChessBoardMap().get(fromPosition).isRiver() && chessboard.getChessBoardMap().get(toPosition).isRiver() && chessboard.getChessBoardMap().get(toPosition).getPiece() != null) {
					return false;
				}
			}else {
				if(chessboard.getRiverArray().contains(toPosition)) {
					return false;
				}
			}
			System.out.println("Normal move");
			return eat(window, piece, fromPosition, toPosition, chessboard);
			
		}else if(jumbStepMap.get(fromPosition+toPosition).get(jumbStepMap.get(fromPosition+toPosition).size()-1).equals(toPosition) && isJumable()) {
			//check whether a rat intervening water squares
			for(int i = 0; i < jumbStepMap.get(fromPosition+toPosition).size()-1; i++) {
				if(chessboard.getChessBoardMap().get(jumbStepMap.get(fromPosition+toPosition).get(i)).getPiece() != null) {
					return false;
				}
			}
			
			System.out.println("Jump move");
			return eat(window, piece, fromPosition, toPosition, chessboard);
		}else {
			return false;
		}
	}
	
	public boolean eat(JungleGameGUI window, Piece piece, String fromPosition, String toPosition, ChessBoard chessboard) {
		//no piece in the toPosition
		if(chessboard.getChessBoardMap().get(toPosition).getPiece() == null) {
			changeLocation(window, piece, fromPosition, toPosition, chessboard);
			return true;
		}else {
			if(isCapture(piece, fromPosition, toPosition, chessboard)) {
				changeLocation(window, piece, fromPosition, toPosition, chessboard);
				return true;
			}else {
				return false;
			}
		}
	}
	
	public boolean isCapture(Piece piece, String fromPosition, String toPosition, ChessBoard chessboard) {
		Piece enemyPiece = chessboard.getChessBoardMap().get(toPosition).getPiece();
		if(!enemyPiece.getPlayer().equals(piece.getPlayer())) {
			if(enemyPiece.isTrap()) {
				return true;
			}else if(piece.getRank() == 1 && enemyPiece.getRank() == 8) {
				return true;
			}else if(piece.getRank() >= enemyPiece.getRank()) {
				if(piece.getRank() == 8 && enemyPiece.getRank() == 1) {
					return false;
				}else {
					return true;
				}
			}
		}
		return false;
	}
	
	public void changeLocation(JungleGameGUI window, Piece piece, String fromPosition, String toPosition, ChessBoard chessboard) {
		Piece enemyPiece = chessboard.getChessBoardMap().get(toPosition).getPiece();
		if(enemyPiece != null)
			enemyPiece.setAlive(false);
		
		chessboard.getChessBoardMap().get(fromPosition).setPiece(null);
		if(chessboard.getChessBoardMap().get(toPosition).isTrap()) {
			piece.setTrap(true);
		}else {
			piece.setTrap(false);
		}
		MovePieceGUI movePieceGUI = new MovePieceGUI(window, fromPosition, toPosition);
		movePieceGUI.movePiece(piece);
		chessboard.getChessBoardMap().get(toPosition).setPiece(piece);
		piece.setLocation(toPosition);
	}
	
	private void setJumbStepMap() {
		//vertical jump
		jumbStepMap.put("B3B7", Arrays.asList("B4", "B5", "B6", "B7"));
		jumbStepMap.put("C3C7", Arrays.asList("C4", "C5", "C6", "C7"));
		jumbStepMap.put("E3E7", Arrays.asList("E4", "E5", "E6", "E7"));
		jumbStepMap.put("F3F7", Arrays.asList("F4", "F5", "F6", "F7"));
		jumbStepMap.put("B7B3", Arrays.asList("B6", "B5", "B4", "B3"));
		jumbStepMap.put("C7C3", Arrays.asList("C6", "C5", "C4", "C3"));
		jumbStepMap.put("E7E3", Arrays.asList("E6", "E5", "E4", "E3"));
		jumbStepMap.put("F7F3", Arrays.asList("F6", "F5", "F4", "F3"));
		
		//horizontal jump
		jumbStepMap.put("A4D4", Arrays.asList("B4", "C4", "D4"));
		jumbStepMap.put("A5D5", Arrays.asList("B5", "C5", "D5"));
		jumbStepMap.put("A6D6", Arrays.asList("B6", "C6", "D6"));
		jumbStepMap.put("D4A4", Arrays.asList("C4", "B4", "A4"));
		jumbStepMap.put("D5A5", Arrays.asList("C5", "B5", "A5"));
		jumbStepMap.put("D6A6", Arrays.asList("C6", "B6", "A6"));
		jumbStepMap.put("D4G4", Arrays.asList("E4", "F4", "G4"));
		jumbStepMap.put("D5G5", Arrays.asList("E5", "F5", "G5"));
		jumbStepMap.put("D6G6", Arrays.asList("E6", "F6", "G6"));
		jumbStepMap.put("G4D4", Arrays.asList("F4", "E4", "D4"));
		jumbStepMap.put("G5D5", Arrays.asList("F5", "E5", "D5"));
		jumbStepMap.put("G6D6", Arrays.asList("F6", "E6", "D6"));
	}

	@Override
	public boolean isJumable() {
		return false;
	}

	@Override
	public boolean isSwimable() {
		return false;
	}
}
