package team.stray.bridgechat.bridge;

import java.io.Serializable;

import team.stray.bridgechat.connect.Transmissible;

public class Card implements Comparable<Card>, Suit, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int value;	//2~14
	private char number;//card point (2~9,T,J,Q,K,A)
	private int suit;	//card suit  (1~4, defined in ISuit)
	
	public Card(){
		this(2, '2', 1);//2 club in default
	}
	
	public Card(int value, char number, int suit) {
		setValue(value);
		setNumber(number);
		setSuit(suit);
	}
	
	/**
	 * print Card's information
	 */
	public void printInfo(){
		System.out.println(getValue()+ "\t"+getNumber()+getSuit());
	}
	
	/**
	 * Used to compare card's order in Player: sortCardsInHand()
	 */
	@Override
	public int compareTo(Card o) {//sort from small to large by value
		int compareSuit = o.getSuit();
		int compareValue = o.getValue();
		int tmp = this.getSuit() - compareSuit;
		if(tmp != 0)
			return tmp;
		else
			return this.getValue() - compareValue;
	}
	
	/*getter and setter*/
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		if(value >= 1 && value <= 52){
			this.value = value;
		}
	}
	
	public char getNumber() {
		return number;
	}
	
	public void setNumber(char number) {
		if((number >= '2' && number <= '9') || number == 'T' || number == 'J' || number == 'Q' || number == 'K' || number == 'A'){
			this.number = number;
		}
	}
	
	public int getSuit() {
		return suit;
	}
	
	public void setSuit(int suit) {
		if(suit >= CLUBS && suit <= SPADES){
			this.suit = suit;
		}
	}
}
