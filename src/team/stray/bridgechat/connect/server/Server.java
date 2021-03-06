package team.stray.bridgechat.connect.server;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
	// public static HashMap<String,Integer> nameToSeat;
	// public static HashMap<Integer,String> seatToName;
	// private Map<String,Integer> nameToTrick;
	private HashMap<Card, String> cardToName;

	private volatile boolean isWaitClientConnect;

	public Server(String name) {
		chatroomServer = new ChatroomServer();
		gameServer = new GameServer();
		connectionServer = new ConnectionServer();
		client = new Client(name, "127.0.0.1");
		client.connect();
		// nameToSeat = new HashMap<String,Integer>();
		// seatToName = new HashMap<Integer,String>();
		// nameToTrick = new HashMap<String,Integer>();//trick
		cardToName = new HashMap<Card, String>();

		Thread thread = new Thread(new Runnable() {// Anonymous class
			public void run() {
				try {
					boolean isPrintOnce = false;
					// int isreceivefourcard=0;
					isWaitClientConnect = true;
					Transmissible last = null;

					List<TransmissibleGameClient> players = new CopyOnWriteArrayList<>();
					List<TransmissibleCard> cardsInRound = new CopyOnWriteArrayList<>();

					System.out.println("wait connection....");
					while (isWaitClientConnect) {
						last = client.getMessageReceiveFromServer();
						if (last instanceof TransmissibleGameClient && GameServer.isPlayersReachFour == false) {
							boolean isReceiveGameClientExist = false;
							/* check if GameClient which is received is exist */
							for (TransmissibleGameClient i : players) {
								if (last.getTimestamp().equals(i.getTimestamp())) {
									isReceiveGameClientExist = true;
									break;
								}
							}
							if (isReceiveGameClientExist == false) {
								players.add((TransmissibleGameClient) last);
								gameServer.addPlayer(((TransmissibleGameClient) last).getTransmissibleGameClient());
								System.out.println("now players : " + players.size());
							}
						} else if (last instanceof TransmissibleString) {
							// prefix = '@' for seat;'#' for call;'%'for out of
							// the card
							String get = ((TransmissibleString) last).getTransmissibleString();
							// boolean isReceiveCardInRoundFull = false;
							if (get.length() != 0 && get.charAt(0) == '@') {
								String seat = (get.substring(0, 1));
								client.getGameClient().setSeat(seat);
								// nameToSeat.put(get.substring(2),
								// get.charAt(1)-'0');
								// seatToName.put(get.charAt(1)-'0',
								// get.substring(2));
								// for (Object key : seatToName.keySet()) {
								// System.out.println(key + " : " +
								// seatToName.get(key));
								// }
								client.setMessageReceiveFromServer(last);
							}
							// get card from client
							else if (get.length() != 0 && get.charAt(0) == '%') {
								boolean isReceiveCardExistInRound = false;
								if (cardsInRound.size() != 0) {
									for (TransmissibleCard i : cardsInRound) {
										// System.out.println(last.getTimestamp()+","+i.getTimestamp());
										if (last.getTimestamp() != null
												&& last.getTimestamp().equals(i.getTimestamp())) {
											isReceiveCardExistInRound = true;
											break;
										}
									}
								}
								if (last.getTimestamp() != null && isReceiveCardExistInRound == false
										&& cardsInRound.size() <= 4) {
									char cardNumber = get.charAt(2);
									int cardSuit = get.charAt(3) - '0';
									int cardValue = Integer.parseInt(get.substring(4));
									Card cardget = new Card(cardValue, cardNumber, cardSuit);

									TransmissibleCard TCardget = new TransmissibleCard();
									TCardget.setTransmissibleCard(cardget);
									TCardget.setTimestamp(last.getTimestamp());// OAO
									cardsInRound.add(TCardget);

									System.out.println("card number is" + cardsInRound.size());

									gameServer.addCardInRound(cardget);
									gameServer.remainCardiInGame(cardget);
									cardToName.put(cardget, client.getGameClient().getName());
									if (cardsInRound.size() == 4) {
										gameServer.compareTrick();
										;
										// get who is big now (name)
										TransmissibleString whoIsBig = new TransmissibleString();
										whoIsBig.setTransmissibleString(
												cardToName.get(gameServer.cardsInRound.get(cardsInRound.size() - 1)));
										// System.out.println("who is
										// big"+whoIsBig.getTransmissibleString());
										System.out.println("biggest card "
												+ gameServer.cardsInRound.get(cardsInRound.size() - 1).getCardInfo());
										client.setMessageReceiveFromServer(whoIsBig);
										gameServer.cardsInRound.clear();
										cardToName.clear();
									}
								}
							}
						}
						// else if(last instanceof TransmissibleCard&&
						// GameServer.isGameOver ==false){
						// isreceivefourcard +=1;
						// if(isreceivefourcard==4){
						//
						// isreceivefourcard=0;
						// }
						// Card cardget=((TransmissibleCard)
						// last).getTransmissibleCard();
						// if(cardget.getValue()>=1&&cardget.getValue()<= 52){
						// client.getGameClient().setCard(cardget);
						// nameToCardValue.put(client.getGameClient().getName(),
						// cardget.getValue());
						// }
						//
						// }

						if (GameServer.isPlayersReachFour == true && isPrintOnce == false) {
							System.out.println("room full");
							client.submitString("ROOM_FULL");
							isPrintOnce = true;
							// isWaitClientConnect = false;
						}
						if (GameServer.isGameOver == true) {
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
	
	/* getter and setter */
	
	public String getIP() {
		return connectionServer.getIP();
	}

	public Client getClient() {
		return client;
	}

	public GameServer getGameServer() {
		return gameServer;
	}

}
