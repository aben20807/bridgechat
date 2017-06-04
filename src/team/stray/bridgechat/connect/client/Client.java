package team.stray.bridgechat.connect.client;

import team.stray.bridgechat.BridgeChat;
import team.stray.bridgechat.bridge.GameClient;
import team.stray.bridgechat.chat.ChatroomClient;
import team.stray.bridgechat.connect.Connection;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.TransmissibleString;
import team.stray.bridgechat.connect.TransmitTimestamp;

public class Client {

	private ChatroomClient chatroomClient;
	private GameClient gameClient;
	private ConnectionClient connectionClient;
	private Transmissible message;

	public Client(String name, String ip) {
		chatroomClient = new ChatroomClient(name, ip);
		gameClient = new GameClient(name);
		connectionClient = new ConnectionClient("test", "127.0.0.1");
		Thread threadGetMessage = new Thread(new ThreadGetMessage());
		threadGetMessage.start();
	}

	public void connect() {
		connectionClient.doFunction(Connection.CONNECT);
	}

	public void submitString() {
		String message = BridgeChat.scanner.nextLine();

		connectionClient.setMessage(message);
		connectionClient.doFunction(Connection.SUBMIT);
	}

	public void printReceiveString() {
		if (message != null) {
			String t1 = ((TransmissibleString) message).getTransmissibleString();
			String t2 = ((TransmitTimestamp) message).getSendTimestamp();
			System.out.println("receive string : "+ t1);
			System.out.println("receive time   : "+ t2);
		} else {
			System.out.println("client receive : null");
		}
		
	}

	class ThreadGetMessage implements Runnable {
		public void run() {
			Transmissible tmp;
			while (true) {
				tmp = connectionClient.getReceiveMessage();
				if (tmp != null) {
					setMessage(tmp);
				}
			}
		}
	}

	public Transmissible getMessage() {
		return message;
	}

	public void setMessage(Transmissible message) {
		this.message = message;
	}
}
