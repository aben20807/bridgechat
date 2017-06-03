package team.stray.bridgechat.connect.client;

import java.io.ObjectInputStream;

import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.TransmissibleString;

public class ThreadClient implements Runnable {

	private final ObjectInputStream in;

	public ThreadClient(ObjectInputStream in) {
		this.in = in;
	}

	public void run() {
		String messageFromOthers;
		try {
			Transmissible receiveMessage;
			while ((receiveMessage = (Transmissible) in.readObject()) != null) {

				if ((receiveMessage instanceof TransmissibleString)
						&& (messageFromOthers = ((TransmissibleString) receiveMessage).getTransmissibleString()) != null)
					System.out.println(messageFromOthers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
