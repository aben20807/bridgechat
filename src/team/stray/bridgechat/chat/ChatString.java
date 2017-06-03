package team.stray.bridgechat.chat;

import java.io.Serializable;

import team.stray.bridgechat.online.Transmissible;

public class ChatString implements Transmissible, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
}
