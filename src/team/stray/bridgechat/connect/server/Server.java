package team.stray.bridgechat.connect.server;

import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.chat.ChatroomServer;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.TransmissibleGameClient;
import team.stray.bridgechat.connect.client.Client;

public class Server {

	private final ChatroomServer chatroomServer;
	private final GameServer gameServer;
	private final ConnectionServer connectionServer;
	private final Client client;

	public Server(String name) {
		chatroomServer = new ChatroomServer();
		gameServer = new GameServer();
		connectionServer = new ConnectionServer();
		client = new Client("test", "127.0.0.1");
		client.connect();

//		Thread thread = new Thread(new Runnable() {// Anonymous class
//			public void run() {
//				Transmissible tmp = null;
//				int tmpc = 0;
//				for(int i = 0; i < 20; i++) {
//					if (tmp != null && !tmp.getTimestamp().equals(client.getMessageReceiveFromServer().getTimestamp())) {
//						tmp = client.getMessageReceiveFromServer();
//
//						if (tmp instanceof TransmissibleGameClient && GameServer.isPlayersReachFour == false) {
//							gameServer.addPlayer(((TransmissibleGameClient) tmp).getTransmissibleGameClient());
//							tmpc++;
//						} else if (GameServer.isPlayersReachFour == true) {
//							System.out.println("room full");
//						} else {
//							System.out.println(tmpc);
//						}
//					}
//				}
//			}
//		});
//		thread.start();

	}

	/* getter and setter */

	public Client getClient() {
		return client;
	}
}
