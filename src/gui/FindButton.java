package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import logic.impl.PPTFinderImpl;
import logic.interfaces.PPTFinder;
import model.FindTypeEnum;
import model.Powerpoint;

public class FindButton extends JButton{
	
	public FindButton() {
		setToolTipText("Buscar powerpoints");
		setOpaque(true);
		//setBackground(new Color(162, 46, 186));
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -8548852393173509613L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void find(String value, GenericTableModel tableModel, FindTypeEnum type) throws Exception {
		PPTFinder pptFinder = new PPTFinderImpl();
		
		if (value.isEmpty())
			type = FindTypeEnum.ALL_FILES;
		
		switch (type) {
		case BY_WORDS:
			List<Powerpoint> list = pptFinder.searchFileByWords(value);
			tableModel.setData(list);  
			break;
		case BY_TITLE:
			list = new ArrayList<Powerpoint>();
			Powerpoint finded = pptFinder.searchFileByName(value);
			if (finded != null)
				list.add(finded);
			tableModel.setData(list);
			break;
		case ALL_FILES:
			list = new ArrayList<Powerpoint>();
			list.addAll(pptFinder.getAll());
			tableModel.setData(list);
			break;
		}				
	}

}
