package team.stray.bridgechat.connect.transmissible;

import team.stray.bridgechat.bridge.GameClient;
import team.stray.bridgechat.connect.Timestamp;
import team.stray.bridgechat.connect.Transmissible;

public class TransmissibleGameClient extends Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameClient transmissibleGameClient;
	
	public TransmissibleGameClient(){
		transmissibleGameClient = new GameClient("test");
		timestamp = new Timestamp();
		type = Transmissible.GAMECLIENT;
	}
	
	/*getter and setter*/
	
	public GameClient getTransmissibleGameClient() {
		return transmissibleGameClient;
	}

	public void setTransmissibleGameClient(GameClient transmissibleGameClient) {
		this.transmissibleGameClient = transmissibleGameClient;
	}
}