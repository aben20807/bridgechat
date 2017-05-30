package team.stray.bridgechat.online;

import team.stray.bridgechat.chat.ChatroomClient;

public class Client{
	
	ChatroomClient chatroomClient;
	GameClient gameClient;
	
	public Client(String name, String ip) {
		chatroomClient = new ChatroomClient(name, ip);
		gameClient = new GameClient(name);
	}
}
