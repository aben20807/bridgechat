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
				Transmissible last = null;
				int tmpc = 0;
				Vector<TransmissibleGameClient> players = new Vector<>();
				while (true) {
					// for(int ii = 0; ii < 20; ii++){

					last = client.getMessageReceiveFromServer();
//					System.out.println(last.getTimestamp());
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
						break;
					}
				}
				// {
				//// System.out.println(tmp == null);
				// if(last != null){
				//// last = client.getMessageReceiveFromServer();
				// if (last instanceof TransmissibleGameClient &&
				// GameServer.isPlayersReachFour == false ){// &&
				//// !last.getTimestamp().equals(client.getMessageReceiveFromServer().getTimestamp()))
				// {
				// boolean exist = false;
				// for(TransmissibleGameClient i : players){
				// if(last.getTimestamp().equals(i.getTimestamp())){
				// exist = true;
				//
				// }
				// }
				// if(exist == false){
				// System.out.println("c:"+ players.size());
				// gameServer.addPlayer(((TransmissibleGameClient)
				// last).getTransmissibleGameClient());
				// players.add((TransmissibleGameClient) last);
				// tmpc++;
				// System.out.println(tmpc);
				// }
				//// BridgeChat.printGameClient();
				// last = client.getMessageReceiveFromServer();
				// }
				// else{
				//// System.out.println(last.getTimestamp().equals(client.getMessageReceiveFromServer().getTimestamp()));
				//
				// last = client.getMessageReceiveFromServer();
				// }
				// }
				// else{
				// last = client.getMessageReceiveFromServer();
				// }
				// if (tmp != null &&
				// !tmp.getTimestamp().equals(client.getMessageReceiveFromServer().getTimestamp()))
				// {
				// tmp = client.getMessageReceiveFromServer();
				//
				// if (tmp instanceof TransmissibleGameClient &&
				// GameServer.isPlayersReachFour == false) {
				// gameServer.addPlayer(((TransmissibleGameClient)
				// tmp).getTransmissibleGameClient());
				// tmpc++;
				// } else if (GameServer.isPlayersReachFour == true) {
				// System.out.println("room full");
				// } else {
				// System.out.println(tmpc);
				// }
				// }
				// }
			}
		});
		thread.start();

	}

	/* getter and setter */

	public Client getClient() {
		return client;
	}
}
