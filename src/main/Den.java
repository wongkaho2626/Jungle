package main;

public class Den {
	private String location;
	private Player player;
	
	public Den(String location, Player player) {
		this.location = location;
		this.player = player;
	}
	
	public String getLocation() {
		return location;
	}
	public Player getPlayer() {
		return player;
	}
}
