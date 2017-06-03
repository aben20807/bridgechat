package team.stray.bridgechat.chat;

public class ChatroomClient{
	
	protected final String name;
	protected final String ip;
	

	public ChatroomClient(String name, String ip) {
		this.ip = ip;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}
}
