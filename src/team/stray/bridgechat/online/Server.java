package team.stray.bridgechat.online;

import team.stray.bridgechat.chat.ChatroomServer;

public class Server{

	ChatroomServer chatroomServer;
	GameServer gameServer;
	
	public Server(String name){
		chatroomServer = new ChatroomServer();
		gameServer = new GameServer(name);
	}
}

