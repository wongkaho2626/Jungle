package piece;

import main.Player;
import movement.DefaultMove;

public class Leopard extends Piece{
	private int rank = 5;
	
	public Leopard(Player player, String location) {
		super(player, location);
		setMovement(new DefaultMove());
	}

	@Override
	public int getRank() {
		return rank;
	}

}
