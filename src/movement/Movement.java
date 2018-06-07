package movement;

import gui.JungleGameGUI;
import main.ChessBoard;
import main.Player;

public interface Movement {
	boolean move(JungleGameGUI window, Player player, String fromPosition, 
			String toPosition, ChessBoard cheesboard);
	boolean isJumable();
	boolean isSwimable();
}
