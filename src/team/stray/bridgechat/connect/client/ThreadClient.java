package team.stray.bridgechat.connect.client;

import java.io.ObjectInputStream;

import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;

public class ThreadClient implements Runnable {

	private final ObjectInputStream in;
	private final ConnectionClient connectionClient;

	public ThreadClient(ObjectInputStream in, ConnectionClient connectionClient) {
		this.in = in;
		this.connectionClient = connectionClient;
	}

	public void run() {
		String messageFromBroadCast;
		try {
			Transmissible receiveMessage;
			while ((receiveMessage = (Transmissible) in.readObject()) != null) {

				if ((receiveMessage instanceof TransmissibleString)
						&& (messageFromBroadCast = ((TransmissibleString) receiveMessage)
								.getTransmissibleString()) != null){
					System.out.println(messageFromBroadCast);
				}
				/*Let connectionClient know the receiveMessage from server*/
				connectionClient.setReceiveMessage(receiveMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
