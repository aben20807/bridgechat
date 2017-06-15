package team.stray.bridgechat.windowgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalBorders.Flush3DBorder;

import com.sun.net.ssl.internal.www.protocol.https.Handler;
import com.sun.xml.internal.ws.developer.StreamingAttachment;

import javafx.scene.control.CheckBox;
import team.stray.bridgechat.bridge.Direction;
import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;
import team.stray.bridgechat.gamegui.GameWindow;

import java.awt.Toolkit;
import java.util.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class WindowSeat extends JFrame {

	private JPanel contentPane;
	private int seatDirection = -1;
	private static boolean seatSouth = false;
	private static boolean seatWest = false;
	private static boolean seatNorth = false;
	private static boolean seatEast = false;
	private static String nameSouth;
	private static String nameWest;
	private static String nameNorth;
	private static String nameEast;
	private static boolean controlThread = true;
	private int count = 0;
	private static String stringReceiveFromServer;
	public static boolean doneSeat = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					WindowSeat frame = new WindowSeat();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public WindowSeat() {

		// can't resize the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setTitle("\u6A4B\u724C123");
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowSeat.class.getResource("/resource/chip.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JCheckBox checkBoxSouth = new JCheckBox("\u5357");
		checkBoxSouth.setFont(new Font("�L�n������", Font.PLAIN, 18));
		checkBoxSouth.setBounds(175, 165, 43, 23);
		contentPane.add(checkBoxSouth);
		checkBoxSouth.setOpaque(false);
		checkBoxSouth.setContentAreaFilled(false);
		checkBoxSouth.setBorderPainted(false);

		JCheckBox checkBoxNorth = new JCheckBox("\u5317");
		checkBoxNorth.setFont(new Font("�L�n������", Font.PLAIN, 18));
		checkBoxNorth.setBounds(175, 43, 43, 23);
		contentPane.add(checkBoxNorth);
		checkBoxNorth.setOpaque(false);
		checkBoxNorth.setContentAreaFilled(false);
		checkBoxNorth.setBorderPainted(false);

		JCheckBox checkBoxEast = new JCheckBox("\u6771");
		checkBoxEast.setFont(new Font("�L�n������", Font.PLAIN, 18));
		checkBoxEast.setBounds(270, 100, 43, 23);
		contentPane.add(checkBoxEast);
		checkBoxEast.setOpaque(false);
		checkBoxEast.setContentAreaFilled(false);
		checkBoxEast.setBorderPainted(false);

		JCheckBox checkBoxWest = new JCheckBox("\u897F");
		checkBoxWest.setFont(new Font("�L�n������", Font.PLAIN, 18));
		checkBoxWest.setBounds(70, 100, 43, 23);
		contentPane.add(checkBoxWest);
		checkBoxWest.setOpaque(false);
		checkBoxWest.setContentAreaFilled(false);
		checkBoxWest.setBorderPainted(false);

		ButtonGroup group = new ButtonGroup();
		group.add(checkBoxNorth);
		group.add(checkBoxWest);
		group.add(checkBoxEast);
		group.add(checkBoxSouth);

		JButton btnCheckSeat = new JButton("\u78BA\u8A8D\u5EA7\u4F4D");
		btnCheckSeat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!seatSouth) {
					if (checkBoxSouth.isSelected()) {
						seatDirection = Direction.SOUTH;
						btnCheckSeat.setEnabled(false);
						checkBoxNorth.setEnabled(false);
						checkBoxWest.setEnabled(false);
						checkBoxEast.setEnabled(false);
						checkBoxSouth.setEnabled(false);						WindowStart.infrastructure.submitString("@" + seatDirection + WindowStart.infrastructure.getName());
						WindowStart.infrastructure.setSeat("@" + seatDirection + WindowStart.infrastructure.getName());
					}
				} else if (seatSouth && checkBoxSouth.isSelected()) {
					checkBoxSouth.setSelected(false);
					
					JFrame stupid = new JFrame();
					stupid.setSize(200, 100);
					JDialog.setDefaultLookAndFeelDecorated(true);
					stupid.setSize(400, 300);
					stupid.setLocationRelativeTo(null);
					Container cp = stupid.getContentPane();
					cp.setLayout(null);
					// stupid.setVisible(true);
					JOptionPane.showMessageDialog(stupid, "南方已被選走啦", "位子已被選取", JOptionPane.WARNING_MESSAGE);
					stupid.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
				if (!seatWest) {
					if (checkBoxWest.isSelected()) {
						seatDirection = Direction.WEST;
						btnCheckSeat.setEnabled(false);
						checkBoxNorth.setEnabled(false);
						checkBoxWest.setEnabled(false);
						checkBoxEast.setEnabled(false);
						checkBoxSouth.setEnabled(false);						WindowStart.infrastructure.submitString("@" + seatDirection + WindowStart.infrastructure.getName());
						WindowStart.infrastructure.setSeat("@" + seatDirection + WindowStart.infrastructure.getName());
					}
				} else if (seatWest && checkBoxWest.isSelected()) {
					checkBoxWest.setSelected(false);
					
					JFrame stupid = new JFrame();
					stupid.setSize(200, 100);
					JDialog.setDefaultLookAndFeelDecorated(true);
					stupid.setSize(400, 300);
					stupid.setLocationRelativeTo(null);
					Container cp = stupid.getContentPane();
					cp.setLayout(null);
					// stupid.setVisible(true);
					JOptionPane.showMessageDialog(stupid, "西方已被選走啦", "位子已被選取", JOptionPane.WARNING_MESSAGE);
					stupid.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
				if (!seatNorth) {
					if (checkBoxNorth.isSelected()) {
						seatDirection = Direction.NORTH;
						btnCheckSeat.setEnabled(false);
						checkBoxNorth.setEnabled(false);
						checkBoxWest.setEnabled(false);
						checkBoxEast.setEnabled(false);
						checkBoxSouth.setEnabled(false);						WindowStart.infrastructure.submitString("@" + seatDirection + WindowStart.infrastructure.getName());
						WindowStart.infrastructure.setSeat("@" + seatDirection + WindowStart.infrastructure.getName());
					}
				} else if (seatNorth && checkBoxNorth.isSelected()) {
					checkBoxNorth.setSelected(false);
					
					JFrame stupid = new JFrame();
					stupid.setSize(200, 100);
					JDialog.setDefaultLookAndFeelDecorated(true);
					stupid.setSize(400, 300);
					stupid.setLocationRelativeTo(null);
					Container cp = stupid.getContentPane();
					cp.setLayout(null);
					// stupid.setVisible(true);
					JOptionPane.showMessageDialog(stupid, "北方已被選走啦", "位子已被選取", JOptionPane.WARNING_MESSAGE);
					stupid.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
				if (!seatEast) {
					if (checkBoxEast.isSelected()) {
						seatDirection = Direction.EAST;
						btnCheckSeat.setEnabled(false);
						checkBoxNorth.setEnabled(false);
						checkBoxWest.setEnabled(false);
						checkBoxEast.setEnabled(false);
						checkBoxSouth.setEnabled(false);
						WindowStart.infrastructure.submitString("@" + seatDirection + WindowStart.infrastructure.getName());
						WindowStart.infrastructure.setSeat("@" + seatDirection + WindowStart.infrastructure.getName());
					}
				} else if (seatEast && checkBoxEast.isSelected()) {
					checkBoxWest.setSelected(false);
					
					JFrame stupid = new JFrame();
					stupid.setSize(200, 100);
					JDialog.setDefaultLookAndFeelDecorated(true);
					stupid.setSize(400, 300);
					stupid.setLocationRelativeTo(null);
					Container cp = stupid.getContentPane();
					cp.setLayout(null);
					// stupid.setVisible(true);
					JOptionPane.showMessageDialog(stupid, "東方已被選走啦", "位子已被選取", JOptionPane.WARNING_MESSAGE);
					stupid.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
				
				
				
//				if ( (checkBoxSouth.isSelected() && !seatSouth) || 
//						(checkBoxWest.isSelected() && !seatWest) || 
//							(checkBoxNorth.isSelected() && !seatNorth) ||
//								(checkBoxEast.isSelected()) && !seatEast) {
//
//					checkBoxNorth.setEnabled(false);
//					checkBoxWest.setEnabled(false);
//					checkBoxEast.setEnabled(false);
//					checkBoxSouth.setEnabled(false);
//				}
//				if (checkBoxSouth.isSelected()) {
//					seatDirection = Direction.SOUTH;
//					btnCheckSeat.setEnabled(false);
//				}
//				if (checkBoxWest.isSelected()) {
//					seatDirection = Direction.WEST;
//					btnCheckSeat.setEnabled(false);
//				}
//				if (checkBoxNorth.isSelected()) {
//					seatDirection = Direction.NORTH;
//					btnCheckSeat.setEnabled(false);
//				}
//				if (checkBoxEast.isSelected()) {
//					seatDirection = Direction.EAST;
//					btnCheckSeat.setEnabled(false);
//				}
//				if (checkBoxSouth.isSelected() || checkBoxWest.isSelected() || checkBoxNorth.isSelected()
//												|| checkBoxEast.isSelected()) {
//				}

//				WindowStart.infrastructure.submitString("@" + seatDirection + WindowStart.infrastructure.getName());
//				WindowStart.infrastructure.setSeat("@" + seatDirection + WindowStart.infrastructure.getName());

			}
		});
		btnCheckSeat.setFont(new Font("�L�n������", Font.PLAIN, 12));
		btnCheckSeat.setBounds(324, 228, 87, 23);
		contentPane.add(btnCheckSeat);

		JLabel lblNameSouth = new JLabel(" ");
		lblNameSouth.setFont(new Font("�L�n������", Font.PLAIN, 16));
		lblNameSouth.setBounds(175, 194, 100, 23);
		contentPane.add(lblNameSouth);

		JLabel lblNameNorth = new JLabel(" ");
		lblNameNorth.setFont(new Font("�L�n������", Font.PLAIN, 16));
		lblNameNorth.setBounds(175, 71, 100, 23);
		contentPane.add(lblNameNorth);

		JLabel lblNameEast = new JLabel(" ");
		lblNameEast.setFont(new Font("�L�n������", Font.PLAIN, 16));
		lblNameEast.setBounds(270, 129, 100, 23);
		contentPane.add(lblNameEast);

		JLabel lblNameWest = new JLabel(" ");
		lblNameWest.setFont(new Font("�L�n������", Font.PLAIN, 16));
		lblNameWest.setBounds(70, 129, 100, 23);
		contentPane.add(lblNameWest);

		JButton btnEnterGame = new JButton("\u9032\u5165\u904A\u6232");
		btnEnterGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GameWindow.openGameGui();
				dispose();
			}
		});
		btnEnterGame.setFont(new Font("�L�n������", Font.PLAIN, 12));
		btnEnterGame.setBounds(324, 195, 87, 23);
		contentPane.add(btnEnterGame);
		btnEnterGame.setEnabled(false);

		JButton btnRefreshSeat = new JButton("\u66F4\u65B0");
		btnRefreshSeat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (seatSouth) {
					checkBoxSouth.setSelected(false);
					checkBoxSouth.setEnabled(false);
					lblNameSouth.setText(nameSouth);		
				}
				if (seatWest) {
					checkBoxWest.setSelected(false);
					checkBoxWest.setEnabled(false);
					lblNameWest.setText(nameWest);				
				}
				if (seatNorth) {
					checkBoxNorth.setSelected(false);
					checkBoxNorth.setEnabled(false);
					lblNameNorth.setText(nameNorth);		
				}
				if (seatEast) {
					checkBoxEast.setSelected(false);
					checkBoxEast.setEnabled(false);
					lblNameEast.setText(nameEast);
				}
				//
				if (seatSouth && seatWest && seatNorth && seatEast && !lblNameSouth.getText().equals(" ")
						&& !lblNameSouth.getText().equals(" ") && !lblNameWest.getText().equals(" ")
						&& !lblNameEast.getText().equals(" ")) {
					btnEnterGame.setEnabled(true);
					btnRefreshSeat.setEnabled(false);

					// for( int i = 0 ; i < 100000000 ; i++ ){
					// System.out.flush();
					// }
					// try {
					// Thread.sleep(3000);
					// } catch (InterruptedException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }

					// try test timer
					// class DateTask extends TimerTask {
					// public void run() {
					// System.out.println("���Ȯɶ��G" + new Date());
					// }
					// }
					// Timer timer = new Timer();
					// timer.schedule(new DateTask(),5000);
					// System.out.println("�{�b�ɶ��G" + new Date());
					// try {
					// Thread.sleep(8000);
					// }
					// catch(InterruptedException e) {
					// }
					// timer.cancel();

					// dispose(); // close the windowSeat

				}
				// if( seatSouth && seatWest && seatNorth && seatEast ){
				//
				// try {
				// Thread.sleep(3000);
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				//
				// }
			}
		});
		btnRefreshSeat.setBounds(10, 10, 87, 23);
		contentPane.add(btnRefreshSeat);

		// new Thread() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				// System.out.println("windowseeeeeeeeeeeeeeeat");
				try {
					// System.out.println("windowseeeeeeeeeeeeeeeat2");
					while (controlThread) {
						// System.out.println("windowseeeeeeeeeeeeeeeat3");
						System.out.flush();
						Transmissible messageReceiveFromServer;
						if (WindowStart.infrastructure != null && WindowStart.infrastructure.getMessage() != null) {
							messageReceiveFromServer = WindowStart.infrastructure.getMessage();
							if (messageReceiveFromServer instanceof TransmissibleString) {
								stringReceiveFromServer = ((TransmissibleString) messageReceiveFromServer)
										.getTransmissibleString();
								// System.out.println("windowseeeeeeeeeeeeeeeat"
								// + stringReceiveFromServer);
								// System.out.println((int)
								// (stringReceiveFromServer.charAt(1) - '0'));
								if (stringReceiveFromServer.length() != 0 && stringReceiveFromServer.charAt(0) == '@') {
									switch ((int) (stringReceiveFromServer.charAt(1) - '0')) {
									case Direction.SOUTH:
										seatSouth = true;
										nameSouth = stringReceiveFromServer.substring(2);
										break;
									case Direction.WEST:
										seatWest = true;
										nameWest = stringReceiveFromServer.substring(2);
										break;
									case Direction.NORTH:
										seatNorth = true;
										nameNorth = stringReceiveFromServer.substring(2);
										break;
									case Direction.EAST:
										seatEast = true;
										nameEast = stringReceiveFromServer.substring(2);
										break;

									}
								}
							}
						}
						if (!lblNameSouth.getText().equals(" ")) {
							if (!lblNameWest.getText().equals(" ")) {
								if (!lblNameNorth.getText().equals(" ")) {
									if (!lblNameEast.getText().equals(" ")) {
										System.out.println("stop the thread");
										doneSeat = true;
										controlThread = false;
									}
								}
							}
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});// .start();
		thread.start();
	}
}
