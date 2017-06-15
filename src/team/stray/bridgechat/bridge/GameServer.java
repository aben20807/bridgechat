package team.stray.bridgechat.bridge;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameServer{

	public static List<Card> cards;
	public static boolean isPlayersReachFour = false;
	public static boolean isGameOver = false;
	public static boolean isRoundFull = false;
	
	public static Dealer dealer;

	public static List<GameClient> players = new CopyOnWriteArrayList<GameClient>();
	private List<Card> cardsInGame = new CopyOnWriteArrayList<Card>();
	public List<Card> cardsInRound = new CopyOnWriteArrayList<Card>();
	
	public GameServer() {
//		players.add(new GameClient("t"));//0
//		players.add(new GameClient("t"));//1
//		players.add(new GameClient("t"));//2
//		players.add(new GameClient("t"));//3
		cardsInGame.clear();
		cardsInRound.clear();
		players.clear(); //initial vector
		
		createCards();
//		printCards();
		dealer = Dealer.getInstnce();
		
	}
	
	public void addPlayer(GameClient g){
		this.players.add(g);
		if(this.players.size() >= 4)
			isPlayersReachFour = true;
		else
			isPlayersReachFour = false;
	}
	public void remainCardiInGame(Card e) {
		this.cardsInGame.add(e);
		if(this.cardsInGame.size() >= 52)
			isGameOver = true;
		else
			isGameOver = false;
	}

	public void addCardInRound(Card e) {
		this.cardsInRound.add(e);
		if(this.cardsInRound.size() >= 4)
			isRoundFull = true;
		else
			isRoundFull = false;
	}
	/**
	 * create cards to 52 different point and suit
	 */
	private void createCards(){
		cards = new CopyOnWriteArrayList<>();
		for(int i = 1; i <= 52; i++){
			int cardValueInEachSuit = (i-1)%13+2; //2~14 for sort in the same suit
			if(cardValueInEachSuit <= 9){
				cards.add(new Card(cardValueInEachSuit, (char)((i%13+1)+'0'), (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 10){
				cards.add(new Card(cardValueInEachSuit, 'T', (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 11){
				cards.add(new Card(cardValueInEachSuit, 'J', (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 12){
				cards.add(new Card(cardValueInEachSuit, 'Q', (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 13){
				cards.add(new Card(cardValueInEachSuit, 'K', (i-1)/13+1));
			}
			else if(cardValueInEachSuit == 14){
				cards.add(new Card(cardValueInEachSuit, 'A', (i-1)/13+1));
			}
		}
	}
	
	/**
	 * print cards' order
	 */
	public void printCards(){
		
		for(int i = 0; i < 52; i++){
			cards.get(i).printInfo();
		}
	}
	
	public void compareTrick(){
		Collections.sort(cardsInRound);
		isRoundFull = false;
	}
	
	/*getter and setter*/
	
	public List<GameClient> getPlayers() {
		return players;
	}
	
	public Dealer getDealer() {
		return dealer;
	}
}
