package team.stray.bridgechat.gamegui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

import team.stray.bridgechat.Infrastructure;
import team.stray.bridgechat.InfrastructureImpl;
import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.Suit;
import team.stray.bridgechat.windowgui.WindowStart;

public class SubmitCardGUI extends JLabel {
	private boolean isMouseHold = false;
	private boolean isMoveable = true;
	int cardx;
	int cardy;
	SubmitCardGUI(){
		
	}
	
	SubmitCardGUI(Card card) {
		printIcon(card);
	}

	public void printIcon(Card card) {
	//	System.out.println("suit="+card.getSuit()+"number="+card.getNumber()+"\n");
		switch (card.getSuit()) {
		case Suit.SPADES:
			switch (card.getNumber()) {
			case '2':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/15.png")));
				break;
			case '3':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/16.png")));
				break;
			case '4':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/17.png")));
				break;
			case '5':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/18.png")));
				break;
			case '6':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/19.png")));
				break;
			case '7':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/20.png")));
				break;
			case '8':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/21.png")));
				break;
			case '9':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/22.png")));
				break;
			case 'T':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/23.png")));
				break;
			case 'J':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/24.png")));
				break;
			case 'Q':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/25.png")));
				break;
			case 'K':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/26.png")));
				break;
			case 'A':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/14.png")));
				break;
			}
			break;
		case Suit.HEARTS:
			switch (card.getNumber()) {
			case '2':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/28.png")));
				break;
			case '3':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/29.png")));
				break;
			case '4':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/30.png")));
				break;
			case '5':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/31.png")));
				break;
			case '6':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/32.png")));
				break;
			case '7':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/33.png")));
				break;
			case '8':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/34.png")));
				break;
			case '9':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/35.png")));
				break;
			case 'T':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/36.png")));
				break;
			case 'J':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/37.png")));
				break;
			case 'Q':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/38.png")));
				break;
			case 'K':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/39.png")));
				break;
			case 'A':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/27.png")));
				break;
			}
			break;
		case Suit.DIAMONDS:
			switch (card.getNumber()) {
			case '2':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/41.png")));
				break;
			case '3':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/42.png")));
				break;
			case '4':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/43.png")));
				break;
			case '5':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/44.png")));
				break;
			case '6':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/45.png")));
				break;
			case '7':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/46.png")));
				break;
			case '8':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/47.png")));
				break;
			case '9':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/48.png")));
				break;
			case 'T':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/49.png")));
				break;
			case 'J':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/50.png")));
				break;
			case 'Q':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/51.png")));
				break;
			case 'K':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/52.png")));
				break;
			case 'A':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/40.png")));
				break;
			}
			break;
		case Suit.CLUBS:
			switch (card.getNumber()) {
			case '2':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/2.png")));
				break;
			case '3':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/3.png")));
				break;
			case '4':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/4.png")));
				break;
			case '5':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/5.png")));
				break;
			case '6':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/6.png")));
				break;
			case '7':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/7.png")));
				break;
			case '8':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/8.png")));
				break;
			case '9':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/9.png")));
				break;
			case 'T':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/10.png")));
				break;
			case 'J':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/11.png")));
				break;
			case 'Q':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/12.png")));
				break;
			case 'K':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/13.png")));
				break;
			case 'A':
				setIcon(new ImageIcon(GameWindow.class.getResource("/resource/1.png")));
				break;
			}

		}
	}
}
