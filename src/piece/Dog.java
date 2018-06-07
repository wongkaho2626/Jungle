package piece;

import main.Player;
import movement.DefaultMove;

public class Dog extends Piece{
	private int rank = 3;
	
	public Dog(Player player, String location) {
		super(player, location);
		setMovement(new DefaultMove());
	}

	@Override
	public int getRank() {
		return rank;
	}

}
