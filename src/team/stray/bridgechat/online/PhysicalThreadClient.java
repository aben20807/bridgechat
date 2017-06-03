package team.stray.bridgechat.online;

import java.io.ObjectInputStream;

public class PhysicalThreadClient implements Runnable {

	private ObjectInputStream in;

	public PhysicalThreadClient(ObjectInputStream in) {
		this.in = in;
	}

	public void run() {
//		Message msg = null;
		String message;
		try {
//			while ((msg = (Message) in.readObject()) != null) {
			while ((message = (String) in.readObject()) != null) {
//				tmp = (String) in.readObject();
//				Message msg = (Message) in.readObject();
				//if (msg.getChatMessage() != "") {
//					System.out.println(msg.getChatMessage());
				//}
//				System.out.println(tmp);
				
//				System.out.println(msg.getChatMessage());
				System.out.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
