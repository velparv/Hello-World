import java.util.ArrayList;

public class Pawn extends Piece{

	public Pawn(char name, Location loc, Grid g, int s) {
		super(name, loc, g, s);
	}

	@Override
	public ArrayList<Location> getPossibleMoveLocations() {
		// TODO Auto-generated method stub
		ArrayList<Location> poss = new ArrayList<Location>();
		int r = getLocation().getX();
		int c = getLocation().getY();
		Location temp;
		if (getSide() < 0) {
			temp = new Location(r-1, c);
			if (getGrid().isValid(temp) && (getGrid().isEmpty(temp) || ((Piece)(getGrid().get(temp))).getSide()!=this.getSide())) {
				poss.add(temp);
			}
			Location tempL = new Location(r-1, c-1);
			Location tempR = new Location(r-1, c+1);
			if (getGrid().isValid(tempL) && !getGrid().isEmpty(tempL) && (((Piece)(getGrid().get(tempL))).getSide()!=this.getSide())) {
				poss.add(tempL);
			}
			if (getGrid().isValid(tempR) && !getGrid().isEmpty(tempR) && (((Piece)(getGrid().get(tempR))).getSide()!=this.getSide())) {
				poss.add(tempR);
			}
		}
		else {
			temp = new Location(r+1, c);
			if (getGrid().isValid(temp) && (getGrid().isEmpty(temp) || ((Piece)(getGrid().get(temp))).getSide()!=this.getSide())) {
				poss.add(temp);
			}
			Location tempL = new Location(r+1, c-1);
			Location tempR = new Location(r+1, c+1);
			if (getGrid().isValid(tempL) && !getGrid().isEmpty(tempL) && (((Piece)(getGrid().get(tempL))).getSide()!=this.getSide())) {
				poss.add(tempL);
			}
			if (getGrid().isValid(tempR) && !getGrid().isEmpty(tempR) && (((Piece)(getGrid().get(tempR))).getSide()!=this.getSide())) {
				poss.add(tempR);
			}
		}
		return poss;
			
	}

	@Override
	public Piece clone() {
		// TODO Auto-generated method stub
		return new Pawn(getName(), getLocation(), getGrid(), getSide());
	}
	
	
	
}
