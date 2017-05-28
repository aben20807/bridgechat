package team.stray.bridgechat.online;

import java.util.ArrayList;
import java.util.Vector;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.Dealer;
import team.stray.bridgechat.chat.Chatroom;

public class GameServer extends Member{

	public static Vector<Card> cards;
	
	private Dealer dealer;
	private ArrayList<GameClient> players = new ArrayList<>();
	
	public GameServer(String name, Chatroom chatroom) {
		super(name, chatroom);
		
		players.add(new GameClient("t", chatroom));//0
		players.add(new GameClient("t", chatroom));//1
		players.add(new GameClient("t", chatroom));//2
		
		createCards();
//		printCards();
		dealer = Dealer.getInstnce();
		dealer.shuffle(cards);
//		printCards();
		dealer.deal(cards, this.getCardsInHand(), 
				players.get(0).getCardsInHand(), 
				players.get(1).getCardsInHand(), 
				players.get(2).getCardsInHand());
		
		this.sortCardsInHand();
		this.printCardsInHand();
		System.out.println("\n");
		
		players.get(0).sortCardsInHand();
		players.get(0).printCardsInHand();
		System.out.println("\n");
		
		players.get(1).sortCardsInHand();
		players.get(1).printCardsInHand();
		System.out.println("\n");
		
		players.get(2).sortCardsInHand();
		players.get(2).printCardsInHand();
	}

	private void createCards(){
		
		cards = new Vector<>();
		for(int i = 1; i <= 52; i++){
			
			int cardValueInEachSuit = (i-1)%13+2; //2~14
			
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
