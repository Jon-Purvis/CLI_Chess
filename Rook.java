public class Rook extends Piece { // canMove doesn't work

   public Rook(boolean white) {
      super(white);
   }
   
   @Override
   public boolean canMove(Board gameBoard, Square start, Square end) {
      int deltaX = end.getX() - start.getX();
      int deltaY = end.getY() - start.getY();
    
    // Rook can only move in a straight line (either horizontally or vertically)
      if (deltaX != 0 && deltaY != 0) {
         return false;
      }
    
    // Check if the path is clear
      if (!isPathClear(gameBoard, start, end)) {
         return false;
      }
    
      Square target = gameBoard.getSquare(end.getX(), end.getY());
    
    // Check if the end square is empty or contains an opponent's piece
      if (!target.isEmpty() && target.getPiece().isWhite() == isWhite()) {
         return false;
      }
    
      return true;
   }
   
   
   public boolean isPathClear(Board gameBoard, Square start, Square end) {
      // Check if the rook is moving vertically
      if (start.getX() == end.getX()) {
         int startCol = start.getY();
         int endCol = end.getY();
         int colDir = (endCol > startCol) ? 1 : -1;
         int col = startCol + colDir;
        
         while (col != endCol) {
            if (!gameBoard.getSquare(start.getX(), col).isEmpty()) {
               return false;
            }
            col += colDir;
         }
      }
      // Check if the rook is moving horizontally
      else if (start.getY() == end.getY()) {
         int startRow = start.getX();
         int endRow = end.getX();
         int rowDir = (endRow > startRow) ? 1 : -1;
         int row = startRow + rowDir;
        
         while (row != endRow) {
            if (!gameBoard.getSquare(row, start.getY()).isEmpty()) {
               return false;
            }
            row += rowDir;
         }
      }
      // If the rook is not moving horizontally or vertically, the path is not clear
      else {
         return false;
      }
    
    // If we reach this point, the path is clear
      return true;   
   }
   
   @Override
   public String toString() {
      if (isWhite()) { return "♜"; }
      else { return "♖"; }
   }
}