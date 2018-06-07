package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import gui.BoxGUI;
import gui.JungleGameGUI;
import piece.Piece;

public class Save {
	private Player playerA, playerB;
	private JungleGameGUI window;
	private ChessBoard chessboard;
	private HashMap<String, String> hashmapObject, currentIconMap;
	private HashMap<String, List<Object>> boxHavePieceMap;
	
	public Save(JungleGameGUI window, Player playerA, Player playerB, ChessBoard chessboard) {
		this.window = window;
		this.playerA = playerA;
		this.playerB = playerB;
		this.chessboard = chessboard;
	}

	@SuppressWarnings("rawtypes")
	public void saveToJsonFile() throws IOException {
		Gson gson = new Gson();
		boxHavePieceMap = new HashMap<String, List<Object>>();
		for(Map.Entry entrySet : chessboard.getChessBoardMap().entrySet()) {
			Box box = (Box) entrySet.getValue();
			if(box.getPiece() != null) {
				Piece p = box.getPiece();
				boxHavePieceMap.put(p.getLocation(), Arrays.asList(String.valueOf(p.getRank()), p.getPlayer().getName(), p.isAlive(), p.isTrap()));
			}
		}
		String boxHavePieceJson = gson.toJson(boxHavePieceMap);
		String playerAJson = gson.toJson(playerA);
		String playerBJson = gson.toJson(playerB);
		String textAreaObject = gson.toJson(window.getTextArea().getText());
		currentIconMap = new HashMap<String, String>();
		for(Map.Entry entrySet : window.getChessBoardMapGUI().entrySet()) {
			BoxGUI boxGUI = (BoxGUI) entrySet.getValue();
			String location = entrySet.getKey().toString();
			String IconLocation = boxGUI.getjLabel().getIcon().toString();
			currentIconMap.put(location, IconLocation);
		}
		String currentIconObject = gson.toJson(currentIconMap);
		
		hashmapObject = new HashMap<String, String>();
		hashmapObject.put("boxHavePiece", boxHavePieceJson);
		hashmapObject.put("playerA", playerAJson);
		hashmapObject.put("playerB", playerBJson);
		hashmapObject.put("textArea", textAreaObject);
		hashmapObject.put("currentIcon", currentIconObject);
	
		String saveFile = gson.toJson(hashmapObject);
		writeToJson(saveFile); 
	}
	
	public void writeToJson (String json) throws IOException {
		File file = new File("save.json");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file);
		
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		bufferedWriter.write(json);
		bufferedWriter.close();
	}
}
