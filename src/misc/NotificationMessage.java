package misc;

public class NotificationMessage {
    private static String message;

	public static String getMessage() {
		return NotificationMessage.message;
	}

	public static void setMessage(String message) {
		NotificationMessage.message = message;
	}
}