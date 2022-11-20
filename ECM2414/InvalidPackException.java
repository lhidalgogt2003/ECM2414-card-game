package ECM2414;

import java.io.Serial;

public class InvalidPackException extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;

	public InvalidPackException() {
		super("Invalid pack");
	}
}
