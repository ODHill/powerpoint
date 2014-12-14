package gui;

import java.io.IOException;
import java.util.List;

import javax.swing.JComboBox;

import logic.impl.PPTFinderImpl;
import logic.interfaces.PPTFinder;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ComboPPT extends JComboBox{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9203894320038152425L;
	
	public ComboPPT() {
		super();
		initialize();
	}

	private void initialize() {			
		load();		
		this.setMaximumRowCount(8);
		this.setToolTipText("Seleccione un powerpoint");
		AutoCompleteDecorator.decorate(this);
//		this.setEditable(true);
	}

	public String getFileName(int index) {
		return  (String)getItemAt(index);
	}

	public String getFileNameSelected() {
		return getFileName(getSelectedIndex());
	}
	
	public void setFileName(String fileName){
		this.setSelectedItem(fileName);
	}
	
	public void load() {
		this.removeAllItems();
		PPTFinder pprFinder = new PPTFinderImpl();
		try {
			List<String> list = pprFinder.getAllFileNames();
			for (String name : list) {
				this.addItem(name);
			}
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (OpenXML4JException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	

}
