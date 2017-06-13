package team.stray.bridgechat.connect.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.connect.Connection;
import team.stray.bridgechat.connect.Transmissible;

public class ConnectionServer extends Connection {

	private List<ObjectOutputStream> memberList;
	private ServerSocket serverSocket;
	private Thread threadServer;

	public ConnectionServer() {
		memberList = new CopyOnWriteArrayList<>();
		try {
			serverSocket = new ServerSocket(8080);
			System.out.println("Server started....");
			System.out.println(getIP());
			new Thread(new Runnable() {// Anonymous class
				public volatile boolean isTerminated = false;
				public void run() {
					Socket socket = null;
					try {
						while (!isTerminated) {
							socket = serverSocket.accept();
							out = new ObjectOutputStream(socket.getOutputStream());
							memberList.add(out);
							threadServer = new Thread(new ThreadServer(socket, memberList));
							threadServer.start();
							System.out.println(memberList.size());
							if(memberList.size() == 4){//terminate when room full
								try {
									Thread.sleep(30);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								isTerminated = true;
								serverSocket.close();
								System.out.println("Stop accepting people connect to server....");
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
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