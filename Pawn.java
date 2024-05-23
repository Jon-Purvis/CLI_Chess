public class Pawn extends Piece { // done except for en peasant

   public Pawn(boolean white) {
      super(white);
   }
   
      
   @Override
   public boolean canMove(Board gameBoard, Square start, Square end) {
      int deltaX = end.getX() - start.getX();
      int deltaY = end.getY() - start.getY();
      Square target = gameBoard.board[end.getX()][end.getY()];
      
      // capture move
      if (Math.abs(deltaX) == 1 && deltaY == this.getDirection() && !target.isEmpty() 
          && target.getPiece().isWhite() != this.isWhite()) {
         return true;
         
      // non-capture move   
      } else if (deltaX == 0 && (deltaY == this.getDirection() && target.isEmpty() || 
                (deltaY == this.getDirection() * 2) && this.hasMoved() == false)) {
         return true;
         
      } else {
         return false;
      }
   }
   
   
   @Override
   public String toString() {
      if (isWhite()) { return "♟"; }
      else { return "♙"; }
   } 
}