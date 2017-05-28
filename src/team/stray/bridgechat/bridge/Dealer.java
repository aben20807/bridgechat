package team.stray.bridgechat.bridge;

public class Dealer {

	public static Dealer instance = null;
	
	private Dealer(){};
	
	public static Dealer getInstnce(){
		if(instance == null){
			synchronized (Dealer.class) {
				if(instance == null){
					instance = new Dealer();
				}
			}
		}
		return instance;
	}
	
	public void shuffle(){
		
	}
	
	public void deal(){
		
	}
}
