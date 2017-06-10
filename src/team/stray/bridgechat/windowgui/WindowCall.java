package team.stray.bridgechat.windowgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class WindowCall extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowCall frame = new WindowCall();
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
	public WindowCall() {
		getContentPane().setBackground(new Color(204, 255, 204));
		getContentPane().setLayout(null);
		
		JToggleButton btnFive = new JToggleButton("");
		btnFive.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/num_five.png")));
		btnFive.setBounds(281, 256, 50, 50);
		getContentPane().add(btnFive);
		
		JToggleButton btnFour = new JToggleButton("");
		btnFour.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/num_four.png")));
		btnFour.setBounds(215, 256, 50, 50);
		getContentPane().add(btnFour);
		
		JToggleButton btnSeven = new JToggleButton("");
		btnSeven.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/num_seven.png")));
		btnSeven.setBounds(413, 256, 50, 50);
		getContentPane().add(btnSeven);
		
		JToggleButton btnSix = new JToggleButton("");
		btnSix.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/num_six.png")));
		btnSix.setBounds(347, 256, 50, 50);
		getContentPane().add(btnSix);
		
		JToggleButton btnOne = new JToggleButton("");
		btnOne.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/num_one.png")));
		btnOne.setBounds(20, 256, 50, 50);
		getContentPane().add(btnOne);
		
		JToggleButton btnThree = new JToggleButton("");
		btnThree.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/num_three.png")));
		btnThree.setBounds(149, 256, 50, 50);
		getContentPane().add(btnThree);
		
		JToggleButton btnClover = new JToggleButton("");
		btnClover.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/clover.png")));
		btnClover.setBounds(215, 196, 50, 50);
		getContentPane().add(btnClover);
		
		JToggleButton btnHeart = new JToggleButton("");
		btnHeart.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/heart.png")));
		btnHeart.setBounds(83, 196, 50, 50);
		getContentPane().add(btnHeart);
		
		JToggleButton btnDiamond = new JToggleButton("");
		btnDiamond.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/diamond.png")));
		btnDiamond.setBounds(149, 196, 50, 50);
		getContentPane().add(btnDiamond);
		
		JToggleButton btnTwo = new JToggleButton("");
		btnTwo.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/num_two.png")));
		btnTwo.setBounds(83, 256, 50, 50);
		getContentPane().add(btnTwo);
		
		JToggleButton btnNT = new JToggleButton("");
		btnNT.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/no_trump_1.png")));
		btnNT.setBounds(281, 196, 50, 50);
		getContentPane().add(btnNT);
		
		JToggleButton btnCheck = new JToggleButton("");
		btnCheck.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/check..png")));
		btnCheck.setBounds(413, 196, 50, 50);
		getContentPane().add(btnCheck);
		
		JToggleButton btnSpade = new JToggleButton("");
		btnSpade.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/Spade.png")));
		btnSpade.setBounds(17, 196, 50, 50);
		getContentPane().add(btnSpade);
		setTitle("\u6A4B\u724C123");
		
		JToggleButton btnPass = new JToggleButton("");
		
		btnPass.setIcon(new ImageIcon(WindowCall.class.getResource("/resource/passs.png")));
		btnPass.setBounds(347, 196, 50, 50);
		getContentPane().add(btnPass);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowCall.class.getResource("/resource/chip.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		
		ButtonGroup groupSuit = new ButtonGroup();
		groupSuit.add(btnSpade);
		groupSuit.add(btnDiamond);
		groupSuit.add(btnHeart);
		groupSuit.add(btnClover);
		groupSuit.add(btnNT);
//		groupSuit.add(btnPass);
		
		ButtonGroup groupNumber = new ButtonGroup();
		groupNumber.add(btnOne);
		groupNumber.add(btnTwo);
		groupNumber.add(btnThree);
		groupNumber.add(btnFour);
		groupNumber.add(btnFive);
		groupNumber.add(btnSix);
		groupNumber.add(btnSeven);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(215, 27, 46, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(215, 153, 46, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(83, 81, 46, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(351, 81, 46, 15);
		getContentPane().add(lblNewLabel_3);
//		groupNumber.add(btnPass);
		
		btnPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					groupSuit.clearSelection();
					groupNumber.clearSelection();
					btnPass.setSelected(true);
			}
		});

		btnSpade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					btnPass.setSelected(false);
			}
		});
		btnDiamond.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnHeart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnClover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnNT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnTwo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnFive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnSix.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnSeven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPass.setSelected(false);
			}
		});
		btnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((groupSuit.getSelection() != null && groupNumber.getSelection() != null) || btnPass.isSelected()){
					btnCheck.setEnabled(false);
					btnSpade.setEnabled(false);
					btnDiamond.setEnabled(false);
					btnClover.setEnabled(false);
					btnNT.setEnabled(false);
					btnPass.setEnabled(false);
					btnHeart.setEnabled(false);
					btnOne.setEnabled(false);
					btnTwo.setEnabled(false);
					btnThree.setEnabled(false);
					btnFour.setEnabled(false);
					btnFive.setEnabled(false);
					btnSix.setEnabled(false);
					btnSeven.setEnabled(false);	
				}
			}
		});
	}
}
