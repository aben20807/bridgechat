package team.stray.bridgechat.online;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ConnectionClient extends Connection{

	protected final String name;
	protected final String ip;
	protected String message;

	public ConnectionClient(String name, String ip) {
		this.ip = ip;
		this.name = name;
	}
	
	public String getPhysicalClientMessage(String message){
		return message;
	}
	
	protected void linkStart() {
		try {
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
			Thread incomingreader = new Thread(new ThreadClient(in));
			incomingreader.start();
			break;
			
		case SUBMIT:
			if((ip != null) && (this.message != "")){
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

	public String getMessage() {
		return message;
	}

	public boolean setMessage(String message) {//TODO Limit the number of words
		if((this.message = message)!=null)
			return true;
		else
			return false;
	}
}
