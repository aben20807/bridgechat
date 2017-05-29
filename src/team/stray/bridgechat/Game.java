package team.stray.bridgechat;

import java.util.Scanner;

import team.stray.bridgechat.chat.IChatroom;
import team.stray.bridgechat.chat.ChatroomClient;
import team.stray.bridgechat.chat.ChatroomServer;
import team.stray.bridgechat.online.GameServer;

public class Game {

	public static Scanner scanner = new Scanner(System.in);

	private static IChatroom chatroom;

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
		// GameServer gameserver = new GameServer("test", new Chatroom());
		chatroom = new ChatroomServer();
		System.out.println(((ChatroomServer)chatroom).getIP());
	}

	private static void connect() {
		chatroom = new ChatroomClient("test", "127.0.0.1");
		((ChatroomClient) chatroom).doFunction(IChatroom.CONNECT);
	}

	private static void submit() {
		String message = scanner.nextLine();
//		System.out.println(message);
		((ChatroomClient) chatroom).setMessage(message);
		((ChatroomClient) chatroom).doFunction(IChatroom.SUBMIT);
	}

}
