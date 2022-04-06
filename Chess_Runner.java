
public class Chess_Runner {
	public static void main(String[] args) {
		System.out.println("'/'/Create chess board with one side (BLACK) having two rooks and a pawn and the other side (WHITE) having a rook and two pawns."); 
		ChessBoard b = new ChessBoard();
		Rook r1 = new Rook('R', new Location(3, 3), b, Piece.BLACK);
		b.put(r1, r1.getLocation());
		Rook r2 = new Rook('r', new Location(2, 1), b, Piece.WHITE);
		b.put(r2, r2.getLocation());
		Rook r3 = new Rook('R', new Location(0, 3), b, Piece.BLACK);
		b.put(r3, r3.getLocation());
		Pawn p1 = new Pawn('P', new Location(1,0), b, Piece.BLACK);
		b.put(p1, p1.getLocation());
		Pawn p2 = new Pawn('p', new Location(6, 5), b, Piece.WHITE);
		b.put(p2, p2.getLocation());
		Pawn p3 = new Pawn('p', new Location(5, 4), b, Piece.WHITE);
		b.put(p3, p3.getLocation());
		System.out.println(b);
		System.out.println("'/'/Rook (R) at (3,3) moves.");
		r1.move();
		System.out.println(b);
		System.out.println("'/'/Pawn (P) at (1, 0) moves and takes the Rook at (2, 1).");
		p1.move();
		System.out.println(b);
		System.out.println("'/'/Rook (R) at (0,3) moves.");
		r3.move();
		System.out.println(b);
		System.out.println("'/'/Rook (R) at (6,5) moves.");
		p2.move();
		System.out.println(b);
		System.out.println("'/'/Rook (R) at (5,4) moves.");
		p3.move();
		System.out.println(b);
	}
}
