package piece;

import main.Player;
import movement.JumbableMove;

public class Tiger extends Piece{
	private int rank = 6;
	
	public Tiger(Player player, String location) {
		super(player, location);
		setMovement(new JumbableMove());
	}

	public int getRank() {
		return rank;
	}

}
