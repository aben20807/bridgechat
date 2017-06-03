package team.stray.bridgechat;

import java.util.Scanner;

import team.stray.bridgechat.chat.IChatroom;
import team.stray.bridgechat.chat.ChatroomClient;
import team.stray.bridgechat.chat.ChatroomServer;
import team.stray.bridgechat.online.Connection;
import team.stray.bridgechat.online.ConnectionClient;
import team.stray.bridgechat.online.ConnectionServer;
import team.stray.bridgechat.online.GameServer;
import team.stray.bridgechat.online.PhysicalClient;
import team.stray.bridgechat.online.PhysicalServer;

public class Game {

	public static Scanner scanner = new Scanner(System.in);

	private static IChatroom chatroom;
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
		((ConnectionClient) connection).doFunction(IChatroom.CONNECT);
	}

	private static void submit() {
		String message = scanner.nextLine();
//		System.out.println(message);
//		((PhysicalClient) chatroom).setMessage(message);
//		((PhysicalClient) chatroom).doFunction(IChatroom.SUBMIT);
		
//		((ChatroomClient) chatroom).setMessage(message);
//		((ChatroomClient) chatroom).doFunction(IChatroom.SUBMIT);
		
		((ConnectionClient) connection).setMessage(message);
		((ConnectionClient) connection).doFunction(IChatroom.SUBMIT);
	}

}
