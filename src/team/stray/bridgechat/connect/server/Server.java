 package team.stray.bridgechat.connect.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import com.sun.org.apache.bcel.internal.generic.LASTORE;

import team.stray.bridgechat.BridgeChat;
import team.stray.bridgechat.InfrastructureImpl;
import team.stray.bridgechat.InfrastructureImplTest;
import team.stray.bridgechat.bridge.GameClient;
import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.chat.ChatroomServer;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.client.Client;
import team.stray.bridgechat.connect.transmissible.*;

public class Server {

	private final ChatroomServer chatroomServer;
	private final GameServer gameServer;
	private final ConnectionServer connectionServer;
	private final Client client;
	private Map<String,Integer> nameToSeat;
	private Map<String,Integer> nameToTrick;
	private Map<String,Integer> nameToCardValue;
	
	private volatile boolean isWaitClientConnect;
	
	
	
	
	public Server(String name) {
		chatroomServer = new ChatroomServer();
		gameServer = new GameServer();
		connectionServer = new ConnectionServer();
		client = new Client(name, "127.0.0.1");
		client.connect();
		nameToSeat = new HashMap<String,Integer>();

		nameToTrick = new HashMap<String,Integer>();//trick
		nameToCardValue = new HashMap<String,Integer>();


		Thread thread = new Thread(new Runnable() {// Anonymous class
			public void run() {
				try {
					boolean isPrintOnce = false;
					int isreceivefourcard=0;
					isWaitClientConnect = true;
					Transmissible last = null;

					
					List<TransmissibleGameClient> players = new CopyOnWriteArrayList<>();
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
						}
						else if(last instanceof TransmissibleString) {
							//prefix = '@' for seat;'#' for call;'%'for out of the card
							String get= ((TransmissibleString) last).getTransmissibleString();
							if(get.length()!=0&&get.charAt(0)=='@'&&get.substring(2).equals(client.getGameClient().getName())){
								String seat =( get.substring(0, 1));
								client.getGameClient().setSeat(seat);
								nameToSeat.put(client.getGameClient().getName(),get.charAt(1)-'0');
								client.setMessageReceiveFromServer(last);
							}
						}
						else if(last instanceof TransmissibleCard&& GameServer.isGameOver ==false){
							isreceivefourcard +=1;
							if(isreceivefourcard==4){
								isreceivefourcard=0;
							}	
							switch (isreceivefourcard) {
							case 1:
								
								break;
							case 2:
								
								break;
							case 3:
	
								break;
							case 4:
	
								break;
								

							default:
								break;
							}
							Card cardget=((TransmissibleCard) last).getTransmissibleCard();
							if(cardget.getValue()>=1&&cardget.getValue()<= 52){
								client.getGameClient().setCard(cardget);
								nameToCardValue.put(client.getGameClient().getName(), cardget.getValue());
							}
		
						}
						
						if (GameServer.isPlayersReachFour == true && isPrintOnce == false) {
							System.out.println("room full");
							isPrintOnce = true;
							//isWaitClientConnect = false;
						}
						if(GameServer.isGameOver == true){
							isWaitClientConnect = false;
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
