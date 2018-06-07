package movement;

public class SwimableMove extends DefaultMove implements Movement{
	@Override
	public boolean isSwimable() {
		return true;
	}
}
