package team.stray.bridgechat.online;

import java.util.Vector;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.chat.Chatroom;

public class Member {

	private String name;
	private Vector<Card> cardsInHand;// = new Vector<>();
	private Chatroom chatroom;
	
	public Member(String name, Chatroom chatroom) {
		setName(name);
		setChatroom(chatroom);
		setCardsInHand(new Vector<>());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Card> getCardsInHand() {
		return cardsInHand;
	}
	public void setCardsInHand(Vector<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}
	public Chatroom getChatroom() {
		return chatroom;
	}
	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}
}
