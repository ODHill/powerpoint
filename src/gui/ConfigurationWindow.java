package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.interfaces.PPTFinder;
import util.Configuration;

@SuppressWarnings("serial")
public class ConfigurationWindow extends JPanel{
	
	private JFileChooser fileChooser;
	
	private JFrame owner;
	
	public ConfigurationWindow(JFrame owner){		
		this.owner = owner;
		add(getFileChooser());
		showWindow();
				
	}
	
	private JFileChooser getFileChooser() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setDialogTitle("Configuración - Seleccione directorio");
			fileChooser.setCurrentDirectory(new File(Configuration.getInstance().getValue(PPTFinder.POWERPOINT_PROPERTIES_KEY)));
		}
		return fileChooser;
	}	
	
	private void showWindow() {
		int res = getFileChooser().showDialog(owner, "Guardar");
		if (res == JFileChooser.APPROVE_OPTION) {
			String newPath = fileChooser.getSelectedFile().getAbsolutePath();
			System.out.println("PATH : " + newPath);
			Configuration.getInstance().setDirectory(newPath);//D:\\Letras\\
		}
	}
	

}
