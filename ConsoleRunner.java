
/**
 * ConsoleRunner:  Prompts the user to determine the parameters of the Game
 * constructor.  Creates a Game and manages the alternating calls to the
 * ‘place’ methods in Game.  Prompts the user for inputs and outputs the state
 * of the board to the console.
 */

import java.util.Scanner;

public class ConsoleRunner {

	/**
	 * Should the human player be the X?  Note that X always goes first.
	 */
	private boolean playerIsX;
	private Game game;
	// Use to process text input from the user.
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Constructor
	 */
	public ConsoleRunner() {
		/*
		 * Use the 'next' method of Scanner and the 'matches' of the String class to
		 * process user responses as strings.
		 */
		String matches;
		while (true) {
			System.out.println("Do you want to play as X, enter (Y/N):");
			matches = scanner.next(); // gets and stores user input
			if (matches.equals("Y") || matches.equals("y")) {
				playerIsX = true;
				break;
			} else if (matches.equals("N") || matches.equals("n")) {
				break;
			}
			System.out.println("Invalid. Enter Again.\n");
		}

		boolean challenge;
		while (true) {
			System.out.println("Do you want a challenge (Y/N): ");
			matches = scanner.next();
			if (matches.equals("Y") || matches.equals("y")) {
				challenge = true;
				break;
			} else if (matches.equals("N") || matches.equals("n")) {
				challenge = false;
				break;
			}
			System.out.println("Invalid choice! Enter Again.\n");
		}
		game = new Game(playerIsX, challenge);
		System.out.println(game.getBoard().toString());
	}

	private void playerTurn() {
		while (true) {
			System.out.println("Enter appropriate x-coordinate: ");
			int x = scanner.nextInt();
			System.out.println("Enter appropriate y-coordinate: ");
			int y = scanner.nextInt();
			if (game.placePlayerPiece(y, x)) {
				break;
			}
			System.out.println("Square already selected. Select other!\n");
		}
		System.out.println("After your move: ");
		String result = checkGameStatus();
		System.out.println(game.getBoard().toString());
		if (!result.equals("")) {
			System.out.println(result);
			System.exit(0);
		}
	}

	private void aiTurn() {
		System.out.println("After AI move: ");
		game.aiPlacePiece();
		String result = checkGameStatus();
		System.out.println(game.getBoard().toString());
		if (!result.equals("")) {
			System.out.println(result);
			System.exit(0);
		}
	}

	// Method for checking the current status of the game
	private String checkGameStatus() {
		if (game.getStatus() == GameStatus.O_WON) {
			if (playerIsX) {
				return "AI won!";
			} else {
				return "You won!";
			}
		} else if (game.getStatus() == GameStatus.X_WON) {
			if (playerIsX) {
				return "You won!";
			} else {
				return "Ai won!";
			}
		} else if (game.getStatus() == GameStatus.DRAW) {
			return "Draw!";
		}
		return "";
	}

	/**
	 * Enter the main control loop which returns only at the end of the game when
	 * one party has won or there has been a draw.
	 */
	public void mainLoop() {
		/*
		 * TBD
		 *
		 * Use the 'nextInt' method of Scanner class to read user responses as integers.
		 *
		 * There is enough work to do here that you may want to introduce private
		 * methods (i.e. helper methods).
		 */
		while (game.getStatus() == GameStatus.IN_PROGRESS) {
			if (playerIsX) {
				playerTurn();
				aiTurn();
			} else {
				aiTurn();
				playerTurn();
			}
		}
	}

}
