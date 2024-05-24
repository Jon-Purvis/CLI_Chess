public class Board {

   private boolean isWhiteTurn; // Indicates whose turn it is: true for white, false for black
   private Square whiteKingSquare;
   private Square blackKingSquare;


   public Square[][] board = new Square[8][8];
   
   public Board() {
   
      this.resetBoard();
      isWhiteTurn = true; // White starts the game
      
   }  
   
   
   public Square getSquare(int x, int y) {      
      return board[x][y];
   }
   
   
   public void resetBoard() {
   
      /* Places the white pieces on the board.
      */
      board[0][7] = new Square(new Rook(true), 0, 7);
      board[1][7] = new Square(new Knight(true), 1, 7);
      board[2][7] = new Square(new Bishop(true), 2, 7);
      board[3][7] = new Square(new Queen(true), 3, 7);
      board[4][7] = new Square(new King(true), 4, 7);
      setKingSquare(board[4][7]); // Set white king's square
      board[5][7] = new Square(new Bishop(true), 5, 7);
      board[6][7] = new Square(new Knight(true), 6, 7);
      board[7][7] = new Square(new Rook(true), 7, 7);
       
      for (int i = 0; i < 8; i++) { board[i][6] = new Square(new Pawn(true), i, 6); }
       
       
      /* Places the black pieces on the board.
      */
      board[0][0] = new Square(new Rook(false), 0, 0);
      board[1][0] = new Square(new Knight(false), 1, 0);
      board[2][0] = new Square(new Bishop(false), 2, 0);
      board[3][0] = new Square(new Queen(false), 3, 0);
      board[4][0] = new Square(new King(false), 4, 0);
      setKingSquare(board[4][0]); // Set black king's square
      board[5][0] = new Square(new Bishop(false), 5, 0);
      board[6][0] = new Square(new Knight(false), 6, 0);
      board[7][0] = new Square(new Rook(false), 7, 0);
       
      for (int i = 0; i < 8; i++) { board[i][1] = new Square(new Pawn(false), i, 1); } 
      
      
      /* Initializes all the squares in the center of the board
      */
      for (int i = 2; i < 6; i++) {
         for (int j = 0; j < 8; j++) {
            board[j][i] = new Square(null, j, i); 
         }
      }  
   }

   public boolean isWhiteTurn() {
      return isWhiteTurn;
  }

  public void switchTurn() {
      isWhiteTurn = !isWhiteTurn;
  }

   public void setKingSquare(Square square) {
      if (square.getPiece() instanceof King) {
         if (square.getPiece().isWhite()) {
            whiteKingSquare = square;
         } else {
            blackKingSquare = square;
         }
      }
   }

   public Square getKingSquare(boolean isWhite) {
      return isWhite ? whiteKingSquare : blackKingSquare;
   }
   
   
   public void printRow(int row) {
      for (int i = 0; i < 8; i++) { System.out.print(" ----- "); }
      System.out.println();
      for (int i = 0; i < 8; i++) { System.out.print("|     |" ); }
      System.out.println();
      
      for (int i = 0; i < 8; i++) { 
         if (board[i][row].getPiece() != null) {
            System.out.print("|  " + board[i][row].pieceToString() + "  |");
         }
         else { System.out.print("|  " + '-' + "  |"); } 
      }
      
      System.out.println();
      for (int i = 0; i < 8; i++) { System.out.print("|     |"); }
      System.out.println();
      for (int i = 0; i < 8; i++) { System.out.print(" ----- "); }
      System.out.println();
   }
   
   
   public void printBoard() {
      for (int row = 0; row <= 7; row++) {
         printRow(row);
      }
   }
   
   
   public void move(Piece piece, Square start, Square end) throws InvalidMoveException {
      if (piece.canMove(this, start, end)) {
          if (end.isEmpty()) {
              board[end.getX()][end.getY()].setPiece(piece);
          } else {
              board[end.getX()][end.getY()].getPiece().capture();
              board[end.getX()][end.getY()].setPiece(null);
              board[end.getX()][end.getY()].setPiece(piece);
          }
          board[start.getX()][start.getY()].setPiece(null);
          piece.setMoved();
          switchTurn();
      } else {
          throw new InvalidMoveException("Invalid move");
      }
  }
   
  
   
} // end of class



   