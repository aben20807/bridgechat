package team.stray.bridgechat;

import java.util.Scanner;

import team.stray.bridgechat.connect.client.Client;
import team.stray.bridgechat.connect.server.Server;

public class BridgeChat {

	public static Scanner scanner = new Scanner(System.in);

	private static Client client;
	private static Server server;
	public static void main(String[] args) {

		while(true){//chat test
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
//			printString();
			printGameClient();
			break;
		default:
			break;
		}}
	}

	private static void open() {
		server = new Server("test server");
		//connect();
		client = server.getClient();
	}

	private static void connect() {
		client = new Client("test", "127.0.0.1");
		client.connect();
	}

	private static void submitString() {
		client.submitString();
	}

	private static void printString() {
		client.printReceiveString();
	}
	
	private static void printGameClient() {
		client.printReceiveGameClient();
	}
}
