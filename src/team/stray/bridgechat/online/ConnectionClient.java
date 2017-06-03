package team.stray.bridgechat.online;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.TransmissibleCard;
import team.stray.bridgechat.chat.TransmissibleString;


public class ConnectionClient extends Connection{

	private final String name;
	private final String ip;
	private Transmissible message;

	public ConnectionClient(String name, String ip) {
		this.ip = ip;
		this.name = name;
	}
	
	protected void linkStart() {
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket(ip, 8000);
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("cannot connect ip "+ ip);
			e.printStackTrace();
		}
	}

	public void doFunction(int command){
		switch (command) {
		case CONNECT:
			linkStart();
			Thread threadClient = new Thread(new ThreadClient(in));
			threadClient.start();
			break;
			
		case SUBMIT:
			if((ip != null) && ((this.message) != null)){
				try {
					out.writeObject(message);
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		default:
			break;
		}
	}

	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}

	public void setMessage(String message) {//TODO Limit the size
		this.message = new TransmissibleString();
		((TransmissibleString)this.message).setTransmissibleString(message);
	}
	
	public void setMessage(Card card) {//TODO Limit the size
		this.message = new TransmissibleCard();
		((TransmissibleCard)this.message).setTransmissibleCard(card);
	}
}
