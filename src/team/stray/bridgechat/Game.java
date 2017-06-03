package team.stray.bridgechat;

import java.util.Scanner;

import team.stray.bridgechat.connect.Connection;
import team.stray.bridgechat.connect.client.ConnectionClient;
import team.stray.bridgechat.connect.server.ConnectionServer;
import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.chat.ChatroomClient;
import team.stray.bridgechat.chat.ChatroomServer;

public class Game {

	public static Scanner scanner = new Scanner(System.in);

//	private static Connection chatroom;
	private static Connection connection;
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
			submit();
		default:
			break;
		}}
	}

	private static void open() {
//		 GameServer gameserver = new GameServer("test");
//		chatroom = new PhysicalServer();
		
//		chatroom = new ChatroomServer();
		
		connection = new ConnectionServer();
	}

	private static void connect() {
//		chatroom = new PhysicalClient("test", "127.0.0.1");
//		((PhysicalClient) chatroom).doFunction(IChatroom.CONNECT);
		
//		chatroom = new ChatroomClient("test", "127.0.0.1");
//		((ChatroomClient) chatroom).doFunction(IChatroom.CONNECT);
		
		connection = new ConnectionClient("test", "127.0.0.1");
		((ConnectionClient) connection).doFunction(Connection.CONNECT);
	}

	private static void submit() {
		String message = scanner.nextLine();
//		System.out.println(message);
//		((PhysicalClient) chatroom).setMessage(message);
//		((PhysicalClient) chatroom).doFunction(IChatroom.SUBMIT);
		
//		((ChatroomClient) chatroom).setMessage(message);
//		((ChatroomClient) chatroom).doFunction(IChatroom.SUBMIT);
		
		((ConnectionClient) connection).setMessage(message);
		((ConnectionClient) connection).doFunction(Connection.SUBMIT);
	}

}
