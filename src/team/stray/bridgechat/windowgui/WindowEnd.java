package team.stray.bridgechat.windowgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class WindowEnd extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowEnd frame = new WindowEnd();
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
	public WindowEnd() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowEnd.class.getResource("/resource/chip.png")));
		setTitle("\u904A\u6232\u7D50\u675F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWinner = new JLabel("\u8D0F\u5BB6 \uFF1A");
		lblWinner.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		lblWinner.setBounds(55, 98, 99, 33);
		contentPane.add(lblWinner);
		
		JLabel label = new JLabel("\u8F38\u5BB6 \uFF1A");
		label.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		label.setBounds(55, 145, 99, 33);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("\u91CD\u65B0\u958B\u59CB");
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		btnNewButton.setBounds(100, 200, 99, 33);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u904A\u6232\u7D50\u675F");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		button.setBounds(240, 200, 99, 33);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("G a m e   O v e r");
		lblNewLabel.setForeground(new Color(135, 206, 235));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 35));
		lblNewLabel.setBounds(66, 30, 310, 48);
		contentPane.add(lblNewLabel);
	}
}
