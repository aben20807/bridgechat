package team.stray.bridgechat.connect;

public abstract class TransmitTimestamp implements Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String sendTimestamp;
	
	/*getter and setter*/
	
	public String getSendTimestamp() {
		return sendTimestamp;
	}

	public void setSendTimestamp(String sendTimestamp) {
		this.sendTimestamp = sendTimestamp;
	}
}
