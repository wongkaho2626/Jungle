package piece;

import main.Player;
import movement.DefaultMove;

public class Cat extends Piece{
	private int rank = 2;

	public Cat(Player player, String location) {
		super(player, location);
		setMovement(new DefaultMove());
	}

	@Override
	public int getRank() {
		return rank;
	}

}
