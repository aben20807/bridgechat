package team.stray.bridgechat.connect.server;

import java.util.Vector;

import team.stray.bridgechat.BridgeChat;
import team.stray.bridgechat.bridge.GameClient;
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
		client = new Client(name, "127.0.0.1");
		client.connect();

		Thread thread = new Thread(new Runnable() {// Anonymous class
			public void run() {
				try {
					boolean running = true;
					Transmissible last = null;
					Vector<TransmissibleGameClient> players = new Vector<>();
					while (running) {
						last = client.getMessageReceiveFromServer();
						if (last instanceof TransmissibleGameClient && GameServer.isPlayersReachFour == false) {
							boolean exist = false;
							for (TransmissibleGameClient i : players) {
								if (last.getTimestamp().equals(i.getTimestamp())) {
									exist = true;
									break;
								}
							}
							if (exist == false) {
								players.add((TransmissibleGameClient) last);
								gameServer.addPlayer(((TransmissibleGameClient) last).getTransmissibleGameClient());
								System.out.println(players.size());
							}
						}
						if (GameServer.isPlayersReachFour == true) {
							System.out.println("room full");
							running = false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	/* getter and setter */

	public Client getClient() {
		return client;
	}
}
