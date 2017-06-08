package team.stray.bridgechat.connect.client;

import team.stray.bridgechat.BridgeChat;
import team.stray.bridgechat.bridge.GameClient;
import team.stray.bridgechat.chat.ChatroomClient;
import team.stray.bridgechat.connect.Connection;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.TransmissibleString;
import team.stray.bridgechat.connect.Timestamp;

public class Client {

	private final ChatroomClient chatroomClient;
	private final GameClient gameClient;
	private final ConnectionClient connectionClient;
	private Transmissible messageReceiveFromServer;

	public Client(String name, String ip) {
		chatroomClient = new ChatroomClient(name, ip);
		gameClient = new GameClient(name);
		connectionClient = new ConnectionClient("test", "127.0.0.1");
		Thread threadGetMessage = new Thread(new ThreadGetMessage());
		threadGetMessage.start();
	}

	public void connect() {
		connectionClient.doFunction(Connection.CONNECT);
		/*submit GameClient to join game*/
		connectionClient.setMessage(this.gameClient);
		connectionClient.doFunction(Connection.SUBMIT);
	}

	public void submitString() {
		String messageString = BridgeChat.scanner.nextLine();
		connectionClient.setMessage(messageString);
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
	}
}
