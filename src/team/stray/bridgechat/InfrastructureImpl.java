package team.stray.bridgechat;

import java.util.Vector;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.client.Client;
import team.stray.bridgechat.connect.server.Server;
import team.stray.bridgechat.connect.transmissible.TransmissibleCard;
import team.stray.bridgechat.connect.transmissible.TransmissibleGameClient;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;

public class InfrastructureImpl implements Infrastructure {

	private Client client;
	private Server server;
	private int type = 0;

	private String name;
	private String connectionIP;
	
	@Override
	public void openRoom() {
		this.server = new Server(this.name);
		type = Infrastructure.SERVER;
		this.client = this.server.getClient();
	}

	@Override
	public String getServerIP() {
		if (type == Infrastructure.SERVER) {
			return server.getIP();
		} else {
			return "";
		}
	}

	@Override
	public void connectRoom() {
		if (this.name != null && this.connectionIP != null) {
			this.client = new Client(this.name, this.connectionIP);
			this.client.connect();
			type = Infrastructure.CLIENT;
		} else {
			System.err.println("name or connect ip not set!");
		}
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
	public String getName() {
		if (client != null) {
			return client.getGameClient().getName();
		} else {
			return null;
		}
	}

	@Override
	public void submitString(String string) {
		if (client != null) {
			this.client.submitString(string);
			
		}
	}

	@Override
	public void submitCard(Card card) {
		if (client != null) {
			this.client.submitCard(card);
		}
	}

	@Override
	public void printMessageInfo() {
		Transmissible message = null;
		if (type == Infrastructure.SERVER) {
			message = this.server.getClient().getMessageReceiveFromServer();
		} else if (type == Infrastructure.CLIENT) {
			message = this.client.getMessageReceiveFromServer();
		}

		switch (message.getType()) {
		case Transmissible.STRING:
			System.out.println(message.getTimestamp());
			System.out.println(((TransmissibleString) message).getTransmissibleString());
			break;

		case Transmissible.GAMECLIENT:
			System.out.println(message.getTimestamp());
			System.out.println(((TransmissibleGameClient) message).getTransmissibleGameClient().getName());
			break;

		case Transmissible.CARD:
			System.out.println(message.getTimestamp());
			((TransmissibleCard) message).getTransmissibleCard().printInfo();
			;
			break;
		default:
			break;
		}
	}

	@Override
	public void chooseSeat() {

	}

	@Override
	public void shuffleCard() {
		GameServer.cards = server.getGameServer().getDealer().shuffle(GameServer.cards);
		System.out.println("shuffle finished");
	}

	@Override
	public void dealCard() {
		this.server.getGameServer().getDealer().deal(GameServer.cards, server.getGameServer().getPlayers().get(0),
				server.getGameServer().getPlayers().get(1), server.getGameServer().getPlayers().get(2),
				server.getGameServer().getPlayers().get(3));
		for (int i = 0; i < 4; i++) {
			this.server.getGameServer().getPlayers().get(i).sortCardsInHand();// sort
																				// cards
																				// in
																				// hand
			if (i == 0) {// server cards
				for (Card c : this.server.getGameServer().getPlayers().get(0).getCardsInHand()) {
					this.client.getGameClient().addCardIntoHand(c);
					c.printInfo();
				}
			} else {
				for (Card c : this.server.getGameServer().getPlayers().get(i).getCardsInHand()) {
					this.client.submitCard(i, c);
					// System.out.print(i + " : ");
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					c.printInfo();
				}
			}
			System.out.println(i + " point : " + this.server.getGameServer().getPlayers().get(i).getPoints());
			System.out.println("----");
		}
		System.out.println("deal finished");
	}

	@Override
	public Vector<Card> getCardsInHand() {
		return this.client.getGameClient().getCardsInHand();
	}

	@Override
	public void cut() {
		shuffleCard();
		dealCard();
	}

	@Override
	public void call() {


	}

	@Override
	public void compareTrick() {
		
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void setSeat(String seat) {
		this.client.getGameClient().setSeat(seat);
	}

	@Override
	public String getSeat() {
		return client.getGameClient().getSeat();
	}
	


	@Override
	public Transmissible getMessage() {
		return client.getMessageReceiveFromServer();
	}
}
