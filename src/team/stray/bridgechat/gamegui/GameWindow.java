package team.stray.bridgechat.gamegui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.javafx.scene.control.skin.TextAreaSkin;

import team.stray.bridgechat.Infrastructure;
import team.stray.bridgechat.InfrastructureImpl;
import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.Direction;
import team.stray.bridgechat.bridge.GameServer;
import team.stray.bridgechat.bridge.Suit;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.transmissible.TransmissibleGameClient;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;

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
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import team.stray.bridgechat.windowgui.*;

public class GameWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
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

	static SubmitCardGUI submitCard1;
	static SubmitCardGUI submitCard2;
	static SubmitCardGUI submitCard3;
	static SubmitCardGUI submitCard4;
	static GameWindow frame;
	static boolean isDeck = false;

	public static void openGameGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					while (WindowStart.infrastructure.getSeat() == null) {
						System.out.println("Seat null!!");
						Thread.sleep(1000);
					}
					while (WindowStart.infrastructure.getSeat().length() < 2) {
						System.out.println(WindowStart.infrastructure.getSeat());
						Thread.sleep(1000);
					}
					while (Integer.parseInt(WindowStart.infrastructure.getSeat().substring(1, 2)) < 1) {
						System.out.println(Integer.parseInt(WindowStart.infrastructure.getSeat().substring(1, 2)));
						Thread.sleep(1000);
					}
					while (WindowStart.infrastructure.getSeatArrange() == null) {
						System.out.println("Seat still null!!");
						Thread.sleep(1000);
					}
					while (WindowStart.infrastructure.getSeatArrange().size() < 4) {
						System.out.println(WindowStart.infrastructure.getSeatArrange().size());
						Thread.sleep(1000);
					}
					Map<Integer, String> seatToName=WindowStart.infrastructure.getSeatArrange();
					for (Object key : seatToName.keySet()) {
			            System.out.println(key + " : " + seatToName.get(key));
			        }
					System.out.println("Get Seat OK!!");
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
					}
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
						Transmissible last = null;
						while (true) {
							last = WindowStart.infrastructure.getMessage();
							if(last instanceof TransmissibleString){
								String get = ((TransmissibleString) last).getTransmissibleString();
								if(get.length()!=0 && get.charAt(0) == '$'){
									frame.textArea.append(get.substring(1)+"\n");
								}
							}
						}
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

		// chat room
		textField = new JTextField();
		textField.setBounds(650, 422, 239, 29);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSend = new JButton("\u50B3\u9001");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.sendMessage();
			}
		});
		btnSend.setBounds(887, 422, 67, 29);
		contentPane.add(btnSend);

		textArea = new JTextArea();
		textArea.setBounds(650, 10, 303, 423);
		contentPane.add(textArea);

		// System.out.println(WindowStart.infrastructure.getSeat().charAt(1));
		if (WindowStart.infrastructure.getSeat().charAt(1) == '1') {

			JLabel player1name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.SOUTH)));
			player1name.setBounds(558, 371, 71, 29);
			contentPane.add(player1name);

			JLabel player4name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.EAST)));
			player4name.setBounds(558, 197, 71, 29);
			contentPane.add(player4name);

			JLabel player3name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.NORTH)));
			player3name.setBounds(286, 8, 71, 29);
			contentPane.add(player3name);

			JLabel player2name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.WEST)));
			player2name.setBounds(31, 197, 71, 29);
			contentPane.add(player2name);

			submitCard1 = new SubmitCardGUI();
			// submitCard1.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/1.png")));
			submitCard1.setBounds(286, 224, 71, 96);
			contentPane.add(submitCard1);

			submitCard2 = new SubmitCardGUI();
			// submitCard2.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/13.png")));
			submitCard2.setBounds(162, 163, 71, 96);
			contentPane.add(submitCard2);

			submitCard3 = new SubmitCardGUI();
			// submitCard3.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/12.png")));
			submitCard3.setBounds(286, 85, 71, 96);
			contentPane.add(submitCard3);

			submitCard4 = new SubmitCardGUI();
			// submitCard4.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/11.png")));
			submitCard4.setBounds(409, 163, 71, 96);
			contentPane.add(submitCard4);
		}

		else if (WindowStart.infrastructure.getSeat().charAt(1) == '2') {

			JLabel player2name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.WEST)));
			player2name.setBounds(558, 371, 71, 29);
			contentPane.add(player2name);

			JLabel player1name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.SOUTH)));
			player1name.setBounds(558, 197, 71, 29);
			contentPane.add(player1name);

			JLabel player4name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.EAST)));
			player4name.setBounds(286, 8, 71, 29);
			contentPane.add(player4name);

			JLabel player3name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.NORTH)));
			player3name.setBounds(31, 197, 71, 29);
			contentPane.add(player3name);

			submitCard1 = new SubmitCardGUI();
			// submitCard1.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/1.png")));
			submitCard1.setBounds(409, 163, 71, 96);
			contentPane.add(submitCard1);

			submitCard2 = new SubmitCardGUI();
			// submitCard2.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/2.png")));
			submitCard2.setBounds(286, 224, 71, 96);
			contentPane.add(submitCard2);

			submitCard3 = new SubmitCardGUI();
			// submitCard3.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/3.png")));
			submitCard3.setBounds(162, 163, 71, 96);
			contentPane.add(submitCard3);

			submitCard4 = new SubmitCardGUI();
			// submitCard4.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/4.png")));
			submitCard4.setBounds(286, 85, 71, 96);
			contentPane.add(submitCard4);
		}

		else if (WindowStart.infrastructure.getSeat().charAt(1) == '3') {

			JLabel player3name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.NORTH)));
			player3name.setBounds(558, 371, 71, 29);
			contentPane.add(player3name);

			JLabel player2name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.WEST)));
			player2name.setBounds(558, 197, 71, 29);
			contentPane.add(player2name);

			JLabel player1name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.SOUTH)));
			player1name.setBounds(286, 8, 71, 29);
			contentPane.add(player1name);

			JLabel player4name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.EAST)));
			player4name.setBounds(31, 197, 71, 29);
			contentPane.add(player4name);

			submitCard2 = new SubmitCardGUI();
			// submitCard2.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/2.png")));
			submitCard2.setBounds(409, 163, 71, 96);
			contentPane.add(submitCard2);

			submitCard3 = new SubmitCardGUI();
			// submitCard3.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/3.png")));
			submitCard3.setBounds(286, 224, 71, 96);
			contentPane.add(submitCard3);

			submitCard4 = new SubmitCardGUI();
			// submitCard4.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/4.png")));
			submitCard4.setBounds(162, 163, 71, 96);
			contentPane.add(submitCard4);

			submitCard1 = new SubmitCardGUI();
			// submitCard1.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/1.png")));
			submitCard1.setBounds(286, 85, 71, 96);
			contentPane.add(submitCard1);
		}

		else if (WindowStart.infrastructure.getSeat().charAt(1) == '4') {

			JLabel player4name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.EAST)));
			player4name.setBounds(558, 371, 71, 29);
			contentPane.add(player4name);

			JLabel player3name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.NORTH)));
			player3name.setBounds(558, 197, 71, 29);
			contentPane.add(player3name);

			JLabel player2name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.WEST)));
			player2name.setBounds(286, 8, 71, 29);
			contentPane.add(player2name);

			JLabel player1name = new JLabel(
					WindowStart.infrastructure.getSeatArrange().get((Integer) (Direction.SOUTH)));
			player1name.setBounds(31, 197, 71, 29);
			contentPane.add(player1name);

			submitCard3 = new SubmitCardGUI();
			// submitCard3.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/3.png")));
			submitCard3.setBounds(409, 163, 71, 96);
			contentPane.add(submitCard3);

			submitCard4 = new SubmitCardGUI();
			// submitCard4.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/4.png")));
			submitCard4.setBounds(286, 224, 71, 96);
			contentPane.add(submitCard4);

			submitCard1 = new SubmitCardGUI();
			// submitCard1.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/1.png")));
			submitCard1.setBounds(162, 163, 71, 96);
			contentPane.add(submitCard1);

			submitCard2 = new SubmitCardGUI();
			// submitCard2.setIcon(new
			// ImageIcon(GameWindow.class.getResource("/resource/2.png")));
			submitCard2.setBounds(286, 85, 71, 96);
			contentPane.add(submitCard2);
		}
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

	public void sendMessage() {
		WindowStart.infrastructure
				.submitString("$" + WindowStart.infrastructure.getSeat().substring(2) + ":" + textField.getText());
	}

	public static void changeTable(Card card) {
		if (WindowStart.infrastructure.getSeat().charAt(1) == '1') {
			submitCard1.printIcon(card);
		} else if (WindowStart.infrastructure.getSeat().charAt(1) == '2') {
			submitCard2.printIcon(card);
		} else if (WindowStart.infrastructure.getSeat().charAt(1) == '3') {
			submitCard3.printIcon(card);
		} else if (WindowStart.infrastructure.getSeat().charAt(1) == '4') {
			submitCard4.printIcon(card);
		}
	}
}
