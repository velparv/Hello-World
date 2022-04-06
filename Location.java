
public class Location implements Comparable<Location> {
	private int x, y;
	public Location() {
		
	}
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Location clone() {
		return new Location(this.x, this.y);
	}
	public int compareTo(Location other) {
		if (this.x == other.x) {
			return this.y - other.y;
		}
		else
			return this.x - other.x;
	}
	public String toString() {
		return "("+x+","+y+")";
	}
}
