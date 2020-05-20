package model;

public class OAZalo {
	private int err;
	private String message;
	private String messageId;
	
	public OAZalo() {

	}
	public OAZalo(int err, String message, String messageId) {
		this.err = err;
		this.message = message;
		this.messageId	= messageId;
	}
	
	public int getErr() {
		return err;
	}
	public void setErr(int err) {
		this.err = err;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
