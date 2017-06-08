package team.stray.bridgechat.bridge;

import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;

public class GameClient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Vector<Card> cardsInHand;
	private int points; //used to cut
	private int seat;
	
	public GameClient(String name) {
		setName(name);
		setCardsInHand(new Vector<>());
		setPoints(0);//initial point at object constructing
	}
	
	public void chooseSeat(){
		
	}
	
	public void printCardsInHand(){
		System.out.println("Points : " + getPoints());
		for(int i = 0; i < 13; i++){
			cardsInHand.get(i).printInfo();
		}
	}
	
	public void sortCardsInHand(){
		Collections.sort(cardsInHand);
	}
	
	/**
	 * Used in Dealer: deal()
	 * By the way count point in hand
	 * @param card: card which will be added in hand
	 */
	public void addCardIntoHand(Card card) {
		if(card.getNumber() == 'A'){
			this.setPoints(this.getPoints() + 4);
		}
		else if(card.getNumber() == 'K'){
			this.setPoints(this.getPoints() + 3);
		}
		else if(card.getNumber() == 'Q'){
			this.setPoints(this.getPoints() + 2);
		}
		else if(card.getNumber() == 'J'){
			this.setPoints(this.getPoints() + 1);
		}
		this.cardsInHand.add(card);
	}
	
	/*getter and setter*/
	
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
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getSeat() {
		return seat;
	}
	
	public void setSeat(int seat) {
		this.seat = seat;
	}
}
