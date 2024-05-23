public class King extends Piece { // done except castling

   public King(boolean white) {
      super(white);
   }
   
   @Override 
   public boolean canMove(Board board, Square start, Square end) {
      boolean canMove = false;
            
      // Checks to see make sure you are trying to move only one square
      int x = Math.abs(start.getX() - end.getX());
      int y = Math.abs(start.getY() - end.getY());
      if (x + y == 1) { canMove = true; }
      
      // Checks if the end square has a white piece already at it.
      if (end.getPiece() == null) {}
      
      else if (end.getPiece().isWhite() == this.isWhite()) {
         canMove = false;
      } 
      
      return canMove;     
   }
   
   @Override
   public String toString() {
      if (isWhite()) { return "♚"; }
      else { return "♔"; }
   }
   
}