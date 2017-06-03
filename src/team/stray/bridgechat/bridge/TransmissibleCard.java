package team.stray.bridgechat.bridge;

import java.io.Serializable;

import team.stray.bridgechat.online.Transmissible;

public class TransmissibleCard implements Transmissible, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Card transmissibleCard;
	
	public Card getTransmissibleCard() {
		return transmissibleCard;
	}
	
	public void setTransmissibleCard(Card transmissibleCard) {
		this.transmissibleCard = transmissibleCard;
	}
}
