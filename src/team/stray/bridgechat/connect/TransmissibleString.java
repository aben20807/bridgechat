package team.stray.bridgechat.connect;

public class TransmissibleString extends TransmitTimestamp implements Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transmissibleString;
	
	/*getter and setter*/
	
	public String getTransmissibleString() {
		return transmissibleString;
	}
	public void setTransmissibleString(String transmissibleString) {
		this.transmissibleString = transmissibleString;
	} 
}
