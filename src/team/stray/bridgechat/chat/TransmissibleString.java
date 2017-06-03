package team.stray.bridgechat.chat;

import java.io.Serializable;

import team.stray.bridgechat.online.Transmissible;

public class TransmissibleString implements Transmissible, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transmissibleString;
	
	public String getTransmissibleString() {
		return transmissibleString;
	}
	public void setTransmissibleString(String transmissibleString) {
		this.transmissibleString = transmissibleString;
	} 
}
