package team.stray.bridgechat.connect;

import team.stray.bridgechat.bridge.Card;

public class TransmissibleCard implements Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Card transmissibleCard;
	private Timestamp timestamp;
	
	public TransmissibleCard(){
		transmissibleCard = new Card();
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
	
	public Card getTransmissibleCard() {
		return transmissibleCard;
	}
	
	public void setTransmissibleCard(Card transmissibleCard) {
		this.transmissibleCard = transmissibleCard;
	}
}
