package team.stray.bridgechat;

import java.util.Scanner;
import java.util.Vector;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.Suit;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.client.Client;
import team.stray.bridgechat.connect.server.Server;
import team.stray.bridgechat.connect.transmissible.TransmissibleCard;
import team.stray.bridgechat.connect.transmissible.TransmissibleGameClient;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;

public class BridgeChat {

	public static Scanner scanner = new Scanner(System.in);

	private static Client client;
	private static Server server;
	private static int type = 0;
	
	public static final int SERVER = 1;
	public static final int CLIENT = 2;

	public static void main(String[] args) {
		try {
			Infrastructure infrastructure = new InfrastructureImpl();
			while (true) {// chat test
				int mode = Integer.parseInt(scanner.nextLine());

				switch (mode) {
				case 1:
					openRoom();
//					infrastructure.openRoom();
					break;
				case 2:
					connectRoom();
//					infrastructure.connectRoom();
					break;
				case 3:
					submitString();
//					infrastructure.submitString("aa");
					break;
				case 4:
					printMessageInfo();
//					infrastructure.printMessageInfo();
					break;
				default:
					break;
				}
//				Vector<Card> cardsInHand = new Vector<>();
//				cardsInHand.add(new Card(2, '2', Suit.SPADES));
//				cardsInHand.add(new Card(3, '3', Suit.HEARTS));
//				cardsInHand.add(new Card(4, '4', Suit.DIAMONDS));
//				cardsInHand.add(new Card(5, '5', Suit.CLUBS));
//				cardsInHand.add(new Card(6, '6', Suit.SPADES));
//				cardsInHand.add(new Card(7, '7', Suit.HEARTS));
//				cardsInHand.add(new Card(8, '8', Suit.DIAMONDS));
//				cardsInHand.add(new Card(9, '9', Suit.CLUBS));
//				cardsInHand.add(new Card(10, 'T', Suit.SPADES));
//				cardsInHand.add(new Card(11, 'J', Suit.HEARTS));
//				cardsInHand.add(new Card(12, 'Q', Suit.DIAMONDS));
//				cardsInHand.add(new Card(13, 'K', Suit.CLUBS));
//				cardsInHand.add(new Card(14, 'A', Suit.SPADES));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void openRoom() {
		server = new Server("test server");
		type = BridgeChat.SERVER;
		client = server.getClient();
	}

	private static void connectRoom() {
		client = new Client("test client", "127.0.0.1");
		client.connect();
		type = BridgeChat.CLIENT;
	}

	private static void submitString() {
		client.submitString();
	}

	private static void printMessageInfo() {
		Transmissible message = null;
		if(type == BridgeChat.SERVER){
			message = server.getClient().getMessageReceiveFromServer();
		}
		else if(type == BridgeChat.CLIENT){
			message = client.getMessageReceiveFromServer();
		}
		
		switch (message.getType()) {
		case Transmissible.STRING:
			System.out.println(message.getTimestamp());
			System.out.println(((TransmissibleString)message).getTransmissibleString());
			break;
		
		case Transmissible.GAMECLIENT:
			System.out.println(message.getTimestamp());
			System.out.println(((TransmissibleGameClient)message).getTransmissibleGameClient().getName());
			break;
		
		case Transmissible.CARD:
			System.out.println(message.getTimestamp());
			((TransmissibleCard)message).getTransmissibleCard().printInfo();;
			break;
		default:
			break;
		}
	}
}
