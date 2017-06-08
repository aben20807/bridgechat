package team.stray.bridgechat.connect;

import team.stray.bridgechat.bridge.GameClient;

public class TransmissibleGameClient implements Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameClient transmissibleGameClient;
	private Timestamp timestamp;
	
	@Override
	public String getTimestamp() {
		return timestamp.getTimestamp();
	}

	@Override
	public void setTimestamp(String sendTimestamp) {
		this.timestamp.setTimestamp(sendTimestamp);
	}
	
	/*getter and setter*/
	
	public GameClient getTransmissibleGameClient() {
		return transmissibleGameClient;
	}

	public void setTransmissibleGameClient(GameClient transmissibleGameClient) {
		this.transmissibleGameClient = transmissibleGameClient;
	}
}
