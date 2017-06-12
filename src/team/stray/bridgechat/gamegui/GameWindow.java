package team.stray.bridgechat.gamegui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import team.stray.bridgechat.Infrastructure;
import team.stray.bridgechat.InfrastructureImpl;
import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.bridge.Suit;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.transmissible.TransmissibleGameClient;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;
import team.stray.bridgechat.windowgui.*;

public class GameWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	static Vector<Card> cardInHand;
	CardGUI card1;
	CardGUI card2;
	CardGUI card3;
	CardGUI card4;
	CardGUI card5;
	CardGUI card6;
	CardGUI card7;
	CardGUI card8;
	CardGUI card9;
	CardGUI card10;
	CardGUI card11;
	CardGUI card12;
	CardGUI card13;

	public static void openGameGui(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow();
					if (WindowStart.infrastructure.getType() == Infrastructure.SERVER) {
						frame.cut();
					}
					frame.updateDeck();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Thread updateTable = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						// System.out.println(WindowStart.infrastructure.//get
						// card's that submit);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		updateTable.start();
	}

	/**
	 * Create the frame.
	 */
	private boolean isMouseHold = false;
	int cardx;
	int cardy;

	public GameWindow() {
		// layout
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/resource/chip.png")));
		setTitle("Bridgechat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// card
		card13 = new CardGUI();
		card13.setBounds(477, 355, 71, 96);
		contentPane.add(card13);

		card12 = new CardGUI();
		card12.setBounds(443, 355, 71, 96);
		contentPane.add(card12);

		card11 = new CardGUI();
		card11.setBounds(409, 355, 71, 96);
		contentPane.add(card11);

		card10 = new CardGUI();
		card10.setBounds(379, 355, 71, 96);
		contentPane.add(card10);

		card9 = new CardGUI();
		card9.setBounds(343, 355, 71, 96);
		contentPane.add(card9);

		card8 = new CardGUI();
		card8.setBounds(312, 355, 71, 96);
		contentPane.add(card8);

		card7 = new CardGUI();
		card7.setBounds(273, 355, 71, 96);
		contentPane.add(card7);

		card6 = new CardGUI();
		card6.setBounds(231, 355, 71, 96);
		contentPane.add(card6);

		card5 = new CardGUI();
		card5.setBounds(192, 355, 71, 96);
		contentPane.add(card5);

		card4 = new CardGUI();
		card4.setBounds(152, 355, 71, 96);
		contentPane.add(card4);

		card3 = new CardGUI();
		card3.setBounds(111, 355, 67, 96);
		contentPane.add(card3);

		card2 = new CardGUI();
		card2.setBounds(71, 355, 71, 96);
		contentPane.add(card2);

		card1 = new CardGUI();
		card1.setBounds(36, 355, 71, 96);
		contentPane.add(card1);

		// chatroom
		textField = new JTextField();
		textField.setBounds(650, 422, 239, 29);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\u50B3\u9001");
		btnNewButton.setBounds(887, 422, 67, 29);
		contentPane.add(btnNewButton);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(650, 10, 303, 423);
		contentPane.add(textArea);

		// player
		JLabel myicon = new JLabel("New label");
		myicon.setBounds(558, 368, 71, 65);
		contentPane.add(myicon);

		JLabel myname = new JLabel("New label");
		myname.setBounds(558, 332, 71, 29);
		contentPane.add(myname);

		JLabel label = new JLabel("New label");
		label.setBounds(558, 181, 71, 65);
		contentPane.add(label);

		JLabel label_1 = new JLabel("New label");
		label_1.setBounds(21, 181, 71, 65);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(273, 25, 71, 65);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(558, 145, 71, 29);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("New label");
		label_4.setBounds(354, 25, 71, 29);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("New label");
		label_5.setBounds(21, 142, 71, 29);
		contentPane.add(label_5);
	}

	public void cut() {
		WindowStart.infrastructure.shuffleCard();
		WindowStart.infrastructure.dealCard();
	}

	public void updateDeck() {
		cardInHand = new Vector<>();
		cardInHand = WindowStart.infrastructure.getCardsInHand();
		// cards in new one turn
		card13 = new CardGUI(cardInHand.get(12));
		card13.setBounds(477, 355, 71, 96);
		contentPane.add(card13);

		card12 = new CardGUI(cardInHand.get(11));
		card12.setBounds(443, 355, 71, 96);
		contentPane.add(card12);

		card11 = new CardGUI(cardInHand.get(10));
		card11.setBounds(409, 355, 71, 96);
		contentPane.add(card11);

		card10 = new CardGUI(cardInHand.get(9));
		card10.setBounds(379, 355, 71, 96);
		contentPane.add(card10);

		card9 = new CardGUI(cardInHand.get(8));
		card9.setBounds(343, 355, 71, 96);
		contentPane.add(card9);

		card8 = new CardGUI(cardInHand.get(7));
		card8.setBounds(312, 355, 71, 96);
		contentPane.add(card8);

		card7 = new CardGUI(cardInHand.get(6));
		card7.setBounds(273, 355, 71, 96);
		contentPane.add(card7);

		card6 = new CardGUI(cardInHand.get(5));
		card6.setBounds(231, 355, 71, 96);
		contentPane.add(card6);

		card5 = new CardGUI(cardInHand.get(4));
		card5.setBounds(192, 355, 71, 96);
		contentPane.add(card5);

		card4 = new CardGUI(cardInHand.get(3));
		card4.setBounds(152, 355, 71, 96);
		contentPane.add(card4);

		card3 = new CardGUI(cardInHand.get(2));
		card3.setBounds(111, 355, 67, 96);
		contentPane.add(card3);

		card2 = new CardGUI(cardInHand.get(1));
		card2.setBounds(71, 355, 71, 96);
		contentPane.add(card2);

		card1 = new CardGUI(cardInHand.get(0));
		card1.setBounds(36, 355, 71, 96);
		contentPane.add(card1);
	}
}
