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
import java.awt.Font;

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
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			 
			
//			URL url = new URL("https://media.giphy.com/media/sSgvbe1m3n93G/giphy.gif");
//			ImageIcon icon = new ImageIcon(url);
//			ImageIcon icon = new ImageIcon("resource/ellipsis.gif","test");
//			label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
			ClassLoader cldr = this.getClass().getClassLoader();  					// set my resource 
			java.net.URL imageURL   = cldr.getResource("resource/ellipsis.gif");  
			ImageIcon icon2 = new ImageIcon(imageURL); 
			JLabel labelGifLoading = new JLabel(icon2);
			labelGifLoading.setBounds(152, 22, 145, 15);
			contentPane.add(labelGifLoading);
			
			JLabel labelTextLoading = new JLabel("L o a d i n g ");
			labelTextLoading.setFont(new Font("AR ESSENCE", Font.PLAIN, 18));
			labelTextLoading.setBounds(32, 14, 106, 23);
			contentPane.add(labelTextLoading);

			ClassLoader cldr2 = this.getClass().getClassLoader();  					// set my resource 
			java.net.URL imageURL2 = cldr2.getResource("resource/loading.gif");
			ImageIcon icon = new ImageIcon(imageURL2); 
			//ImageIcon icon = new ImageIcon(new URL("https://i.giphy.com/3o8doNAGKZXsrsgzW8.gif"));	
			JLabel labelGifBg = new JLabel(icon);
			labelGifBg.setBounds(0, 0, 335, 390);
			contentPane.add(labelGifBg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
