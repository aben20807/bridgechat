package team.stray.bridgechat.online;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class PhysicalThreadServer implements Runnable{
	
	private Socket socket;
	Vector<ObjectOutputStream> memberList;
//	private Message msg;
	
	private ObjectInputStream in;
	
	public PhysicalThreadServer(ObjectInputStream in, Vector<ObjectOutputStream> memberList){
		this.in = in;
		this.memberList = memberList;
	}
	
	public void run(){
		try {
			//ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Message msg;
//			String message;
			String clientText;
			while((clientText = (String) in.readObject())!=null){
				
//				msg = (Message) in.readObject();
//				String clientText = msg.getChatMessage();
				
//				String clientText = (String) in.readObject();
				
				System.out.println(clientText);
//				broadCast(msg, memberList);
				broadCast(clientText, memberList);
				if(clientText.equals("bye"))break;
			}
			in.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void broadCast(Message msg, Vector<ObjectOutputStream> memberList){
		
		for(int i = 0; i < memberList.size(); i++){
			try {
				ObjectOutputStream out = memberList.get(i);
				System.out.println("broadcast " + msg.getChatMessage());
				out.writeObject(msg);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void broadCast(String msg, Vector<ObjectOutputStream> memberList){
		
		for(int i = 0; i < memberList.size(); i++){
			try {
				ObjectOutputStream out = memberList.get(i);
				System.out.println("broadcast " + msg);
				out.writeObject(msg);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}