public class ChessBoard implements Grid {

	private Piece[][] grid;

	public ChessBoard() {
		grid = new Piece[8][8];
	}
	
	@Override
	public int getNumRows() {
		// TODO Auto-generated method stub
		return grid==null? -1: grid.length;
	}

	@Override
	public int getNumCols() {
		// TODO Auto-generated method stub
		return grid == null? -1: grid[0].length;
	}

	@Override
	public boolean isValid(Location loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty(Location loc) {
		// TODO Auto-generated method stub
		return (loc != null && isValid(loc) && grid[loc.getX()][loc.getY()]==null);
	}

	@Override
	public boolean put(Object E, Location loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean remove(Object E) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean remove(Location loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(Location loc) {
		// TODO Auto-generated method stub
		if (isEmpty(loc))
			return null;
		return grid[loc.getX()][loc.getY()];
	}

	public String toString() {
		String s = "";
		if (grid == null)
			return s;
		for (Piece[] arr: grid) {
			for (Piece p: arr) {
				if (p == null)
					s += "[ ]";
				else
					s += "["+p.toString()+"]";
			}
			s+="\n";
		}
		return s;
	}
}
