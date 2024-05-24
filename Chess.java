import java.util.Scanner;

public class Chess {

   public static void main(String[] args) {
        Board gameBoard = new Board();

        // Simulate a simple game
        while (!isGameOver(gameBoard)) {
            // Print the current board
            gameBoard.printBoard();

            // Get the current player's move
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your move (e.g., 'a2 a4'): ");
            String move = scanner.nextLine();

            // Make the move
            boolean validMove = makeMove(gameBoard, move);
            if (!validMove) {
                System.out.println("Invalid move, try again.");
            }
        }

        // Game over, print the final board
        gameBoard.printBoard();
        System.out.println("Game over.");
    }

   private static boolean makeMove(Board board, String moveInput) {
      // Parse the moveInput
      String[] parts = moveInput.split(" ");
      if (parts.length != 2) {
          return false; // Invalid move format
      }
      String startPos = parts[0];
      String endPos = parts[1];

      // Convert position strings to board coordinates
      int startX = startPos.charAt(0) - 'a'; // Convert 'a' to 0, 'b' to 1, etc.
      int startY = 8 - (startPos.charAt(1) - '0');
      int endX = endPos.charAt(0) - 'a';
      int endY = 8 - (endPos.charAt(1) - '0');

      // Get the piece at the start position
      Piece piece = board.getSquare(startX, startY).getPiece();
      if (piece == null) {
          return false; // No piece at start position
      }

      // Move the piece on the board
      Square startSquare = board.getSquare(startX, startY);
      Square endSquare = board.getSquare(endX, endY);
      try {
          board.move(piece, startSquare, endSquare);
          return true; // Move successful
      } catch (InvalidMoveException e) {
          System.out.println("Invalid move: " + e.getMessage());
          return false; // Move unsuccessful
      }
   }

   private static boolean isGameOver(Board board) {
      // Get the current player's king position
      Square kingSquare = null;
      boolean isWhiteTurn = board.isWhiteTurn(); 
      for (int x = 0; x < 8; x++) {
          for (int y = 0; y < 8; y++) {
              Square square = board.board[x][y];
              Piece piece = square.getPiece();
              if (piece instanceof King && piece.isWhite() == isWhiteTurn) {
                  kingSquare = square;
                  break;
              }
          }
      }
      
   // Check if the king is in check
   if (kingSquare != null && isKingInCheck(board, kingSquare)) {
         // Now check if the king has legal moves to escape check
         if (!hasLegalMovesToEscapeCheck(board, kingSquare)) {
            // No legal moves to escape check, it's checkmate
            return true;
         }
   }

      return false; // Game is not over
   }
  
  // Helper method to check if the king is in check
  private static boolean isKingInCheck(Board board, Square kingSquare) {
      // Iterate over all opponent pieces and check if they can capture the king
      for (int x = 0; x < 8; x++) {
          for (int y = 0; y < 8; y++) {
              Square square = board.board[x][y];
              Piece piece = square.getPiece();
              if (piece != null && piece.isWhite() != kingSquare.getPiece().isWhite() && piece.canMove(board, square, kingSquare)) {
                  return true; // King is in check
              }
          }
      }
      return false; // King is not in check
  }
  
  private static boolean hasLegalMovesToEscapeCheck(Board board, Square kingSquare) {
   // Get the coordinates of the king's square
   int kingX = kingSquare.getX();
   int kingY = kingSquare.getY();
   
   // Iterate over all possible moves of the king
   for (int dx = -1; dx <= 1; dx++) {
       for (int dy = -1; dy <= 1; dy++) {
           // Skip the king's current position
           if (dx == 0 && dy == 0) {
               continue;
           }
           
           int newX = kingX + dx;
           int newY = kingY + dy;
           
           // Check if the new position is within the board bounds
           if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
               Square targetSquare = board.getSquare(newX, newY);
               Piece king = kingSquare.getPiece();
               // Check if the king can move to the target square without being in check
               if (king.canMove(board, kingSquare, targetSquare)) {
                   // Simulate the move and check if the king is still in check
                   Piece capturedPiece = targetSquare.getPiece();
                   targetSquare.setPiece(king);
                   kingSquare.setPiece(null);
                   boolean isInCheck = isKingInCheck(board, targetSquare);
                   // Undo the move
                   targetSquare.setPiece(capturedPiece);
                   kingSquare.setPiece(king);
                   if (!isInCheck) {
                       return true; // King has legal move to escape check
                   }
               }
           }
       }
   }
   
   return false; // King has no legal moves to escape check
}

}