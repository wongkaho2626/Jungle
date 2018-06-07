package piece;

import main.Player;
import movement.SwimableMove;

public class Rat extends Piece{
	private int rank = 1;

	public Rat(Player player, String location) {
		super(player, location);
		setMovement(new SwimableMove());
	}

	@Override
	public int getRank() {
		return rank;
	}

}
