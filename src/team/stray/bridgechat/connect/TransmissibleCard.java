package team.stray.bridgechat.connect;

import team.stray.bridgechat.bridge.Card;

public class TransmissibleCard extends TransmitTimestamp implements Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Card transmissibleCard;
	
	/*getter and setter*/
	
	public Card getTransmissibleCard() {
		return transmissibleCard;
	}
	
	public void setTransmissibleCard(Card transmissibleCard) {
		this.transmissibleCard = transmissibleCard;
	}
	
	
}
