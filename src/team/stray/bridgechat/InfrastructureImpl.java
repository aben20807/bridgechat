package team.stray.bridgechat;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.client.Client;
import team.stray.bridgechat.connect.server.Server;
import team.stray.bridgechat.connect.transmissible.TransmissibleCard;
import team.stray.bridgechat.connect.transmissible.TransmissibleGameClient;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;

public class InfrastructureImpl implements Infrastructure{

	private static Client client;
	private static Server server;
	private static int type = 0;
	
	private String name;
	private String connectionIP;
	
	public static final int SERVER = 1;
	public static final int CLIENT = 2;
	
	@Override
	public void openRoom() {
		server = new Server(this.name);
		type = BridgeChat.SERVER;
	}

	@Override
	public String getServerIP() {
		return server.getIP();
	}

	@Override
	public void connectRoom() {
		client = new Client(this.name, this.connectionIP);
		client.connect();
		type = BridgeChat.CLIENT;
	}

	@Override
	public void setConnectionIP(String connectionIP) {
		this.connectionIP = connectionIP;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void submitString(String string) {
		client.submitString(string);
	}

	@Override
	public void submitCard(Card card) {
		client.submitCard(card);
	}

	@Override
	public void printMessageInfo() {
		Transmissible message = null;
		if(type == BridgeChat.SERVER){
			message = server.getClient().getMessageReceiveFromServer();
		}
		else if(type == BridgeChat.CLIENT){
			message = client.getMessageReceiveFromServer();
		}
		
		switch (message.getType()) {
		case Transmissible.STRING:
			System.out.println(message.getTimestamp());
			System.out.println(((TransmissibleString)message).getTransmissibleString());
			break;
		
		case Transmissible.GAMECLIENT:
			System.out.println(message.getTimestamp());
			System.out.println(((TransmissibleGameClient)message).getTransmissibleGameClient().getName());
			break;
		
		case Transmissible.CARD:
			System.out.println(message.getTimestamp());
			((TransmissibleCard)message).getTransmissibleCard().printInfo();;
			break;
		default:
			break;
		}
	}

	@Override
	public void chooseSeat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shuffleCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dealCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void call() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void compareTrick() {
		// TODO Auto-generated method stub
		
	}

}
