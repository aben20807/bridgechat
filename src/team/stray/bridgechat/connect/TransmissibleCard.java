package team.stray.bridgechat.connect;

import team.stray.bridgechat.bridge.Card;

public class TransmissibleCard extends Transmissible{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Card transmissibleCard;
	
	public TransmissibleCard(){
		transmissibleCard = new Card();
		timestamp = new Timestamp();
		type = Transmissible.CARD;
	}

	/*getter and setter*/
	
	public Card getTransmissibleCard() {
		return transmissibleCard;
	}
	
	public void setTransmissibleCard(Card transmissibleCard) {
		this.transmissibleCard = transmissibleCard;
	}
}