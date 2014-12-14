package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import model.FindTypeEnum;

public class MainWindow extends JFrame{
	/**
	 * 
	 */	
	private static final long serialVersionUID = -2278436951424873713L;
	
	private JRadioButton chkByTitle = null;
	private JRadioButton chkByString = null;
	private JTextField txtTextToFind = null;
	private FindButton fbtFind = null;
	private JPanel pnlUpper = null;
	private PPTPanel pnlDown = null;
	private ComboPPT cmbPowerpoints = null;
	private JMenuBar jmbMenuBar = null;
	private JMenu 	jmbConfiguracion = null; 
	private JMenuItem itemConfiguration = null;

	public MainWindow() {
		initialize();		
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainWindow();					
			}
		});
			

	}
	

	public JRadioButton getChkByTitle() {
		if (chkByTitle == null) {
			chkByTitle = new JRadioButton();
			chkByTitle.setText("Por título ");
			chkByTitle.setSelected(true);
			chkByTitle.setToolTipText("Filtrar por título");
			//chkByTitle.setBackground(new Color(162, 46, 186));
			chkByTitle.addActionListener(new ActionListener() {
				

				public void actionPerformed(ActionEvent arg0) {					 
					if (chkByTitle.isSelected()) {
						getTxtTextToFind().setText("");
					}
				}
			});
		}
		return chkByTitle;
	}

	public JRadioButton getChkByString() {
		if (chkByString == null) {
			chkByString = new JRadioButton();
			chkByString.setToolTipText("Filtrar por texto");
			chkByString.setText("Por texto");
			//chkByString.setBackground(new Color(162, 46, 186));
		}
		return chkByString;
	}

	public JTextField getTxtTextToFind() {
		if (txtTextToFind == null) {
			txtTextToFind = new JTextField();
			txtTextToFind.setPreferredSize(new Dimension(400,20));
			txtTextToFind.setMinimumSize(new Dimension(400,20));
			txtTextToFind.setMaximumSize(new Dimension(400,20));
			txtTextToFind.setToolTipText("Ingrese el texto a buscar");
			txtTextToFind.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						getFbtFind().doClick();
					}
					
				}
			});
		}
		return txtTextToFind;
	}

	public FindButton getFbtFind() {
		if(fbtFind == null) {
			fbtFind = new FindButton();
			fbtFind.setText("Buscar");
			fbtFind.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {	
					getPnlDown().clear();
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					//TODOLoadingDialog loadDialog = new LoadingDialog(MainWindow.this);
					try {
						if(getChkByString().isSelected())							
							fbtFind.find(getTxtTextToFind().getText().trim(), getPnlDown().getTableModel(), FindTypeEnum.BY_WORDS);							
						else						
							fbtFind.find(getCmbPowerpoints().getFileNameSelected(), getPnlDown().getTableModel(), FindTypeEnum.BY_TITLE);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					//loadDialog.dispose();
				}
			});
		}
		return fbtFind;
	}
	
	public JPanel getPnlUpper() {
		if(pnlUpper == null) {
			pnlUpper = new JPanel();
			pnlUpper.setLayout(new BoxLayout(pnlUpper, BoxLayout.Y_AXIS));
			//pnlUpper.setBackground(new Color(162, 46, 186));
			ButtonGroup gr = new ButtonGroup();
			gr.add(getChkByString());
			gr.add(getChkByTitle());
			Box uno = Box.createHorizontalBox();
			uno.add(getChkByString());uno.add(Box.createHorizontalStrut(6));
			uno.add(getTxtTextToFind());
			uno.add(Box.createHorizontalStrut(118));
			uno.add(getFbtFind());
			uno.add(Box.createHorizontalGlue());
			
			Box dos = Box.createHorizontalBox();
			dos.add(getChkByTitle());
			dos.add(getCmbPowerpoints());
			dos.add(Box.createHorizontalGlue());
			
			pnlUpper.setPreferredSize(new Dimension(600,60));
			pnlUpper.add(Box.createVerticalStrut(10));
			pnlUpper.add(dos);
			pnlUpper.add(uno);
		}
		return pnlUpper;
	}
	
	
	public PPTPanel getPnlDown() {
		if (pnlDown == null) {
			pnlDown = new PPTPanel();
		}		
		return pnlDown;
	}


	private void initialize() {
		
		this.getContentPane().setLayout(new BorderLayout());	
		this.setIconImage(getIcon());
		this.setJMenuBar(getJmbMenuBar());
		this.getContentPane().add(getPnlUpper(),BorderLayout.NORTH);
		this.getContentPane().add(Box.createVerticalStrut(10));
		this.getContentPane().add(getPnlDown(),BorderLayout.SOUTH);				
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);			
		setTitle("Powerpoint Show");	
		setLocationByPlatform(true);
		this.setResizable(false);		
		setColorComponents(this);
	}
	
	
	private Image getIcon() {
		URL url = ClassLoader.getSystemResource("img/icon.png");
		return Toolkit.getDefaultToolkit().createImage(url);
	}

	public ComboPPT getCmbPowerpoints() {
		if (cmbPowerpoints == null) {
			cmbPowerpoints = new ComboPPT();
			cmbPowerpoints.setMinimumSize(new Dimension(400,20));
			cmbPowerpoints.setMaximumSize(new Dimension(400,20));
			cmbPowerpoints.setPreferredSize(new Dimension(400,20));
		}		
		return cmbPowerpoints;
	}

	public JMenuBar getJmbMenuBar() {
		if(jmbMenuBar == null) {
			jmbMenuBar = new JMenuBar();
			jmbMenuBar.add(getJmbConfiguracion());			
		}
		
		return jmbMenuBar;
	}
	
	private JMenu getJmbConfiguracion(){
		if (jmbConfiguracion == null) {
			jmbConfiguracion = new JMenu("Opciones");
			jmbConfiguracion.add(getItemConfiguration());
			jmbConfiguracion.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		}
		
		return jmbConfiguracion;
	}

	
	private JMenuItem getItemConfiguration() {
		if (itemConfiguration == null) {
			itemConfiguration = new JMenuItem("Configuración");
			itemConfiguration.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new ConfigurationWindow(MainWindow.this).setVisible(true);
					getCmbPowerpoints().load();
				}
			});
		}
		
		return itemConfiguration;
	}	
	
	private void setColorComponents(Container container) {
		Component[] components = container.getComponents();
			for (Component c : components) {
				if (c instanceof JTextArea 
						|| c instanceof JTextField || c instanceof Border)
					continue;
				
//				c.setBackground(new Color(186, 117 , 255));
				c.setBackground(new Color(231, 206 , 255));				
				c.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
				
				
				if (c instanceof Container)
					setColorComponents(((Container) c));					
			}
	}
	
}
