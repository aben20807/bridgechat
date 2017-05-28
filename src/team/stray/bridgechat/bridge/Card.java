package team.stray.bridgechat.bridge;

public class Card implements Comparable<Card>{

	private int value;	//can compare among 52 cards
	private char number;//card point (2~9,T,J,Q,K,A)
	private int suit;	//card suit  (1~4, defined in ISuit)
	
	public Card(int value, char number, int suit) {
		setValue(value);
		setNumber(number);
		setSuit(suit);
	}
	
	public void printInfo(){
		System.out.println(getValue()+ "\t"+getNumber()+getSuit());
	}
	

	@Override
	public int compareTo(Card o) {
		int compareValue = o.getValue();
		return this.getValue() - compareValue;
	}
	
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
		if(suit >= ISuit.clubs && suit <= ISuit.spades){
			this.suit = suit;
		}
	}

}
