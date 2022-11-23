package ECM2414;

import java.io.Serial;

/**
 * Custom exception class to handle invalid pack/file errors
 */

public class InvalidPackException extends Exception {

	@Serial
	private static final long serialVersionUID = 6966383005802193295L;

	public InvalidPackException(String s) {
		super(s);
	}
}
