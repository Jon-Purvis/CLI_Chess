public class Bishop extends Piece { 

   public Bishop(boolean white) {
      super(white);
   }
   
   @Override
   public boolean canMove(Board gameBoard, Square start, Square end) {      
      int deltaX = end.getX() - start.getX();
      int deltaY = end.getY() - start.getY();
   
      // Check if the move is diagonal
      if (Math.abs(deltaX) == Math.abs(deltaY)) {
         Square target = gameBoard.getSquare(end.getX(), end.getY());
      
        // Check if the target square is empty or contains an opponent's piece
         if (target.isEmpty() || target.getPiece().isWhite() != this.isWhite()) {
            return isPathClear(gameBoard, start, end); // Check if the path is clear
         }
      }
      return false;
   }
  
   
   public boolean isPathClear(Board gameBoard, Square start, Square end) {
      int deltaX = end.getX() - start.getX();
      int deltaY = end.getY() - start.getY();
      int rowDir = (deltaX > 0) ? 1 : -1;
      int colDir = (deltaY > 0) ? 1 : -1;
      int row = start.getX() + rowDir;
      int col = start.getY() + colDir;
   
      while (row != end.getX() && col != end.getY()) {
         Square currentSquare = gameBoard.getSquare(row, col);
         if (!currentSquare.isEmpty()) {
            return false; // A piece blocks the path
         }
         row += rowDir;
         col += colDir;
      }
   
      return true; // The path is clear
   }
   
   @Override
   public String toString() {
      if (isWhite()) { return "♝"; }
      else { return "♗"; }
   }
}