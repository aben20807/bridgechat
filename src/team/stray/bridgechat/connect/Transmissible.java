package team.stray.bridgechat.connect;

import java.io.Serializable;

public interface Transmissible extends Serializable{

	public String getTimestamp();
	public void setTimestamp(String sendTimestamp);
}
