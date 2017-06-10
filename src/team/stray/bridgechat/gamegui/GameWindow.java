package team.stray.bridgechat.gamegui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import team.stray.bridgechat.bridge.Card;
import team.stray.bridgechat.bridge.Suit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;


public class GameWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	static Vector<Card> cardInHand;
	
	public static void main(String[] args) {
		cardInHand = new Vector<>();
		cardInHand.add(new Card(2, '2', Suit.SPADES));
		cardInHand.add(new Card(3, '3', Suit.HEARTS));
		cardInHand.add(new Card(4, '4', Suit.DIAMONDS));
		cardInHand.add(new Card(5, '5', Suit.CLUBS));
		cardInHand.add(new Card(6, '6', Suit.SPADES));
		cardInHand.add(new Card(7, '7', Suit.HEARTS));
		cardInHand.add(new Card(8, '8', Suit.DIAMONDS));
		cardInHand.add(new Card(9, '9', Suit.CLUBS));
		cardInHand.add(new Card(10, 'T', Suit.SPADES));
		cardInHand.add(new Card(11, 'J', Suit.HEARTS));
		cardInHand.add(new Card(12, 'Q', Suit.DIAMONDS));
		cardInHand.add(new Card(13, 'K', Suit.CLUBS));
		cardInHand.add(new Card(14, 'A', Suit.SPADES));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow(cardInHand);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private boolean isMouseHold = false;
	int cardx;
	int cardy;
	public GameWindow(Vector<Card> cardInHand) {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/resource/chip.png")));
		setTitle("Bridgechat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CardGUI card13 = new CardGUI(cardInHand.get(12));
		//card13.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/13.png")));
		card13.setBounds(477, 355, 71, 96);
		contentPane.add(card13);
		
		CardGUI card12 = new CardGUI(cardInHand.get(11));
		//card12.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/12.png")));
		//card12.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/12.png")));
		card12.setBounds(443, 355, 71, 96);
		contentPane.add(card12);
		
		CardGUI card11 = new CardGUI(cardInHand.get(10));
		//card11.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/11.png")));
		//card11.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/11.png")));
		card11.setBounds(409, 355, 71, 96);
		contentPane.add(card11);
		
		CardGUI card10 = new CardGUI(cardInHand.get(9));
		//card10.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/10.png")));
		//card10.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/10.png")));
		card10.setBounds(379, 355, 71, 96);
		contentPane.add(card10);
		
		CardGUI card9 = new CardGUI(cardInHand.get(8));
		//card9.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/9.png")));
		//card9.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/9.png")));
		card9.setBounds(343, 355, 71, 96);
		contentPane.add(card9);
		
		CardGUI card8 = new CardGUI(cardInHand.get(7));
		//card8.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/8.png")));
		//card8.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/8.png")));
		card8.setBounds(312, 355, 71, 96);
		contentPane.add(card8);
		
		CardGUI card7 = new CardGUI(cardInHand.get(6));
		//card7.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/7.png")));
		//card7.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/7.png")));
		card7.setBounds(273, 355, 71, 96);
		contentPane.add(card7);
		
		CardGUI card6 = new CardGUI(cardInHand.get(5));
		//card6.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/6.png")));
		//card6.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/6.png")));
		card6.setBounds(231, 355, 71, 96);
		contentPane.add(card6);
		
		CardGUI card5 = new CardGUI(cardInHand.get(4));
		//card5.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/5.png")));
		//card5.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/5.png")));
		card5.setBounds(192, 355, 71, 96);
		contentPane.add(card5);
		
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
		
		CardGUI card4 = new CardGUI(cardInHand.get(3));
		//card4.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/4.png")));
		//card4.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/4.png")));
		card4.setBounds(152, 355, 71, 96);
		contentPane.add(card4);
		
		CardGUI card3 = new CardGUI(cardInHand.get(2));
		//card3.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/3.png")));
		//card3.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/3.png")));
		card3.setBounds(111, 355, 67, 96);
		contentPane.add(card3);
		
		CardGUI card2 = new CardGUI(cardInHand.get(1));
		//card2.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/2.png")));
		//card2.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/2.png")));
		card2.setBounds(71, 355, 71, 96);
		contentPane.add(card2);
		
		CardGUI card1 = new CardGUI(cardInHand.get(0));
		//card1.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/1.png")));
	/*	card1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(isMouseHold){
					
						card1.setBounds(card1.getX()+e.getX()-35, card1.getY()+e.getY()-48,71,96);
						System.out.println("card1x="+(card1.getX())+"card1y="+(card1.getY()));
						System.out.println("cardxx="+(e.getX())+"cardyy="+(e.getY()));
				}
			}
		});
		
		card1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				isMouseHold = true;
				cardx = card1.getX();
				cardy = card1.getY();
				System.out.println("cardx="+cardx+"cardy="+cardy);
			}
			
			
			@Override
			public void mouseReleased(MouseEvent e) {
				isMouseHold = false;
			}
			
		});*/
	//	card1.setIcon(new ImageIcon(GameWindow.class.getResource("/resource/1.png")));
		
		card1.setBounds(36, 355, 71, 96);
		contentPane.add(card1);
		
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
}
