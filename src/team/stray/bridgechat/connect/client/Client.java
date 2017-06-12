package team.stray.bridgechat.connect.client;

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

	public Client(String name, String ip) {
		chatroomClient = new ChatroomClient(name, ip);
		gameClient = new GameClient(name);
		connectionClient = new ConnectionClient(name, ip);
		connectOrder = connectOrderCounter;
		connectOrderCounter++;
		Thread threadGetMessage = new Thread(new ThreadGetMessage());
		threadGetMessage.start();
	}

	public void connect() {
		connectionClient.doFunction(Connection.CONNECT);
		/*submit GameClient to join game*/
		connectionClient.setMessage(this.gameClient);
		connectionClient.doFunction(Connection.SUBMIT);
		//System.out.println("send GameClient "+messageReceiveFromServer.getTimestamp());
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
			System.out.println("receive string : "+ t1);
			System.out.println("receive time   : "+ t2);
		} else {
			System.out.println("client receive : null");
		}
		
	}
	
	public void printReceiveGameClient(){
		if (messageReceiveFromServer != null) {
			String t2 = messageReceiveFromServer.getTimestamp();
			System.out.println("receive time   : "+ t2);
		} else {
			System.out.println("client receive : null");
		}
	}

	class ThreadGetMessage implements Runnable {
		public void run() {
			while (true) {
				Transmissible tmp = connectionClient.getReceiveMessage();
				if (tmp != null) {
					setMessageReceiveFromServer(tmp);
				}
			}
		}
	}

	/*getter and setter*/
	
	public Transmissible getMessageReceiveFromServer() {
		return messageReceiveFromServer;
	}

	public void setMessageReceiveFromServer(Transmissible message) {
		this.messageReceiveFromServer = message;
//		if(message instanceof TransmissibleCard){
//			System.out.print(((TransmissibleCard) message).getMember()+" : ");
//			((TransmissibleCard) message).getTransmissibleCard().printInfo();
//		}
		
		if(message instanceof TransmissibleCard &&
				gameClient.getCardsInHand().size() < 13 &&
				((TransmissibleCard) message).getMember() ==Integer.parseInt(getGameClient().getSeat().substring(0,1))){
			Card receive = ((TransmissibleCard) this.messageReceiveFromServer).getTransmissibleCard();
			boolean isCardExist = false;
			for(Card i : gameClient.getCardsInHand()){
				if(receive.equals(i)){
					isCardExist = true;
				}
			}
			if(isCardExist == false){
//				System.out.println(((TransmissibleCard) message).getMember());
//				System.out.println(getConnectOrder());
				gameClient.addCardIntoHand(((TransmissibleCard) this.messageReceiveFromServer).getTransmissibleCard());
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
