package team.stray.bridgechat.windowgui;

import team.stray.bridgechat.*;
import team.stray.bridgechat.bridge.Direction;

import team.stray.bridgechat.connect.Transmissible;
import team.stray.bridgechat.connect.transmissible.TransmissibleString;
import team.stray.bridgechat.gamegui.GameWindow;
//import team.stray.bridgechat.gamegui.GameWindowTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import com.sun.prism.paint.Stop;

//import .WindowSeat;

public class WindowStart {

	private JFrame frame;
	private JTextField inputIP;
	private JTextField inputName;
	public static Infrastructure infrastructure = null;
	private static String stringReceiveFromServer;
	private static boolean isRoomFull = false;
	private static WindowLoad windowLoad;
	private static WindowSeat windowSeat;
	private static boolean stopThreadOfLoad = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		infrastructure = new InfrastructureImpl();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowStart window = new WindowStart();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// GameWindow.openGameGui();
	}

	/**
	 * Create the application.
	 */
	public WindowStart() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setFont(new Font("微軟正黑體", Font.BOLD, 12));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(WindowStart.class.getResource("/resource/chip.png")));
		frame.setTitle("\u6A4B\u724C123");
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		inputIP = new JTextField();
		inputIP.setBounds(266, 100, 96, 21);
		frame.getContentPane().add(inputIP);
		inputIP.setColumns(10);
		inputIP.setVisible(false);

		inputName = new JTextField();
		inputName.setBounds(266, 152, 96, 21);
		frame.getContentPane().add(inputName);
		inputName.setColumns(10);

		JLabel labelName = new JLabel("\u60A8\u7684\u5927\u540D :");
		labelName.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		labelName.setBounds(169, 147, 87, 22);
		frame.getContentPane().add(labelName);

		JLabel labelIP = new JLabel("IP\uFF1A");
		labelIP.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		labelIP.setBounds(219, 97, 35, 18);
		frame.getContentPane().add(labelIP);
		labelIP.setVisible(false);

		JRadioButton btnEnterRoom = new JRadioButton("\u9032\u5165\u623F\u9593");

		btnEnterRoom.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		btnEnterRoom.setBounds(235, 39, 119, 35);
		frame.getContentPane().add(btnEnterRoom);
		// set the background transparent
		btnEnterRoom.setOpaque(false);
		btnEnterRoom.setContentAreaFilled(false);
		btnEnterRoom.setBorderPainted(false);

		JRadioButton btnBuildRoom = new JRadioButton("\u5EFA\u7ACB\u623F\u9593");
		btnBuildRoom.setBackground(Color.WHITE);
		btnBuildRoom.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		btnBuildRoom.setBounds(60, 39, 119, 35);
		frame.getContentPane().add(btnBuildRoom);
		// set the background transparent
		btnBuildRoom.setOpaque(false);
		btnBuildRoom.setContentAreaFilled(false);
		btnBuildRoom.setBorderPainted(false);

		// add two radiobuttons to group let them can only choose only one
		ButtonGroup group = new ButtonGroup();
		group.add(btnBuildRoom);
		group.add(btnEnterRoom);

		// btnEnterRoom.addActionListener(this);

		btnEnterRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnEnterRoom.isSelected()) {
					// btnBuildRoom.setSelected(false);

					inputIP.setVisible(true);
					labelIP.setVisible(true);
				}
			}

		});
		btnBuildRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnBuildRoom.isSelected()) {
					// btnEnterRoom.setSelected(false);

					inputIP.setVisible(false);
					labelIP.setVisible(false);
				}
			}
		});

		JLabel labelUserIP = new JLabel("IP :");
		labelUserIP.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 12));
		labelUserIP.setBounds(10, 10, 124, 15);
		frame.getContentPane().add(labelUserIP);
		// System.out.println(getIP());
		labelUserIP.setText("\u672C\u5730IP : " + getIP());

		JButton btnStart = new JButton("\u958B\u59CB");
		btnStart.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// success into the room
				if (btnBuildRoom.isSelected() || (btnEnterRoom.isSelected() && !inputIP.getText().equals(""))) {

					infrastructure.setName(inputName.getText()); // set user
																	// name
					System.out.println(inputName.getText());
					if (btnEnterRoom.isSelected()) {
						infrastructure.setConnectionIP(inputIP.getText()); // set
																			// client
																			// connection
																			// ip
						System.out.println(inputIP.getText());
						infrastructure.connectRoom(); // connect to the input ip
														// room
					}
					if (btnBuildRoom.isSelected()) {
						infrastructure.openRoom();
					}

					/*
					 * GameWindow frame1 = new GameWindow(); try{
					 * Thread.sleep(10000); } catch(Exception e){
					 * e.printStackTrace(); } if
					 * (WindowStart.infrastructure.getType() ==
					 * Infrastructure.SERVER) { frame1.cut(); }
					 * 
					 * frame1.updateDeck(); frame1.setVisible(true);
					 * frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					 */

					/******************************************
					 * Test for Game GUI // GameWindow game = new GameWindow();
					 * // game.openGameGui();
					 *******************************************/
					/******************************************
					 * Test for WindowSeat WindowSeat windowSeat = new
					 * WindowSeat(); // new windowSeat.setVisible(true);
					 *******************************************/
					// WindowLoad windowLoad;
					
					try {
						windowLoad = new WindowLoad();
						windowLoad.setVisible(true);
						windowLoad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
//					 WindowSeat windowSeat = new WindowSeat();
//					 windowSeat.setVisible(true);

					frame.setVisible(false);

				} else if (!btnBuildRoom.isSelected() && !btnEnterRoom.isSelected()) { // no
																						// press
																						// buildroom
																						// btn
																						// &
																						// enterroom
																						// btn
					JFrame stupid = new JFrame();
					stupid.setSize(200, 100);
					JDialog.setDefaultLookAndFeelDecorated(true);
					stupid.setSize(400, 300);
					stupid.setLocationRelativeTo(null);
					Container cp = stupid.getContentPane();
					cp.setLayout(null);
					// stupid.setVisible(true);
					JOptionPane.showMessageDialog(stupid, "請進入房間 或建立房間", "瘟腥提醒", JOptionPane.ERROR_MESSAGE);
					stupid.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				} else if (inputIP.getText().equals("") || !btnBuildRoom.isSelected()) { // no
																							// input
																							// ip
																							// or
																							// no
																							// build
																							// room

					JFrame stupid = new JFrame();
					stupid.setSize(200, 100);
					JDialog.setDefaultLookAndFeelDecorated(true);
					stupid.setSize(400, 300);
					stupid.setLocationRelativeTo(null);
					Container cp = stupid.getContentPane();
					cp.setLayout(null);
					// stupid.setVisible(true);
					JOptionPane.showMessageDialog(stupid, "請輸入ip\n或建立房間", "瘟腥提醒", JOptionPane.ERROR_MESSAGE);
					stupid.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStart.setBounds(233, 209, 87, 23);
		frame.getContentPane().add(btnStart);

		Thread thread = new Thread(new Runnable() {
			public void run() {
				// System.out.println("windowseeeeeeeeeeeeeeeat");
				try {
//					 System.out.println("windowseeeeeeeeeeeeeeeat2");
					while ( stopThreadOfLoad ) {
//						 System.out.println("windowseeeeeeeeeeeeeeeat3");
						
						System.out.flush();
						Transmissible messageReceiveFromServer;
						if (infrastructure != null && infrastructure.getMessage() != null) {
							messageReceiveFromServer = infrastructure.getMessage();
							if (messageReceiveFromServer instanceof TransmissibleString) {
								stringReceiveFromServer = ((TransmissibleString) messageReceiveFromServer)
										.getTransmissibleString();

								if (stringReceiveFromServer.length() != 0
										&& stringReceiveFromServer.equals("ROOM_FULL")) {
									System.out.println(stringReceiveFromServer + "test1");
									Thread.sleep(10870);
									windowLoad.dispose();

									// windowSeat.setVisible(true);

									WindowSeat windowSeat = new WindowSeat();
									windowSeat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									windowSeat.setVisible(true);
									stopThreadOfLoad = false;

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

	public String getIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "";
		}
	}
}
