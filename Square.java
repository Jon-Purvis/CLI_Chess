public class Square {

   private Piece piece;
   private int x;
   private int y;
   
   public Square(Piece piece, int x, int y) {
      this.piece = piece;
      this.x = x;
      this.y = y;
   }

   public Piece getPiece() { return piece; }
   
   public String pieceToString() { return piece.toString(); }
   
   public boolean isEmpty() { return piece == null; }
   
   public void setPiece(Piece p) { piece = p; }
   
   public int getX() { return x; }
   
   public int getY() { return y; }
   
   public void setX(int x) { this.x = x; }
   
   public void setY(int y) { this.y = y; }
}