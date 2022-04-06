import java.util.ArrayList;

public abstract class Piece {
	public static final int WHITE = -1;
	public static final int BLACK = 1;
	private char name;
	private Location loc;
	private Grid g;
	private int side;
	
	public Piece(char name, Location loc, Grid g, int s) {
		this.name = name;
		this.loc = loc;
		this.g = g;
		side = s;
	}
	
	public char getName() {
		return name;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public Grid getGrid() {
		return g;
	}
	
	public int getSide() {
		return side;
	}
	
	public boolean updateLocation(int r, int c) {
		Location loc = new Location(r, c);
		if (g.isValid(loc)) {
			this.loc = loc;
			return true;
		}
		return false;
	}
	
	public abstract ArrayList<Location> getPossibleMoveLocations();
	
	public abstract Piece clone();
	
	public void move() {
		ArrayList<Location> pos = getPossibleMoveLocations();
		for (int i = 0; i < pos.size(); i++) {
			if (!g.isEmpty(pos.get(i)) && ((Piece)g.get(pos.get(i))).getSide() != this.side) {
				Piece old = (Piece)(g.get(pos.get(i)));
				g.remove(old.loc);
				Location prev = this.loc;
				g.put(this, old.loc);
				g.remove(prev);
				this.updateLocation(old.loc.getX(), old.loc.getY());
				return;
			}
		}
		int i = (int)(Math.random()*pos.size());
		Location prev = this.loc;
		g.put(this, pos.get(i));
		g.remove(prev);
		this.updateLocation(pos.get(i).getX(), pos.get(i).getY());
		return;
		
	}

	public boolean equals(Piece other){

	}	

	public String toString() {
		return ""+name;
	}
	
}
