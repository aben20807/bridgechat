package team.stray.bridgechat.connect;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Connection {

	protected ObjectInputStream in;
	protected ObjectOutputStream out;
	
	public static final int CONNECT = 0;
	public static final int SUBMIT = 1;
}
