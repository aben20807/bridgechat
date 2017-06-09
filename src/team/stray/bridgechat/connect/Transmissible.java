package team.stray.bridgechat.connect;

import java.io.Serializable;

public abstract class Transmissible implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int CARD 		= 1;
	public static final int GAMECLIENT 	= 2;
	public static final int STRING 		= 3;
	
	protected Timestamp timestamp;
	protected int type;

	/*getter and setter*/
	
	public String getTimestamp() {
		return timestamp.getTimestamp();
	}

	public void setTimestamp(String sendTimestamp) {
		this.timestamp.setTimestamp(sendTimestamp);
	}
	
	public int getType() {
		return type;
	}
}