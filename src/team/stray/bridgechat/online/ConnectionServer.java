package team.stray.bridgechat.online;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class ConnectionServer extends Connection {

	private Vector<ObjectOutputStream> memberList;
	private ServerSocket serverSocket;

	public ConnectionServer() {
		memberList = new Vector<>();
		try {
			serverSocket = new ServerSocket(8000);
			System.out.println("Server started....");
			System.out.println(getIP());
			while (true) {
				Socket socket = serverSocket.accept();
				out = new ObjectOutputStream(socket.getOutputStream());
				memberList.add(out);

				Thread chatThread = new Thread(new ThreadServer(socket, memberList));
				chatThread.start();
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