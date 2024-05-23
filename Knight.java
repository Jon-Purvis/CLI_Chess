public class Knight extends Piece { 

   public Knight(boolean white) {
      super(white);
   }
   
   @Override
   public boolean canMove(Board board, Square start, Square end) {
      boolean  canMove = false;
            
      // Checks to see make sure you are trying to move only in an "L shape"
      int x = Math.abs(start.getX() - end.getX());
      int y = Math.abs(start.getY() - end.getY());
      if (x * y == 2) { canMove = true; }  
      
      // Checks if the end square has a white piece already at it.
      if (end.getPiece() == null) {}
      
      else if (end.getPiece().isWhite() == this.isWhite()) {
         canMove = false;
      }    
      
      return canMove; 
   }
   
   @Override
   public String toString() {
      if (isWhite()) { return "♞"; }
      else { return "♘"; }
   }

   
}