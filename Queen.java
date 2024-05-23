public class Queen extends Piece {  

   public Queen(boolean white) {
      super(white);
   }
   
   @Override
   public boolean canMove(Board gameBoard, Square start, Square end) {
      int deltaX = end.getX() - start.getX();
      int deltaY = end.getY() - start.getY();
      Square target = gameBoard.getSquare(end.getX(), end.getY());
        
      if (deltaX == 0 || deltaY == 0) {  // Rook-like move
         if (isPathClear(gameBoard, start, end)) {
            if (target.isEmpty() || target.getPiece().isWhite() != this.isWhite()) {
               return true;
            }
         }
      } else if (Math.abs(deltaX) == Math.abs(deltaY)) {  // Bishop-like move
         if (isPathClear(gameBoard, start, end)) {
            if (target.isEmpty() || target.getPiece().isWhite() != this.isWhite()) {
               return true;
            }
         }
      }
        
      return false;   
   }
   
   
   public boolean isPathClear(Board gameBoard, Square start, Square end) {
      // Check if the queen is moving vertically
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
        // Check if the queen is moving horizontally
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
        // Check if the queen is moving diagonally
      else if (Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY())) {
         int startRow = start.getX();
         int endRow = end.getX();
         int startCol = start.getY();
         int endCol = end.getY();
         int rowDir = (endRow > startRow) ? 1 : -1;
         int colDir = (endCol > startCol) ? 1 : -1;
         int row = startRow + rowDir;
         int col = startCol + colDir;
            
         while (row != endRow && col != endCol) {
            if (!gameBoard.getSquare(row, col).isEmpty()) {
               return false;
            }
            row += rowDir;
            col += colDir;
         }
      }
        // If the queen is not moving horizontally, vertically, or diagonally, the path is not clear
      else {
         return false;
      }
        
        // If we reach this point, the path is clear
      return true;
   }
   
   @Override
   public String toString() {
      if (isWhite()) { return "♛"; }
      else { return "♕"; }
   }

 
}