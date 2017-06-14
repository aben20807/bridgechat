package team.stray.bridgechat.bridge;

import java.util.Collections;
import java.util.List;

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
	public List<Card> shuffle(List<Card> cards){
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
	public void deal(List<Card> cards, 
			GameClient player1, GameClient player2, 
			GameClient player3, GameClient player4){
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
