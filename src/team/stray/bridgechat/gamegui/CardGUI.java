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

public class CardGUI extends submitCardGUI {
	private boolean isMouseHold = false;
	private boolean isMoveable = true;
	int cardx;
	int cardy;
	CardGUI(){
		
	}
	
	CardGUI(Card card) {
		printIcon(card);
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (isMouseHold) {
					setBounds(getX() + e.getX() - 35, getY() + e.getY() - 48, 71, 96);
					// System.out.println("card1x=" + (getX()) + "card1y=" +
					// (getY()));
					// System.out.println("cardxx=" + (e.getX()) + "cardyy=" +
					// (e.getY()));
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (isMoveable) {
					isMouseHold = true;
					cardx = getX();
					cardy = getY();
				//	System.out.println("cardx=" + cardx + "cardy=" + cardy);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isMouseHold = false;
				if (getY() < 260) {
					setVisible(false);
					WindowStart.infrastructure.submitString("%"+WindowStart.infrastructure.getSeat().substring(1,2)+card.getNumber()+card.getSuit()+card.getValue());
					GameWindow.changeTable(card);
				} else {
					setLocation(cardx, cardy);
				}
			}

		});
	}
}
