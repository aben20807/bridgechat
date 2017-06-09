package team.stray.bridgechat.connect;

public final class Timestamp extends Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String timestamp;
	
	/*getter and setter*/
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String sendTimestamp) {
		this.timestamp = sendTimestamp;
	}
}