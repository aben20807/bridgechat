package team.stray.bridgechat.online;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import team.stray.bridgechat.chat.IChatroom;

public class PhysicalServer implements IChatroom {

	private Vector<ObjectOutputStream> memberList;
	private ServerSocket serverSocket;

//	private ObjectOutputStream out;
	
	public PhysicalServer() {
		memberList = new Vector<>();
		try {
			serverSocket = new ServerSocket(8000);
			
			
			System.out.println("Server started....");
			System.out.println(getIP());
			while (true) {
				Socket socket = serverSocket.accept();
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				memberList.add(out);

				Thread threadServer = new Thread(new PhysicalThreadServer(in, memberList));
				threadServer.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "";
		}
	}
}
