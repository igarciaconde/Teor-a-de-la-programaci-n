package tp.p2.control.exceptions;

public class CommandParseException extends Exception {
		
	private static final long serialVersionUID = 1L;

	public CommandParseException() {
		super("Commando erroneo");
	}
	
	public CommandParseException(String s) {
		super(s);
	}
}
