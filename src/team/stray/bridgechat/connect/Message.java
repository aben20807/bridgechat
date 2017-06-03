package team.stray.bridgechat.connect;

import java.io.Serializable;

import team.stray.bridgechat.bridge.Card;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chatMessage;
	private Card card;
	
	public Message() {
		this("", new Card(0, '0', 0));
	}
	
	public Message(String message, Card card){
		setChatMessage(message);
		setCard(card);
	}
	
	public String getChatMessage() {
		return chatMessage;
	}
	public boolean setChatMessage(String chatMessage) {
//		System.out.println(chatMessage);
//		if((this.chatMessage = chatMessage) != null){
		this.chatMessage = chatMessage;	
		return true;
//		}
//		else return false;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
}
