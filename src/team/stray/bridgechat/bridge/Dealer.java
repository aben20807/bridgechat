package team.stray.bridgechat.bridge;

import java.util.Collections;
import java.util.Vector;

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
	
	/**
	 * Use Collections.shuffle to shuffle cards
	 * @param cards: before shuffling
	 * @return cards after shuffling
	 */
	public Vector<Card> shuffle(Vector<Card> cards){
		try {
			Collections.shuffle(cards);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		return cards;
	}
	
	/**
	 * deal cards to four Player
	 * @param cards
	 * @param player1
	 * @param player2
	 * @param player3
	 * @param player4
	 */
	public void deal(Vector<Card> cards, 
			Player player1, Player player2, 
			Player player3, Player player4){
		for(int i = 0; i < 52; i++){
			switch (i%4+1) {
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
