package gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class LoadingDialog extends JDialog{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 698023747642764243L;
	private JLabel photo;
	private JLabel text;
	private JPanel panel;
	
	public LoadingDialog(JFrame frame) {
		setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setPreferredSize(new Dimension(300,180));
		setMinimumSize(new Dimension(300,180));
		getContentPane().add(getPanel());
		//setLocationRelativeTo(frame);        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setVisible(true);

		
		
	}
	
	private ImageIcon getImageIcon() {
		URL url = ClassLoader.getSystemResource("img/loading.gif");
		//return new ImageIcon(url);
		ImageIcon image = new ImageIcon(url);  
		int scale = 3;  
		  
		int width = image.getIconWidth();  
		int height = image.getIconHeight();  
		BufferedImage buffer = new BufferedImage(scale * width, scale * height, BufferedImage.TYPE_INT_ARGB);  
		Graphics2D graphics = buffer.createGraphics();  
		graphics.scale(scale,scale);  
		image.paintIcon(null, graphics, 0, 0);  
		graphics.dispose();
		
		return new ImageIcon(buffer);
	}
	
	private Image getIcon() {
		URL url = ClassLoader.getSystemResource("img/icon.png");
		return Toolkit.getDefaultToolkit().createImage(url);
	}
	
	private JLabel getPhoto() {
		if (photo == null) {
			photo = new JLabel("Buscando");
			
		}
		return photo;
	}
	
	private JLabel getText() {
		if (text == null) {
			text = new JLabel("Buscando");
			
		}
		return text;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getPhoto());
			
		}
		return panel;
	}
	
}
