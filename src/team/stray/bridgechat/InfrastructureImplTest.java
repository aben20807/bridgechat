package team.stray.bridgechat;

import java.util.Vector;

import team.stray.bridgechat.bridge.Card;

public class InfrastructureImplTest {

	static Infrastructure infrastructure;
	public static void main(String[] args) {

		infrastructure = new InfrastructureImpl();
		System.out.println("InfrastructureImplTest....");
		while (true) {
			int mode = Integer.parseInt(BridgeChat.scanner.nextLine());
			switch (mode) {
			case 1://server create room
				infrastructure.setName("inf server");
				infrastructure.openRoom();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print("seat:");
				infrastructure.setSeat((BridgeChat.scanner.nextLine()));
				infrastructure.submitString("@"+infrastructure.getSeat()+infrastructure.getName());
				break;

			case 2://client connect
				infrastructure.setName("inf client");
				infrastructure.setConnectionIP("192.168.56.1");
				infrastructure.connectRoom();
				System.out.print("seat:");
				infrastructure.setSeat((BridgeChat.scanner.nextLine()));
				infrastructure.submitString("@"+infrastructure.getSeat()+infrastructure.getName());
				break;
			
			case 3://shuffle and deal card
				infrastructure.shuffleCard();
				infrastructure.dealCard();
				break;
			
			case 4://print card in hand
				Vector<Card> cards = infrastructure.getCardsInHand();
				for (Card i : cards) {
					i.printInfo();
				}
				System.out.println("print cards" + cards.size());
				break;
			
			case 5://submit string
				String msg = BridgeChat.scanner.nextLine();
				infrastructure.submitString(msg);
				break;
				
			case 6://get name
				System.out.println(infrastructure.getName());
				
			case 7://submit card
				infrastructure.submitCard(new Card());
				break;
				
			case 8://print last received message
				infrastructure.printMessageInfo();
				break;
			default:
				break;
			}
		}
	}
}
