package team.stray.bridgechat.online;

import java.util.Vector;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.Dealer;
import team.stray.bridgechat.chat.Chatroom;

public class GameServer extends Member{

	public static Vector<Card> cards;
	
	public GameServer(String name, Chatroom chatroom) {
		super(name, chatroom);
		
		createCards();
		printCards();
		Dealer.getInstnce().shuffle(cards);
		printCards();
	}

	private void createCards(){
		
		cards = new Vector<>();
		for(int i = 1; i <= 52; i++){
			
			int cardValueInEachSuit = (i-1)%13+2;//2~14
			
			if(cardValueInEachSuit <= 9){
				cards.add(new Card(i, (char)((i%13+1)+'0'), (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 10){
				cards.add(new Card(i, 'T', (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 11){
				cards.add(new Card(i, 'J', (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 12){
				cards.add(new Card(i, 'Q', (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 13){
				cards.add(new Card(i, 'K', (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 14){
				cards.add(new Card(i, 'A', (i-1)/13+1));
			}
		}
	}
	
	private void printCards(){
		
		for(int i = 0; i < 52; i++){
			cards.get(i).printInfo();
		}
	}
}
