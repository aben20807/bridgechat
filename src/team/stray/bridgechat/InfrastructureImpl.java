package team.stray.bridgechat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private String seat;

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
		System.out.println("shuffling....");
		GameServer.cards = GameServer.dealer.shuffle(GameServer.cards);
		System.out.println("shuffle finished");
	}

	@Override
	public void dealCard() {
		System.out.println("dealing....");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// ***
		// this.server.getGameServer().getDealer().deal(GameServer.cards,
		// server.getGameServer().getPlayers().get(0),
		// server.getGameServer().getPlayers().get(1),
		// server.getGameServer().getPlayers().get(2),
		// server.getGameServer().getPlayers().get(3));
		// ***
		GameServer.dealer.deal(GameServer.cards, GameServer.players.get(0), GameServer.players.get(1),
				GameServer.players.get(2), GameServer.players.get(3));
		for (int i = 0; i < 4; i++) {
			// sort cards in hand
			// ***
			// this.server.getGameServer().getPlayers().get(i).sortCardsInHand();
			// System.out.println(i + " point : " +
			// this.server.getGameServer().getPlayers().get(i).getPoints());
			GameServer.players.get(i).sortCardsInHand();
			System.out.println(i + " point : " + GameServer.players.get(i).getPoints());
		}
		// for (int i = 0; i < 4; i++) {
		// // sort cards in hand
		// this.server.getGameServer().getPlayers().get(i).sortCardsInHand();
		// // deal
		// for (Card c :
		// this.server.getGameServer().getPlayers().get(i).getCardsInHand()) {
		// this.client.submitCard(i, c);
		// // System.out.print(i + " : ");
		// try {
		// Thread.sleep(600);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// c.printInfo();
		// }
		// System.out.println(i + " point : " +
		// this.server.getGameServer().getPlayers().get(i).getPoints());
		// System.out.println("----");
		// }
		// System.out.println("deal finished");

		for (int j = 0; j < 13; j++) {
			System.out.println("----" + (j+1) + "----");
			for (int i = 0; i < 4; i++) {
				// ***
				// Card c =
				// this.server.getGameServer().getPlayers().get(i).getCardsInHand().get(j);
				Card c = GameServer.players.get(i).getCardsInHand().get(j);
				if (i == 0) {
					this.server.getClient().getGameClient().addCardIntoHand(c);
				} else {
					this.client.submitCard(i, c);
				}
				// System.out.print(i + " : ");
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c.printInfo();
			}

		}
		System.out.println("deal finished");
	}

	@Override
	public List<Card> getCardsInHand() {
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
		this.seat = seat;
	}

	@Override
	public String getSeat() {
//		return client.getGameClient().getSeat();
		return seat;
	}

	@Override
	public Transmissible getMessage() {
		if(client != null){
		return client.getMessageReceiveFromServer();
		}
		else return null;
	}

	@Override
	public HashMap<Integer,String> getSeatArrange() {
		// TODO Auto-generated method stub
		return this.client.seatToName;
	}
	
}
