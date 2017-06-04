package team.stray.bridgechat.connect.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.TransmissibleString;
import team.stray.bridgechat.connect.TransmitTimestamp;

public class ThreadServer implements Runnable {

	private final Socket socket;
	private Vector<ObjectOutputStream> memberList;

	public ThreadServer(Socket socket, Vector<ObjectOutputStream> memberList) {
		this.socket = socket;
		this.memberList = memberList;
	}

	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			while (true) {
				Transmissible receiveMessage = (Transmissible) in.readObject();
				if (receiveMessage instanceof TransmissibleString) {
					String clientText = ((TransmissibleString)receiveMessage).getTransmissibleString();
					//System.out.println(clientText);
					broadcast(receiveMessage, memberList);
					if (clientText.equals("bye")){
						break;
					}
				}
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void broadcast(Transmissible message, Vector<ObjectOutputStream> memberList) {
		/*Set Time stamp*/
		Date date = new Date();
		((TransmitTimestamp)message).setSendTimestamp(date.toString());
		
		/*Broadcast*/
		for (ObjectOutputStream i : memberList) {
			try {
				ObjectOutputStream out = i;
				out.writeObject(message);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}