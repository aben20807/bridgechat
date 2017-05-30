package team.stray.bridgechat.online;

import java.util.Vector;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.Dealer;
import team.stray.bridgechat.chat.IChatroom;

public class GameServer extends Player{

	public static Vector<Card> cards;
	
	private Dealer dealer;
	private Vector<GameClient> players = new Vector<>();
	
	public GameServer(String name) {
		super(name);
		players.add(new GameClient("t"));//0
		players.add(new GameClient("t"));//1
		players.add(new GameClient("t"));//2
		
		createCards();
//		printCards();
		dealer = Dealer.getInstnce();
		dealer.shuffle(cards);
//		printCards();
		dealer.deal(cards, this, 
				players.get(0), 
				players.get(1), 
				players.get(2));
		
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
