 package team.stray.bridgechat.connect.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import team.stray.bridgechat.BridgeChat;
import team.stray.bridgechat.bridge.GameClient;
import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.chat.ChatroomServer;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.client.Client;
import team.stray.bridgechat.connect.transmissible.TransmissibleGameClient;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;

public class Server {

	private final ChatroomServer chatroomServer;
	private final GameServer gameServer;
	private final ConnectionServer connectionServer;
	private final Client client;
	private Map<String,Integer> nameToSeat;
	private Map<String,Integer> nameToTrick;
	
	private volatile boolean isWaitClientConnect;
	
	
	
	
	public Server(String name) {
		chatroomServer = new ChatroomServer();
		gameServer = new GameServer();
		connectionServer = new ConnectionServer();
		client = new Client(name, "127.0.0.1");
		client.connect();
		nameToSeat = new HashMap<String,Integer>();
		nameToTrick = new HashMap<String,Integer>();//¼[¼Æ

		Thread thread = new Thread(new Runnable() {// Anonymous class
			public void run() {
				try {
					isWaitClientConnect = true;
					Transmissible last = null;
					Vector<TransmissibleGameClient> players = new Vector<>();
					System.out.println("wait connection....");
					while (isWaitClientConnect) {
						last = client.getMessageReceiveFromServer();
						if (last instanceof TransmissibleGameClient && GameServer.isPlayersReachFour == false) {
							boolean isReceiveGameClientExist = false;
							/*check if GameClient which is received is exist*/
							for (TransmissibleGameClient i : players) {
								if (last.getTimestamp().equals(i.getTimestamp())) {
									isReceiveGameClientExist = true;
									break;
								}
							}
							if (isReceiveGameClientExist == false) {
								players.add((TransmissibleGameClient) last);
								gameServer.addPlayer(((TransmissibleGameClient) last).getTransmissibleGameClient());
								System.out.println("now players : "+players.size());
							}
						}else if(last instanceof TransmissibleString) {
							//prefix = '@' for seat;'#' for call;'%'for out of the card
							String get= ((TransmissibleString) last).getTransmissibleString();
							if(get.length()!=0&&get.charAt(0)=='@'&&get.substring(2).equals(client.getGameClient().getName())){
								String seat =( get.substring(0, 1));
								client.getGameClient().setSeat(seat);
								nameToSeat.put(client.getGameClient().getName(),get.charAt(1)-'0');
							}
						}
						
						if (GameServer.isPlayersReachFour == true) {
							System.out.println("room full");
							//isWaitClientConnect = false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	public String getIP(){
		return connectionServer.getIP();
	}

	/* getter and setter */

	public Client getClient() {
		return client;
	}
	
	public GameServer getGameServer() {
		return gameServer;
	}
}
