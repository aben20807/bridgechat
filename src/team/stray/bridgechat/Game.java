package team.stray.bridgechat;

import team.stray.bridgechat.chat.Chatroom;
import team.stray.bridgechat.online.GameServer;

public class Game {

	public static void main(String[] args) {

		GameServer gameserver = new GameServer("test", new Chatroom());
	}

}
