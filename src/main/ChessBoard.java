package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import piece.Piece;
import piece.PieceFactory;

public class ChessBoard {
	private Player playerA, playerB;
	private List<Piece> pieceListA, pieceListB;
	private List<String> trapArray, riverArray;
	private List<Den> denArray;
	HashMap<String, Box> chessBoardMap;
	private Piece elephantA, lionA, tigerA, leopardA, wolfA, dogA, catA, ratA;
	private Piece elephantB, lionB, tigerB, leopardB, wolfB, dogB, catB, ratB;
	private PieceFactory pieceFactory;
	
	public ChessBoard(Player playerA, Player playerB){
		this.playerA = playerA;
		this.playerB = playerB;
		denArray = Arrays.asList(new Den("D1", playerA), new Den("D9", playerB));
		trapArray = Arrays.asList("C1", "C9", "D2", "D8", "E1", "E9");
		riverArray = Arrays.asList("B4", "B5", "B6", "C4", "C5", "C6", "E4", "E5", "E6", "F4", "F5", "F6");
	}
	
	public void init() {
		setChessBoardMap();
		
		pieceListA = new ArrayList<Piece>();
		pieceFactory = new PieceFactory();
		elephantA = pieceFactory.createPiece(8, playerA, "A3");
		lionA = pieceFactory.createPiece(7, playerA, "G1");
		tigerA = pieceFactory.createPiece(6, playerA, "A1");
		leopardA = pieceFactory.createPiece(5, playerA, "E3");
		wolfA = pieceFactory.createPiece(4, playerA, "C3");
		dogA = pieceFactory.createPiece(3, playerA, "F2");
		catA = pieceFactory.createPiece(2, playerA, "B2");
		ratA = pieceFactory.createPiece(1, playerA, "G3");
		pieceListA.add(elephantA);
		pieceListA.add(lionA);
		pieceListA.add(tigerA);
		pieceListA.add(leopardA);
		pieceListA.add(wolfA);
		pieceListA.add(dogA);
		pieceListA.add(catA);
		pieceListA.add(ratA);

		pieceListB = new ArrayList<Piece>();
		elephantB = pieceFactory.createPiece(8, playerB, "G7");
		lionB = pieceFactory.createPiece(7, playerB, "A9");
		tigerB = pieceFactory.createPiece(6, playerB, "G9");
		leopardB = pieceFactory.createPiece(5, playerB, "C7");
		wolfB = pieceFactory.createPiece(4, playerB, "E7");
		dogB = pieceFactory.createPiece(3, playerB, "B8");
		catB = pieceFactory.createPiece(2, playerB, "F8");
		ratB = pieceFactory.createPiece(1, playerB, "A7");
		pieceListB.add(elephantB);
		pieceListB.add(lionB);
		pieceListB.add(tigerB);
		pieceListB.add(leopardB);
		pieceListB.add(wolfB);
		pieceListB.add(dogB);
		pieceListB.add(catB);
		pieceListB.add(ratB);
		
		for(Piece p : pieceListA) {
			chessBoardMap.get(p.getLocation()).setPiece(p);
		}
		
		for(Piece p : pieceListB) {
			chessBoardMap.get(p.getLocation()).setPiece(p);
		}
	}
	
	public void loadChessBoard(HashMap<String, List<Object>> data, Player playerA, Player playerB) {
		setChessBoardMap();
		
		pieceListA = new ArrayList<Piece>();
		pieceListB = new ArrayList<Piece>();
		for(Map.Entry entrySet : data.entrySet()) {
			Piece piece;
			pieceFactory = new PieceFactory();
			List<Object> list = (List<Object>) entrySet.getValue();
			if(list.get(1).equals(playerA.getName())) {
				piece = pieceFactory.createPiece(Integer.parseInt(list.get(0).toString()), playerA, entrySet.getKey().toString());
				piece.setAlive((boolean) list.get(2));
				piece.setTrap((boolean) list.get(3));
				pieceListA.add(piece);
			}else if(list.get(1).equals(playerB.getName())){
				piece = pieceFactory.createPiece(Integer.parseInt(list.get(0).toString()), playerB, entrySet.getKey().toString());
				piece.setAlive((boolean) list.get(2));
				piece.setTrap((boolean) list.get(3));
				pieceListB.add(piece);
			}
		}
		
		for(Piece p : pieceListA) {
			chessBoardMap.get(p.getLocation()).setPiece(p);
		}
		
		for(Piece p : pieceListB) {
			chessBoardMap.get(p.getLocation()).setPiece(p);
		}
	}
	
	private void setChessBoardMap() {
		chessBoardMap = new HashMap<String, Box>();
		for (char alphabet = 'A'; alphabet <= 'G'; alphabet++) {
			for(int i = 1; i <= 9; i++) {
				Box box;
				String location = alphabet + String.valueOf(i);
				if(denArray.get(0).getLocation().contains(location) || denArray.get(1).getLocation().contains(location)) {
					box = new Box(true, false, false);
				}else if(trapArray.contains(location)) {
					box = new Box(false, true, false);
				}else if(riverArray.contains(location)) {
					box = new Box(false, false, true);
				}else {
					box = new Box(false, false, false);
				}
				chessBoardMap.put(location, box);
			}
		}
	}
	
	public HashMap<String, Box> getChessBoardMap() {
		return chessBoardMap;
	}
		
	public List<Den> getDenArray() {
		return denArray;
	}
	
	public List<String> getTrapArray() {
		return trapArray;
	}
	
	public List<String> getRiverArray() {
		return riverArray;
	}
	
	public List<Piece> getPieceListA() {
		return pieceListA;
	}
	
	public List<Piece> getPieceListB() {
		return pieceListB;
	}

}
