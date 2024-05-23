public abstract class Piece {

   private boolean white = true;
   private boolean captured = false;
   private boolean hasMoved = false;
   
   public Piece(boolean white) {
      if (!white) { setBlack(); }
   }
   
   public boolean isWhite() {
      return white;
   }
   
   public boolean isCaptured() {
      return captured;
   }
   
   public boolean hasMoved() {
      return hasMoved;
   }
   
   public int getDirection() {
      return (this.isWhite() == true) ? -1 : 1;
   }

   public void setBlack() {
      white = false;
   }
   
   public void capture() {
      captured = true;
   }
   
   public void setMoved() {
      hasMoved = true;
   }
   
   public abstract boolean canMove(Board gameBoard, Square start, Square end);
   
   public abstract String toString();

}