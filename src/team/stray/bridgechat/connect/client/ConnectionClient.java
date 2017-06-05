package team.stray.bridgechat.connect.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.connect.Connection;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.TransmissibleCard;
import team.stray.bridgechat.connect.TransmissibleString;


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
			Thread threadClient = new Thread(new ThreadClient(in, this));
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

	/**
	 * Let connectionClient get the receiveMessage
	 * @param message: message in ThreadClient
	 */
	public void setReceiveMessage(Transmissible message) {//TODO Limit the size
		this.message = message;
	}
	
	/**
	 * Let Client's ThreadGetMessage get message
	 * @return Transmissible message
	 */
	public Transmissible getReceiveMessage(){
		System.out.flush();//Oriental mysterious power OuO
		return message;
	}
	
	/*getter and setter*/
	
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
