package piece;

import main.Player;
import movement.DefaultMove;

public class Wolf extends Piece{
	private int rank = 4;

	public Wolf(Player player, String location) {
		super(player, location);
		setMovement(new DefaultMove());
	}

	@Override
	public int getRank() {
		return rank;
	}

}
