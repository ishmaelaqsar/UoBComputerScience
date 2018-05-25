import java.util.Arrays;

/**
 * 
 */

/**
 * @author ixa444
 *
 */
public class Chess {

	private String[][] chessBoard = new String[10][10];

//	/**
//	 * @param chessBoard
//	 */
//	public Chess(String[][] chessBoard) {
//		this.chessBoard = chessBoard;
//	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String[] pieces = {" ", "rook", "knight", "bishop", "queen", "king", "bishop", "knight", "rook", " "};
		String
		String[] letters = {" ", "a", "b", "c", "d", "e", "f", "g", "h" ," "};
		for(int i = 0; i < chessBoard.length; i++){
			for(int j = 0; j < chessBoard[i].length; j++){
				if(i == 0){
					chessBoard[i][j] = letters[j];
				}
				else if(i == 9){
					chessBoard[i][j] = letters[j];
				}
				else if(i == 1){
					chessBoard[i][j] = pieces[j];
				}
				else if(i == 2){
					chessBoard[i][j] = "pawn |";
				}
				else if(i == 7){
					chessBoard[i][j] = "PAWN";
				}
				else if(i == 8){
					chessBoard[i][j] = pieces[j].toUpperCase();
				}
			}
			if(i == 0 || i == 9){
				chessBoard[i][0] = " ";
			}
			else{
				int k = 9 - i;
				String g = Integer.toString(k);
				chessBoard[i][0] = g;
			}
		}
		
		String board = "";
		
		for(int i = 0; i < chessBoard.length; i++){
			for(int j = 0; j<chessBoard[i].length; j++){
				board += chessBoard[i][j];
			}
			board += "\n";
		}
		
		return board;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Chess board = new Chess();
		
		System.out.println(board.toString());
	}

}
