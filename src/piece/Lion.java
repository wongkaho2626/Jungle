package piece;

import main.Player;
import movement.JumbableMove;

public class Lion extends Piece{
	private int rank = 7;
	
	public Lion(Player player, String location) {
		super(player, location);
		setMovement(new JumbableMove());
	}
	
	public int getRank() {
		return rank;
	}

}
