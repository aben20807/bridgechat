package team.stray.bridgechat.online;

import java.io.ObjectInputStream;

public class ThreadClient implements Runnable {
	
	private final ObjectInputStream in;
	
	public ThreadClient(ObjectInputStream in){
		this.in = in;
	}
	
	public void run(){
		String messageFromOthers;
		try {
			while((messageFromOthers = (String) in.readObject()) != null){	
				System.out.println(messageFromOthers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

