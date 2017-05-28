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
		Collections.shuffle(cards);
		return cards;
	}
	
	public Vector<Card> deal(Vector<Card> cards){
		
		return cards;
	}
}
