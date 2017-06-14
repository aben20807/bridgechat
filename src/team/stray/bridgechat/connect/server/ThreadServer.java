package team.stray.bridgechat.connect.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;

public class ThreadServer implements Runnable {

	private final Socket socket;
	private List<ObjectOutputStream> memberList;

	public ThreadServer(Socket socket, List<ObjectOutputStream> memberList) {
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
					if (clientText.equals("bye")){
						break;
					}
				}
				broadcast(receiveMessage, memberList);
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Broadcast message to every members from server
	 * @param message: message need to broadcast
	 * @param memberList: broadcast target list
	 */
	public void broadcast(Transmissible message, List<ObjectOutputStream> memberList) {
		for (ObjectOutputStream i : memberList) {
			straightTransmit(message, i);
		}
	}
	
	public void straightTransmit(Transmissible message, ObjectOutputStream out){
		try {
			out.writeObject(message);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}