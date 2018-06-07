package piece;
import main.Player;

public class PieceFactory {
	public Piece createPiece(int rank, Player player, String location) {
		if(rank == 8) {
			return new Elephant(player, location);
		}else if (rank == 7) {
			return new Lion(player, location);
		}else if (rank == 6) {
			return new Tiger(player, location);
		}else if (rank == 5) {
			return new Leopard(player, location);
		}else if (rank == 4) {
			return new Wolf(player, location);
		}else if (rank == 3) {
			return new Dog(player, location);
		}else if (rank == 2) {
			return new Cat(player, location);
		}else {
			return new Rat(player, location);
		}
	}
}
