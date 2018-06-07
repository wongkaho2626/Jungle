package piece;
import gui.JungleGameGUI;
import main.ChessBoard;
import main.Player;
import movement.Movement;

public abstract class Piece{
	private Movement movement;
	private Player player;
	private String location;
	private boolean alive = true;
	private boolean trap = false;
	
	public Piece(Player player, String location) {
		this.player = player;
		this.location = location;
	}
	
    public abstract int getRank();
	
    public String getLocation() {
		return location;
    };
    
    public void setLocation(String location) {
    		this.location = location;
    }
    
    public boolean isAlive() {
    		return alive;
    }
    
    public void setAlive (boolean alive) {
    		this.alive = alive;
    }
    
    public Player getPlayer() {
    		return player;
    }
	
    public boolean move(JungleGameGUI window, Player player, String fromPosition, String toPosition, ChessBoard cheesboard){
        return movement.move(window, player, fromPosition, toPosition, cheesboard);
     }
  
     public void setMovement(Movement movement) {
         this.movement = movement;
     }

	public boolean isTrap() {
		return trap;
	}

	public void setTrap(boolean trap) {
		this.trap = trap;
	}
    
}
