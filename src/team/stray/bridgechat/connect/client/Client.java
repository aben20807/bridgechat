package team.stray.bridgechat.connect.client;

import java.util.HashMap;

import team.stray.bridgechat.BridgeChat;
import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.GameClient;
import team.stray.bridgechat.chat.ChatroomClient;
import team.stray.bridgechat.connect.Connection;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.transmissible.TransmissibleCard;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;
import team.stray.bridgechat.connect.Timestamp;

public class Client {

	private static int connectOrderCounter = 0;
	private final ChatroomClient chatroomClient;
	private final GameClient gameClient;

	private final int connectOrder;
	private final ConnectionClient connectionClient;
	private Transmissible messageReceiveFromServer;
	public static HashMap<String, Integer> nameToSeat;
	public static HashMap<Integer, String> seatToName;

	public Client(String name, String ip) {
		chatroomClient = new ChatroomClient(name, ip);
		gameClient = new GameClient(name);
		connectionClient = new ConnectionClient(name, ip);
		connectOrder = connectOrderCounter;
		connectOrderCounter++;
		nameToSeat = new HashMap<String, Integer>();
		seatToName = new HashMap<Integer, String>();
		Thread threadGetMessage = new Thread(new ThreadGetMessage());
		threadGetMessage.start();
	}

	public void connect() {
		connectionClient.doFunction(Connection.CONNECT);
		/* submit GameClient to join game */
		connectionClient.setMessage(this.gameClient);
		connectionClient.doFunction(Connection.SUBMIT);
		// System.out.println("send GameClient
		// "+messageReceiveFromServer.getTimestamp());
	}

	public void submitString() {
		String messageString = BridgeChat.scanner.nextLine();
		connectionClient.setMessage(messageString);
		connectionClient.doFunction(Connection.SUBMIT);
	}

	public void submitString(String string) {
		connectionClient.setMessage(string);
		connectionClient.doFunction(Connection.SUBMIT);
	}

	public void submitCard(Card card) {
		connectionClient.setMessage(card);
		connectionClient.doFunction(Connection.SUBMIT);
	}

	public void submitCard(int member, Card card) {
		connectionClient.setMessage(member, card);
		connectionClient.doFunction(Connection.SUBMIT);
	}

	public void printReceiveString() {
		if (messageReceiveFromServer != null) {
			String t1 = ((TransmissibleString) messageReceiveFromServer).getTransmissibleString();
			String t2 = messageReceiveFromServer.getTimestamp();
			System.out.println("receive string : " + t1);
			System.out.println("receive time   : " + t2);
		} else {
			System.out.println("client receive : null");
		}

	}

	public void printReceiveGameClient() {
		if (messageReceiveFromServer != null) {
			String t2 = messageReceiveFromServer.getTimestamp();
			System.out.println("receive time   : " + t2);
		} else {
			System.out.println("client receive : null");
		}
	}

	class ThreadGetMessage implements Runnable {
		public void run() {
			while (true) {
				Transmissible last = connectionClient.getReceiveMessage();
				if (last != null) {
					setMessageReceiveFromServer(last);
					if (last instanceof TransmissibleString) {
						// prefix = '@' for seat;'#' for call;'%'for out of the
						// card
						String get = ((TransmissibleString) last).getTransmissibleString();
						// boolean isReceiveCardInRoundFull = false;
						if (get.length() != 0 && get.charAt(0) == '@') {
							String seat = (get.substring(0, 1));
							getGameClient().setSeat(seat);
							nameToSeat.put(get.substring(2), get.charAt(1) - '0');
							seatToName.put(get.charAt(1) - '0', get.substring(2));
//							 for (Object key : seatToName.keySet()) {
//							 System.out.println(key + " : " +
//							 seatToName.get(key));
//							 }
						}
					}
				}
			}
		}
	}

	/* getter and setter */

	public Transmissible getMessageReceiveFromServer() {
		return messageReceiveFromServer;
	}

	public void setMessageReceiveFromServer(Transmissible message) {
		this.messageReceiveFromServer = message;
		// if(message instanceof TransmissibleCard){
		// System.out.print(((TransmissibleCard) message).getMember()+" : ");
		// ((TransmissibleCard) message).getTransmissibleCard().printInfo();
		// }

		if (message instanceof TransmissibleCard && gameClient.getCardsInHand().size() < 13
				&& getGameClient().getSeat().length() >= 2 && getGameClient().getSeat().substring(0, 1).equals("@")
				&& ((TransmissibleCard) message).getMember() == Integer
						.parseInt(getGameClient().getSeat().substring(1, 2))) {
			Card receive = ((TransmissibleCard) this.messageReceiveFromServer).getTransmissibleCard();
			boolean isCardExist = false;
			for (Card i : gameClient.getCardsInHand()) {
				if (receive.equals(i)) {
					isCardExist = true;
				}
			}
			if(isCardExist == false){
//				System.out.println(((TransmissibleCard) message).getMember());
//				System.out.println(getConnectOrder());
				Card tmpCard = ((TransmissibleCard) this.messageReceiveFromServer).getTransmissibleCard();
				System.out.print("receive : ");
				tmpCard.printInfo();
				System.out.flush();
				gameClient.addCardIntoHand(tmpCard);
			}
		}
	}

	public GameClient getGameClient() {
		return gameClient;
	}

	public int getConnectOrder() {
		return connectOrder;
	}
}
