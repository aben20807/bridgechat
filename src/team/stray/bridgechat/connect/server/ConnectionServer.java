package team.stray.bridgechat.connect.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import team.stray.bridgechat.connect.Connection;

public class ConnectionServer extends Connection {

	private Vector<ObjectOutputStream> memberList;
	private ServerSocket serverSocket;

	public ConnectionServer() {
		memberList = new Vector<>();
		try {
			serverSocket = new ServerSocket(8000);
			System.out.println("Server started....");
			System.out.println(getIP());
			Thread thread = new Thread(new Runnable() {//Anonymous class
				public void run() {
					while (true) {
						Socket socket;
						try {
							socket = serverSocket.accept();
							out = new ObjectOutputStream(socket.getOutputStream());
							memberList.add(out);

							Thread threadServer = new Thread(new ThreadServer(socket, memberList));
							threadServer.start();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return server's IP address
	 */
	public String getIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "";
		}
	}
}