package team.stray.bridgechat.connect.transmissible;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.connect.Timestamp;
import team.stray.bridgechat.connect.Transmissible;

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