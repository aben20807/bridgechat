package team.stray.bridgechat.connect;

public class TransmissibleString implements Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transmissibleString;
	private Timestamp timestamp;
	
	public TransmissibleString(){
		transmissibleString = new String();
		timestamp = new Timestamp();
	}
	
	@Override
	public String getTimestamp() {
		return timestamp.getTimestamp();
	}

	@Override
	public void setTimestamp(String sendTimestamp) {
		this.timestamp.setTimestamp(sendTimestamp);
	}
	
	/*getter and setter*/
	
	public String getTransmissibleString() {
		return transmissibleString;
	}
	public void setTransmissibleString(String transmissibleString) {
		this.transmissibleString = transmissibleString;
	}
}
