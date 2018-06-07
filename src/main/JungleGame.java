package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import gui.BoxGUI;
import gui.JungleGameGUI;
import gui.StartingPlacePieceGUI;
import piece.Piece;

public class JungleGame {
	private Player playerA, playerB;
	private String playerAName, playerBName;
	private JungleGameGUI window;
	private static ChessBoard chessboard;
	private JButton btnNewStartButton, btnLoadButton, btnSaveButton;
	private JTextField textField;
	private JTextArea textArea;
	private String firstClick;
	
	public JungleGame(JungleGameGUI window) {
		this.window = window;
		btnNewStartButton = window.getBtnNewStartButton();
		btnLoadButton = window.getBtnLoadButton();
		btnSaveButton = window.getBtnSaveButton();
		textField = window.getTextField();
		textArea = window.getTextArea();
		
		btnNewStartButton.setEnabled(false);
		btnSaveButton.setEnabled(false);
		
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					try {
						getQuery(textField.getText());
						textField.setText("");
					} catch (Exception e1) {
						System.out.println(e1);
					}
				}
			}
		});
		
		btnNewStartButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				startNewGame();
				btnNewStartButton.setEnabled(false);
				btnSaveButton.setEnabled(false);
			} 
		});
		
		btnLoadButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				loadJsonFile();
			} 
		});
		
		btnSaveButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				Save save = new Save(window, playerA, playerB, chessboard);
				try {
					save.saveToJsonFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} 
		});
		
		textArea.append("Here is the Jungle Game.\n");
		textArea.append("Player A, please enter your name: \n");
	}

	private ChessBoard startBuildChessboard(Player playerA, Player playerB) {
		return new ChessBoard(playerA, playerB);
	}
	
	public StartingPlacePieceGUI placePiece(JungleGameGUI window, ChessBoard chessboard, Player playerA, Player playerB) {
		StartingPlacePieceGUI startingPlacePiece = new StartingPlacePieceGUI(window, chessboard, playerA, playerB);
		return startingPlacePiece;
	}
	
	public boolean checkStep(JungleGameGUI window, Player player, String query, ChessBoard cheesboard) {
		try {
			String fromPosition = query.substring(0, 2);
			String toPosition = query.substring(4);
			Piece piece = cheesboard.getChessBoardMap().get(fromPosition).getPiece();
			if(piece == null) {
				textArea.append("Move is invalid, please enter again. \n");
				return false;
			}else {
				if(piece.move(window, player, fromPosition, toPosition, cheesboard)) {
					return true;
				}else {
					textArea.append("Move is invalid, please enter again. \n");
					return false;
				}
			}
		}catch(Exception ex) {
			textArea.append("Move is invalid, please enter again. \n");
			return false;
		}
	}
	
	
	private void startNewGame() {
		textArea.setText("");
		textArea.append("Here is the Jungle Game.\n");
		textArea.append("Player A, please enter your name: \n");
		window.reset();
		playerAName = null;
		playerBName = null;
		chessboard = null;
		playerA = null;
		playerB = null;
		firstClick = null;
		window.startNewGame();
	}
	
	private void getQuery(String query) {
		if(!query.equals("")) {
			if(playerAName == null) {
				textArea.append("Player A, your name is : " + query + "\n");
				playerAName = query;
				playerA = new Player(playerAName, true, false);
				textArea.append("Player B, please enter your name: \n");
			}else if(playerBName == null) {
				playerBName = query;
				playerB = new Player(playerBName, false, false);
				textArea.append("Player B, your name is : " + query + "\n");
				chessboard = startBuildChessboard(playerA, playerB);
				chessboard.init();
				textArea.append("The game is started. \nPlayer A, please insert the move. \n");
				textArea.append("(FromPosition->ToPosition) \n");
				StartingPlacePieceGUI startingPlacePiece = placePiece(window, chessboard, playerA, playerB);
				startingPlacePiece.init();
				touchScreen();
			}else {
				btnNewStartButton.setEnabled(true);
				btnSaveButton.setEnabled(true);
				if(playerA.isTurn()) {
					textArea.append(playerA.getName() + ":" + query + "\n");
					if(checkStep(window, playerA, query, chessboard)) {
						playerA.setTurn(!playerA.isTurn());
						playerB.setTurn(!playerB.isTurn());
					}
					checkWin(playerA, chessboard);
				}else if(playerB.isTurn()){
					textArea.append(playerB.getName() + ":" + query + "\n");
					if(checkStep(window, playerB, query, chessboard)) {
						playerA.setTurn(!playerA.isTurn());
						playerB.setTurn(!playerB.isTurn());
					}
					checkWin(playerB, chessboard);
				}
			}
		}
	}
	
	private void checkWin(Player player, ChessBoard chessboard) {
		boolean allDead;
		for(Den den : chessboard.getDenArray()) {
			if(chessboard.getChessBoardMap().get(den.getLocation()).getPiece() != null) {
				player.setWin(true);
				JOptionPane.showMessageDialog(window.frame, player.getName() + ", Congrats, you win the game.");
			}
		}
		allDead = true;
		for(Piece p : chessboard.getPieceListA()) {
			if(p.isAlive()) {
				allDead = false;
			}
		}
		allDead = true;
		for(Piece p : chessboard.getPieceListB()) {
			if(p.isAlive()) {
				allDead = false;
			}
		}
		if(allDead) {
			player.setWin(true);
			JOptionPane.showMessageDialog(window.frame, player.getName() + ", Congrats, you win the game.");
		}
	}
	
	private void touchScreen() {
		for(Map.Entry entrySet : window.getChessBoardMapGUI().entrySet()) {
			BoxGUI boxGUI = (BoxGUI) entrySet.getValue();
			boxGUI.getjLabel().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(boxGUI.getBoxName());
					if(firstClick == null) {
						firstClick = boxGUI.getBoxName();
					}else {
						getQuery(firstClick + "->" + boxGUI.getBoxName());
						firstClick = null;
					}
				}
			});
		}
	}
		
	private void loadJsonFile() {
		try {
			startNewGame();
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(new FileReader("save.json"));
			HashMap<String, String> hashMap = new Gson().fromJson(jsonObject, HashMap.class);
			Gson gson = new Gson();
			//load playerA
			playerA = gson.fromJson(hashMap.get("playerA"), Player.class);
			playerAName = playerA.getName();
			//load playerB
			playerB = gson.fromJson(hashMap.get("playerB"), Player.class);
			playerBName = playerB.getName();
			chessboard = startBuildChessboard(playerA, playerB);
			//load the textArea
			String textArea = gson.fromJson(hashMap.get("textArea"), String.class);
			window.getTextArea().setText(textArea);
			//load the chessboard GUI image
			HashMap<String, String> currentIcon = gson.fromJson(hashMap.get("currentIcon"), HashMap.class);
			for(Map.Entry entrySet : currentIcon.entrySet()) {
				window.getChessBoardMapGUI().get(entrySet.getKey()).getjLabel().setIcon(new ImageIcon(entrySet.getValue().toString()));;
			}
			//load the piece
			HashMap<String, List<Object>> data = gson.fromJson(hashMap.get("boxHavePiece"), HashMap.class);
			chessboard.loadChessBoard(data, playerA, playerB);
			StartingPlacePieceGUI startingPlacePiece = placePiece(window, chessboard, playerA, playerB);
			startingPlacePiece.setPieceGUIList();
			touchScreen();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
}
