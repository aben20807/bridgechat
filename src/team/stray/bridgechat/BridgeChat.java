package team.stray.bridgechat;

import java.util.Scanner;

import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.TransmissibleCard;
import team.stray.bridgechat.connect.TransmissibleGameClient;
import team.stray.bridgechat.connect.TransmissibleString;
import team.stray.bridgechat.connect.client.Client;
import team.stray.bridgechat.connect.server.Server;

public class BridgeChat {

	public static Scanner scanner = new Scanner(System.in);

	private static Client client;
	private static Server server;
	private static int type = 0;
	
	public static final int SERVER = 1;
	public static final int CLIENT = 2;

	public static void main(String[] args) {
		try {
			while (true) {// chat test
				int mode = Integer.parseInt(scanner.nextLine());

				switch (mode) {
				case 1:
					open();
					break;
				case 2:
					connect();
					break;
				case 3:
					submitString();
					break;
				case 4:
					printMessageInfo();
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void open() {
		server = new Server("test server");
		type = BridgeChat.SERVER;
		// connect();
		// client = server.getClient();
	}

	private static void connect() {
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
