package team.stray.bridgechat.bridge;

import java.util.Collections;
import java.util.Vector;

public class Dealer {

	public static Dealer instance = null;
	
	private Dealer(){};
	
	public static Dealer getInstnce(){
		if(instance == null){
			synchronized (Dealer.class) {
				if(instance == null){
					instance = new Dealer();
				}
			}
		}
		return instance;
	}
	
	public Vector<Card> shuffle(Vector<Card> cards){
		
		try {
			Collections.shuffle(cards);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		return cards;
	}
	
	public void deal(Vector<Card> cards, 
			Vector<Card> player1cards, Vector<Card> player2cards, 
			Vector<Card> player3cards, Vector<Card> player4cards){
		for(int i = 0; i < 52; i++){
			switch (i/13+1) {
			case 1:
				player1cards.add(cards.get(i));
				break;
			case 2:
				player2cards.add(cards.get(i));
				break;
			case 3:
				player3cards.add(cards.get(i));
				break;
			case 4:
				player4cards.add(cards.get(i));
				break;
			default:
				break;
			}
		}
	}
}
