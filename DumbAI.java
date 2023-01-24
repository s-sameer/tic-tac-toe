
/**
 * Realization of AI interface using simplistic random placement strategy.
 */

import java.util.Random;

public class DumbAI implements AI {

	private Random random = new Random();
	private char aiPiece;

	/**
	 * Construct a DumbAI.
	 * 
	 * @param aiIsX Indicates whether the AI player's piece is the 'X'.
	 */
	public DumbAI(boolean aiIsX) {
		if (aiIsX) {
			aiPiece = 'X';
		} else {
			aiPiece = 'O';
		}
	}

	@Override
	public Move chooseMove(Board board) {
		int i;
		int j;

		do {
			i = random.nextInt(3);
			j = random.nextInt(3);
		} while (board.get(i, j) != ' ');
		return new Move(i, j, aiPiece);
	}
}
