package team.stray.bridgechat.online;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import team.stray.bridgechat.chat.TransmissibleString;

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
				// String clientText = (String) in.readObject();
				Transmissible receiveMessage = (Transmissible) in.readObject();
				if (receiveMessage instanceof TransmissibleString) {
					String clientText = ((TransmissibleString)receiveMessage).getTransmissibleString();
					System.out.println(clientText);
					broadCast(receiveMessage, memberList);
					if (clientText.equals("bye"))
						break;
				}
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void broadCast(Transmissible message, Vector<ObjectOutputStream> memberList) {

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