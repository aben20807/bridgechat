package team.stray.bridgechat.bridge;

public class Card {

	private int key;
	private int value;
	private char number;
	private int suit;
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		if(key >= 1 && key <= 52){
			this.key = key;
		}
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		if(value >= 2 && value <= 14){
			this.value = value;
		}
	}
	public char getNumber() {
		return number;
	}
	public void setNumber(char number) {
		if((number >= '2' && number <= '9') || number == 'J' || number == 'Q' || number == 'K' || number == 'A'){
			this.number = number;
		}
	}
	public int getSuit() {
		return suit;
	}
	public void setSuit(int suit) {
		if(suit >= ISuit.clubs && suit <= ISuit.spades){
			this.suit = suit;
		}
	}
}
