package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.impl.ShowPPTImpl;
import logic.interfaces.ShowPPT;
import model.Powerpoint;

public class PPTPanel extends JSplitPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1880227302919116141L;
	private JTable tblPowerpoint;
	private JTextArea txtArea;
	private  GenericTableModel<Powerpoint> tableModel;
	
	public PPTPanel() {
		super(JSplitPane.HORIZONTAL_SPLIT);
		this.setLeftComponent(new JScrollPane(getTblPowerpoint()));
		this.setRightComponent(new JScrollPane(getTxtArea()));
		this.setMinimumSize(new Dimension(800, 500));
		this.setPreferredSize(new Dimension(800, 500));
		this.setOneTouchExpandable(false);
		this.setDividerLocation(400);
		Dimension minimumSize = new Dimension(400, 50);
		getTxtArea().setMinimumSize(minimumSize);
		getTblPowerpoint().setMinimumSize(minimumSize);
	}

	public JTable getTblPowerpoint() {
		if (tblPowerpoint == null) {
			tblPowerpoint = new JTable(getTableModel());
			tblPowerpoint.setShowGrid(true);
			tblPowerpoint.setAutoCreateRowSorter(true);
			tblPowerpoint.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tblPowerpoint.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent mouseEvent) {
					if(mouseEvent.getClickCount() < 2){
						mouseEvent.consume();
						if (tblPowerpoint.getSelectedRow() != -1) {
							Object obj = tblPowerpoint.getModel().getValueAt(tblPowerpoint.getSelectedRow(), 1);
							String a = obj.toString().replaceAll("[\\n]{2,}", "\n");
							getTxtArea().setText(a);
						}
					} else {
						mouseEvent.consume();
						if (tblPowerpoint.getSelectedRow() != -1) {
							ShowPPT showPPT = new ShowPPTImpl();
							File obj = (File)tblPowerpoint.getModel().getValueAt(tblPowerpoint.getSelectedRow(), 2);
							try {
								showPPT.showPPT(obj.getAbsolutePath());
							} catch (IOException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}												
					}
				}
			});
			
			tblPowerpoint.addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (tblPowerpoint.getSelectedRow() != -1) {
						if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
							arg0.consume();
							ShowPPT showPPT = new ShowPPTImpl();
							File obj = (File)getTblPowerpoint().getModel().getValueAt(getTblPowerpoint().getSelectedRow(), 2);
							try {
								showPPT.showPPT(obj.getAbsolutePath());
							} catch (IOException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}						
						} 
					}
				}
			});			
			
			tblPowerpoint.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					
	                if (tblPowerpoint.getSelectedRow() >= 0) {	                    
	                	Object obj = tblPowerpoint.getModel().getValueAt(tblPowerpoint.getSelectedRow(), 1);
						String a = obj.toString().replaceAll("[\\n]{2,}", "\n");
						getTxtArea().setText(a);
	                }
					
				}

	        }
	);
		}
		return tblPowerpoint;
	}

	private JTextArea getTxtArea() {
		if (txtArea == null) {
			txtArea = new JTextArea();
			txtArea.setEditable(false);
			txtArea.setFocusable(false);
			txtArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));			
		}
		return txtArea;
	}
	public GenericTableModel<Powerpoint> getTableModel() {
		if (tableModel == null) {
			tableModel = new GenericTableModel<Powerpoint>(new ArrayList<String>(Arrays.asList(new String[]{"fileName", "songLyrics", "file"})),
					new String[]{"Archivo"});
		}
		return tableModel;
	}
	
	public void clear() {
		getTxtArea().setText("");
		getTableModel().limpiarLista();
	}
	

}
