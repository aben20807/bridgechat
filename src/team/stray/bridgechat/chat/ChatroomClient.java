package team.stray.bridgechat.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ChatroomClient implements IChatroom {

	private final String name;
	private final String ip;
	private String message;
	private BufferedReader reader;
	private PrintStream writer;

	public ChatroomClient(String name, String ip) {
		this.ip = ip;
		this.name = name;
	}

	private void linkStart() {
		try {
			Socket socket = new Socket(ip, 8000);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("cannot connect ip "+ ip);
			e.printStackTrace();
		}
	}
	
	public void doFunction(int command){
		switch (command) {
		case CONNECT:
			linkStart();
			Thread incomingreader = new Thread(new IncomingReader());
			incomingreader.start();
			break;
			
		case SUBMIT:
			if((ip != null) && (this.message != "")){
				try {
					writer.println(name+":"+this.message);
					writer.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		default:
			break;
		}
	}

	private class IncomingReader implements Runnable {
		public void run(){
			String messageFromOthers;
			try {
				while((messageFromOthers = reader.readLine()) != null){
					System.out.println(messageFromOthers);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
