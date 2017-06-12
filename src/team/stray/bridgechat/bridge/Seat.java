package team.stray.bridgechat.bridge;

import team.stray.bridgechat.InfrastructureImpl;

public class Seat implements Direction{

	private int direction;
	private String nameatSeat;
	
	public Seat(){
		this(1,"serever");//south default
		
	}
	public Seat(int direction, String nameatSeat){
		setDirection(direction);
		setNameatSeat(nameatSeat);
	}
	

	/**
	 * print Seat's information
	 */
	public void printInfo(){
		System.out.println(getDirection()+ "\t"+getNameatSeat());
	}
	
	/**
	 * clockwise turn
	 */
	public int clockwiseturn(int direction,String nameatSeat){
		int nextDirection;
		if((direction+1)<=4){
			nextDirection = direction+1;
		}else{
			nextDirection = 1;
		}
		return nextDirection;
	}
	
	/*getter and setter*/
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int direction){	
		if(direction >= SOUTH && direction <= EAST){
		this.direction = direction;
		}
	}
	
	public String getNameatSeat(){
		return nameatSeat;
	}
	
	public void setNameatSeat(String nameatSeat){
		
	}
}
