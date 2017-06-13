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
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import team.stray.bridgechat.windowgui.*;

public class GameWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	// Vector<Card> cardInHand;
	/*
	 * static CardGUI card1; static CardGUI card2; static CardGUI card3; static
	 * CardGUI card4; static CardGUI card5; static CardGUI card6; static CardGUI
	 * card7; static CardGUI card8; static CardGUI card9; static CardGUI card10;
	 * static CardGUI card11; static CardGUI card12; static CardGUI card13;
	 */
	JLabel submitCard1;
	JLabel submitCard2;
	JLabel submitCard3;
	JLabel submitCard4;
	static GameWindow frame;
	static boolean isDeck = false;

	public static void openGameGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GameWindow();
					if (WindowStart.infrastructure.getType() == Infrastructure.SERVER) {
						while (!GameServer.isPlayersReachFour) {
							Thread.sleep(2000);
							// System.out.println(GameServer.isPlayersReachFour);
						}
						Thread.sleep(2000);
						WindowStart.infrastructure.shuffleCard();
						Thread.sleep(3000);
						WindowStart.infrastructure.dealCard();
						// frame.setVisible(true);
						// frame.updateDeck();
					}
					// frame.setVisible(true);
					// frame.updateDeck();

					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Thread updateDeck = new Thread(new Runnable() {
			public void run() {
				while (!isDeck) {
					try {
						// System.out.println(frame != null);
						Thread.sleep(3000);
						if (frame != null) {
							frame.updateDeck();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		updateDeck.start();

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

		Thread updateChatroom = new Thread(new Runnable() {
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
		updateChatroom.start();
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
		/*
		 * card13 = new CardGUI(); card13.setBounds(477, 355, 71, 96);
		 * contentPane.add(card13);
		 * 
		 * card12 = new CardGUI(); card12.setBounds(443, 355, 71, 96);
		 * contentPane.add(card12);
		 * 
		 * card11 = new CardGUI(); card11.setBounds(409, 355, 71, 96);
		 * contentPane.add(card11);
		 * 
		 * card10 = new CardGUI(); card10.setBounds(379, 355, 71, 96);
		 * contentPane.add(card10);
		 * 
		 * card9 = new CardGUI(); card9.setBounds(343, 355, 71, 96);
		 * contentPane.add(card9);
		 * 
		 * card8 = new CardGUI(); card8.setBounds(312, 355, 71, 96);
		 * contentPane.add(card8);
		 * 
		 * card7 = new CardGUI(); card7.setBounds(273, 355, 71, 96);
		 * contentPane.add(card7);
		 * 
		 * card6 = new CardGUI(); card6.setBounds(231, 355, 71, 96);
		 * contentPane.add(card6);
		 * 
		 * card5 = new CardGUI(); card5.setBounds(192, 355, 71, 96);
		 * contentPane.add(card5);
		 * 
		 * card4 = new CardGUI(); card4.setBounds(152, 355, 71, 96);
		 * contentPane.add(card4);
		 * 
		 * card3 = new CardGUI(); card3.setBounds(111, 355, 67, 96);
		 * contentPane.add(card3);
		 * 
		 * card2 = new CardGUI(); card2.setBounds(71, 355, 71, 96);
		 * contentPane.add(card2);
		 * 
		 * card1 = new CardGUI(); card1.setBounds(36, 355, 71, 96);
		 * contentPane.add(card1);
		 */

		// chat room
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

		JLabel player2icon = new JLabel("New label");
		player2icon.setBounds(21, 181, 71, 65);
		contentPane.add(player2icon);

		JLabel player3icon = new JLabel("New label");
		player3icon.setBounds(273, 10, 71, 65);
		contentPane.add(player3icon);

		JLabel player4name = new JLabel("New label");
		player4name.setBounds(558, 145, 71, 29);
		contentPane.add(player4name);

		JLabel player3name = new JLabel("New label");
		player3name.setBounds(354, 25, 71, 29);
		contentPane.add(player3name);

		JLabel player2name = new JLabel("New label");
		player2name.setBounds(21, 142, 71, 29);
		contentPane.add(player2name);

		submitCard1 = new JLabel("New label");
		submitCard1.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/1.png")));
		submitCard1.setBounds(286, 224, 71, 96);
		contentPane.add(submitCard1);

		submitCard2 = new JLabel("New label");
		submitCard2.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/13.png")));
		submitCard2.setBounds(162, 163, 71, 96);
		contentPane.add(submitCard2);

		submitCard3 = new JLabel("New label");
		submitCard3.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/12.png")));
		submitCard3.setBounds(286, 85, 71, 96);
		contentPane.add(submitCard3);

		submitCard4 = new JLabel("New label");
		submitCard4.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/11.png")));
		submitCard4.setBounds(409, 163, 71, 96);
		contentPane.add(submitCard4);
	}

	public void cut() {
		WindowStart.infrastructure.shuffleCard();
		WindowStart.infrastructure.dealCard();
	}

	public void updateDeck() {
		// System.out.println("card in hand size = " +
		// WindowStart.infrastructure.getCardsInHand().size());
		if (WindowStart.infrastructure.getCardsInHand().size() == 13) {
			List<Card> cardInHand = new CopyOnWriteArrayList<>();
			cardInHand = WindowStart.infrastructure.getCardsInHand();
			// cards in new one turn
			CardGUI card13 = new CardGUI(cardInHand.get(12));
			card13.setBounds(477, 355, 71, 96);
			contentPane.add(card13);

			CardGUI card12 = new CardGUI(cardInHand.get(11));
			card12.setBounds(443, 355, 71, 96);
			contentPane.add(card12);

			CardGUI card11 = new CardGUI(cardInHand.get(10));
			card11.setBounds(409, 355, 71, 96);
			contentPane.add(card11);

			CardGUI card10 = new CardGUI(cardInHand.get(9));
			card10.setBounds(379, 355, 71, 96);
			contentPane.add(card10);

			CardGUI card9 = new CardGUI(cardInHand.get(8));
			card9.setBounds(343, 355, 71, 96);
			contentPane.add(card9);

			CardGUI card8 = new CardGUI(cardInHand.get(7));
			card8.setBounds(312, 355, 71, 96);
			contentPane.add(card8);

			CardGUI card7 = new CardGUI(cardInHand.get(6));
			card7.setBounds(273, 355, 71, 96);
			contentPane.add(card7);

			CardGUI card6 = new CardGUI(cardInHand.get(5));
			card6.setBounds(231, 355, 71, 96);
			contentPane.add(card6);

			CardGUI card5 = new CardGUI(cardInHand.get(4));
			card5.setBounds(192, 355, 71, 96);
			contentPane.add(card5);

			CardGUI card4 = new CardGUI(cardInHand.get(3));
			card4.setBounds(152, 355, 71, 96);
			contentPane.add(card4);

			CardGUI card3 = new CardGUI(cardInHand.get(2));
			card3.setBounds(111, 355, 67, 96);
			contentPane.add(card3);

			CardGUI card2 = new CardGUI(cardInHand.get(1));
			card2.setBounds(71, 355, 71, 96);
			contentPane.add(card2);

			CardGUI card1 = new CardGUI(cardInHand.get(0));
			card1.setBounds(36, 355, 71, 96);
			contentPane.add(card1);
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			isDeck = true;
			System.out.println("deal cards finished");
			frame.setVisible(true);

		}
	}
}
