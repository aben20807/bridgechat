package team.stray.bridgechat;

import java.util.Vector;

import team.stray.bridgechat.bridge.Card;

public interface Infrastructure {

	/*Server*/
	void openRoom();
	String getServerIP();
	
	/*Client*/
	void connectRoom();
	void setConnectionIP(String connectionIP);
	
	/*Server and Client*/
	void setName(String name);
	String getName();
	int getType();
	void setSeat(int seat);
	int getSeat();
	void submitString(String string);
	void submitCard(Card card);
	void printMessageInfo();
	
	/*Bridge*/
	void chooseSeat();
	void shuffleCard();
	void dealCard();
	Vector<Card> getCardsInHand();
	void cut();
	void call();
	void compareTrick();
}
