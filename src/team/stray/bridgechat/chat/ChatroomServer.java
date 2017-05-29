package team.stray.bridgechat.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class ChatroomServer implements IChatroom{

	private Vector<PrintStream> memberList;
	
	public ChatroomServer(){
		memberList = new Vector<>();
		
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("Server started....");
			System.out.println(getIP());
			while(true){
				Socket socket = serverSocket.accept();
				PrintStream writer = new PrintStream(socket.getOutputStream());
				memberList.add(writer);
				
				Thread chatThread = new Thread(new Chat(socket, memberList));
				chatThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIP(){
		try {
			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "";
		}
	}
}

class Chat implements Runnable{
	
	private Socket socket;
	Vector<PrintStream> memberList;
	public Chat(Socket socket, Vector<PrintStream> memberList){
		this.socket = socket;
		this.memberList = memberList;
	}
	
	public void run(){
		try {
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true){
				String clientText = clientInput.readLine();
				System.out.println(clientText);
				broadCast(clientText, memberList);
				if(clientText.equals("bye"))break;
			}
			clientInput.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void broadCast(String message, Vector<PrintStream> memberList){
		
		for(int i = 0; i < memberList.size(); i++){
			try {
				PrintStream writer = memberList.get(i);
				writer.println(message);
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}