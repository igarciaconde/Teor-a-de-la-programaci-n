package tp.p2.control.exceptions;

public class GameOverException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public GameOverException() {
		super("GAME OVER.");
	}
}
