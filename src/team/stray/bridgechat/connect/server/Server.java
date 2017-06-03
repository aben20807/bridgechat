package team.stray.bridgechat.connect.server;

import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.chat.ChatroomServer;

public class Server{

	ChatroomServer chatroomServer;
	GameServer gameServer;
	
	public Server(String name){
		chatroomServer = new ChatroomServer();
		gameServer = new GameServer(name);
	}
}

