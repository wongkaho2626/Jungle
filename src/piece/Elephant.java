package piece;

import main.Player;
import movement.DefaultMove;

public class Elephant extends Piece{
	private int rank = 8;
	
	public Elephant(Player player, String location) {
		super(player, location);
		setMovement(new DefaultMove());
	}

	public int getRank() {
		return rank;
	}


}
