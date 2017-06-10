package team.stray.bridgechat;

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
	void submitString(String string);
	void submitCard(Card card);
	void printMessageInfo();
	
	/*Bridge*/
	void chooseSeat();
	void shuffleCard();
	void dealCard();
	void cut();
	void call();
	void compareTrick();
}
