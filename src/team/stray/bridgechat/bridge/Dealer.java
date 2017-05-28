package team.stray.bridgechat.bridge;

import java.util.Collections;
import java.util.Vector;

import team.stray.bridgechat.online.Member;

public class Dealer {

	public static Dealer instance = null;
	
	private Dealer(){}
	
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
			Member player1, Member player2, 
			Member player3, Member player4){
		for(int i = 0; i < 52; i++){
			switch (i/13+1) {
			case 1:
				player1.addCardIntoHand(cards.get(i));
				break;
			case 2:
				player2.addCardIntoHand(cards.get(i));
				break;
			case 3:
				player3.addCardIntoHand(cards.get(i));
				break;
			case 4:
				player4.addCardIntoHand(cards.get(i));
				break;
			default:
				break;
			}
		}
	}
}
