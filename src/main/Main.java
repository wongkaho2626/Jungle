package main;

import gui.JungleGameGUI;

public class Main {
	
	public static void main (String [] args) {
		JungleGameGUI window = new JungleGameGUI();
		window.startNewGame();
		window.frame.setVisible(true);
		JungleGame jungleGame = new JungleGame(window);
	}
}
