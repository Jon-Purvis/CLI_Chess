public class Chess {

   public static void main(String[] args) {
      Board gameBoard = new Board();
      // gameBoard.printBoard();

      Piece whitePawn = gameBoard.board[3][6].getPiece();
      gameBoard.move(whitePawn, gameBoard.board[3][6], gameBoard.board[3][5]);
      gameBoard.printBoard();
      System.out.println();

      /*
       * Piece whitePawn2 = gameBoard.board[1][6].getPiece();
       * gameBoard.move(whitePawn2, gameBoard.board[1][6], gameBoard.board[1][5]);
       * gameBoard.printBoard();
       * System.out.println();
       */

      Piece whiteBishop = gameBoard.board[2][7].getPiece();
      gameBoard.move(whiteBishop, gameBoard.board[2][7], gameBoard.board[5][4]);
      gameBoard.printBoard();
      System.out.println();

      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
            System.out.print(j + " " + i + " ");
            if (whiteBishop.canMove(gameBoard, gameBoard.board[1][6],
                  gameBoard.board[j][i])) {
               System.out.println(" Can move: yes");
            } else {
               System.out.println(" Can move: no");
            }
         }
      }

      /*
       * do {
       * Scanner keyboard = new Scanner(System.in);
       * System.out.println("Player 1's move");
       * System.out.println("Enter piece's starting location");
       * 
       * String startLoc = keyboard.nextLine(); // Read user input
       * int startX = parseInt(startLoc.charAt(0));
       * int startY = parseInt(startLoc.charAt(1));
       * 
       * System.out.println("Enter piece's ending location");
       * 
       * String endLoc = keyboard.nextLine(); // Read user input
       * int endX = parseInt(startLoc.charAt(0));
       * int endY = parseInt(startLoc.charAt(1));
       * 
       * 
       * 
       * 
       * 
       * Scanner keyboard = new Scanner(System.in);
       * System.out.println("Player 2's move");
       * String move = keyboard.nextLine(); // Read user input
       * //make move
       * 
       * } while(!gameIsOver());
       * }
       * 
       * public void Move(int startX, int startY, int endX, int endY) {}
       */

   }
}