package team.stray.bridgechat.online;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import team.stray.bridgechat.chat.IChatroom;

public class PhysicalClient implements IChatroom {

	protected final String name;
	protected final String ip;
	
	protected String message;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	protected Message msg;

	public PhysicalClient(String name, String ip) {
		this.ip = ip;
		this.name = name;
		msg = new Message();
	}

	protected void linkStart() {
		try {
			Socket socket = new Socket(ip, 8000);
			
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
		} catch (Exception e) {
			System.out.println("cannot connect ip " + ip);
			e.printStackTrace();
		}
	}

	public void doFunction(int command) {
		switch (command) {
		case CONNECT:
			linkStart();
			Thread threadClient = new Thread(new PhysicalThreadClient(in));
			threadClient.start();
			break;

		case SUBMIT:
			if ((ip != null) && (this.msg.getChatMessage() != "")) {
				try {
//					System.out.println(msg.getChatMessage());
//					out.writeObject(this.msg);
					out.writeObject(this.message);
					out.flush();
				} catch (IOException e) {
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
//
//	public String getMessage() {
//		return message;
//	}

	public boolean setMessage(String message) {// TODO Limit the number of words
//		System.out.println(message);
		if ((this.msg.setChatMessage(message))){
//		if((this.message = message) != null){
			return true;
		}
		else
			return false;
	}
}
