
/**
 * Represents the logic of the game in terms of detecting wins or draws.  Also
 * places new pieces for the human player or AI.
 */
import java.util.Scanner;

public class Game {
	private Board board = new Board();
	private GameStatus status;
	private AI ai;
	private boolean aiIsX;
	private boolean playerIsX;
	private char playerChar;
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Construct a new Game according to the given parameters.
	 */
	public Game(boolean playerIsX, boolean challenging) {
		aiIsX = !playerIsX;
		this.playerIsX = playerIsX;
		status = GameStatus.IN_PROGRESS;
		if (challenging) {
			ai = new SmartAI(aiIsX);
		} else {
			ai = new DumbAI(aiIsX);
		}
	}

	/**
	 * Return a copy of the board's current contents.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Get the game's status.
	 */
	public GameStatus getStatus() {
		if (!board.isFull()) {
			if ( // horizontal
			board.get(0, 0) == 'X' && board.get(0, 1) == 'X' && board.get(0, 2) == 'X'
					|| board.get(1, 0) == 'X' && board.get(1, 1) == 'X' && board.get(1, 2) == 'X'
					|| board.get(2, 0) == 'X' && board.get(2, 1) == 'X' && board.get(2, 2) == 'X'

					// vertical
					|| board.get(0, 0) == 'X' && board.get(1, 0) == 'X' && board.get(2, 0) == 'X'
					|| board.get(0, 1) == 'X' && board.get(1, 1) == 'X' && board.get(2, 1) == 'X'
					|| board.get(0, 2) == 'X' && board.get(1, 2) == 'X' && board.get(2, 2) == 'X'

					// diagonal
					|| board.get(0, 0) == 'X' && board.get(1, 1) == 'X' && board.get(2, 2) == 'X'
					|| board.get(0, 2) == 'X' && board.get(1, 1) == 'X' && board.get(2, 0) == 'X') {
				status = GameStatus.X_WON;
			}

			else if ( // horizontal
			board.get(0, 0) == 'O' && board.get(0, 1) == 'O' && board.get(0, 2) == 'O'
					|| board.get(1, 0) == 'O' && board.get(1, 1) == 'O' && board.get(1, 2) == 'O'
					|| board.get(2, 0) == 'O' && board.get(2, 1) == 'O' && board.get(2, 2) == 'O'

					// vertical
					|| board.get(0, 0) == 'O' && board.get(1, 0) == 'O' && board.get(2, 0) == 'O'
					|| board.get(0, 1) == 'O' && board.get(1, 1) == 'O' && board.get(2, 1) == 'O'
					|| board.get(0, 2) == 'O' && board.get(1, 2) == 'O' && board.get(2, 2) == 'O'

					// diagonal
					|| board.get(0, 0) == 'O' && board.get(1, 1) == 'O' && board.get(2, 2) == 'O'
					|| board.get(0, 2) == 'O' && board.get(1, 1) == 'O' && board.get(2, 0) == 'O') {
				status = GameStatus.O_WON;
			} else {
				status = GameStatus.IN_PROGRESS;
			}
		} else {
			status = GameStatus.DRAW;
		}
		return status;
	}

	/**
	 * Place a piece for the player on the board.
	 *
	 * @param i i-coordinate of desired position.
	 * @param j j-coordinate of desired position
	 * @return true only if the coordinates of the desired position are in range and
	 *         the corresponding cell is empty.
	 *
	 * @precondition status == IN_PROGRESS
	 *
	 */
	public boolean placePlayerPiece(int i, int j) {
		if (playerIsX) {
			playerChar = 'X';
		} else {
			playerChar = '0';
		}
		if (status == GameStatus.IN_PROGRESS) {
			while (board.get(i, j) != ' ') {
				System.out.println("Enter appropriate x-coordinate: ");
				i = scanner.nextInt();
				System.out.println("Enter appropriate y-coordinate: ");
				j = scanner.nextInt();
			}

			if (board.get(i, j) == ' ') {
				Move move = new Move(i, j, playerChar);
				board = new Board(board, move);
				return true;
			}
		}
		return false;
	}

	/**
	 * @precondition status == IN_PROGRESS
	 */
	public void aiPlacePiece() {
		if (status == GameStatus.IN_PROGRESS) {
			Move move = ai.chooseMove(board);
			board = new Board(board, move);
		}
	}
}
