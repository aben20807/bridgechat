package team.stray.bridgechat.online;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ThreadServer implements Runnable{
	
	private final Socket socket;
	private Vector<ObjectOutputStream> memberList;
	
	public ThreadServer(Socket socket, Vector<ObjectOutputStream> memberList){
		this.socket = socket;
		this.memberList = memberList;
	}
	
	public void run(){
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			while(true){
				String clientText = (String) in.readObject();
				System.out.println(clientText);
				broadCast(clientText, memberList);
				if(clientText.equals("bye"))break;
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void broadCast(String message, Vector<ObjectOutputStream> memberList){
		
		for(int i = 0; i < memberList.size(); i++){
			try {
				ObjectOutputStream writer = memberList.get(i);
				writer.writeObject(message);
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}