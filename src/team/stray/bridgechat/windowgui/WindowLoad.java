package team.stray.bridgechat.windowgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JLabel;

public class WindowLoad extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowLoad frame = new WindowLoad();
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
	 * 
	 * @throws MalformedURLException
	 */
	public WindowLoad() throws MalformedURLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowLoad.class.getResource("/resource/chip.png")));
		setTitle("\u6A4B\u724C123");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 331, 418);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
//			URL url = new URL("https://media.giphy.com/media/sSgvbe1m3n93G/giphy.gif");
//			ImageIcon icon = new ImageIcon(url);
			ImageIcon icon = new ImageIcon(new URL("https://i.giphy.com/3o8doNAGKZXsrsgzW8.gif"));

			JLabel label = new JLabel(icon);
			label.setText("Hello");
			label.setBounds(0, 0, 335, 390);
			contentPane.add(label);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
