package team.stray.bridgechat.connect.transmissible;

import team.stray.bridgechat.connect.Timestamp;
import team.stray.bridgechat.connect.Transmissible;

public class TransmissibleString extends Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transmissibleString;

	public TransmissibleString(){
		transmissibleString = new String();
		timestamp = new Timestamp();
		type = Transmissible.STRING;
	}
	
	/*getter and setter*/
	
	public String getTransmissibleString() {
		return transmissibleString;
	}
	public void setTransmissibleString(String transmissibleString) {
		this.transmissibleString = transmissibleString;
	}
}