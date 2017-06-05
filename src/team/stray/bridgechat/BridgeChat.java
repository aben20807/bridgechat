package team.stray.bridgechat;

import java.util.Scanner;

import team.stray.bridgechat.connect.Connection;
import team.stray.bridgechat.connect.client.Client;
import team.stray.bridgechat.connect.client.ConnectionClient;
import team.stray.bridgechat.connect.server.ConnectionServer;
import team.stray.bridgechat.connect.server.Server;
import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.chat.ChatroomClient;
import team.stray.bridgechat.chat.ChatroomServer;

public class BridgeChat {

	public static Scanner scanner = new Scanner(System.in);

//	private static Connection chatroom;
	private static Connection connection;
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
			printString();
			break;
		default:
			break;
		}}
	}

	private static void open() {
//		 GameServer gameserver = new GameServer("test");

		server = new Server("test server");
//		connection = new ConnectionServer();
		
		connect();
//		connection = new ConnectionClient("test", "127.0.0.1");
//		((ConnectionClient) connection).doFunction(Connection.CONNECT);
	}

	private static void connect() {

//		connection = new ConnectionClient("test", "127.0.0.1");
//		((ConnectionClient) connection).doFunction(Connection.CONNECT);
		client = new Client("test", "127.0.0.1");
		client.connect();
		
	}

	private static void submitString() {
//		String message = scanner.nextLine();
//
//		((ConnectionClient) connection).setMessage(message);
//		((ConnectionClient) connection).doFunction(Connection.SUBMIT);
		client.submitString();
	}

	private static void printString() {
//		String message = scanner.nextLine();
//
//		((ConnectionClient) connection).setMessage(message);
//		((ConnectionClient) connection).doFunction(Connection.SUBMIT);
		client.printReceiveString();
	}
}
