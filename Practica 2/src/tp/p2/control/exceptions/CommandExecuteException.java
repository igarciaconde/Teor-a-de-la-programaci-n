package tp.p2.control.exceptions;

public class CommandExecuteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public CommandExecuteException() {
		super("Error durante la ejecucion de un comando.");
	}
	
	public CommandExecuteException(String s) {
		super(s);
	}

}
