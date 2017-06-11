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
			case 1:
				infrastructure.openRoom();
				System.out.print("seat:");
				infrastructure.setSeat(Integer.parseInt(BridgeChat.scanner.nextLine()));
				break;
			case 2:
				infrastructure.setName("inf");
				infrastructure.setConnectionIP("127.0.0.1");
				infrastructure.connectRoom();
				System.out.print("seat:");
				infrastructure.setSeat(Integer.parseInt(BridgeChat.scanner.nextLine()));
				break;
			case 3:
				infrastructure.shuffleCard();
				infrastructure.dealCard();
				
				break;
			case 4:
				Vector<Card> cards = infrastructure.getCardsInHand();
				for (Card i : cards) {
					i.printInfo();
				}
				System.out.println("print cards" + cards.size());
				break;
			default:
				break;
			}
		}

	}

}
