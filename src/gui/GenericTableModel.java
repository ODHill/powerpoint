package gui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class GenericTableModel<T> extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1504157598414363005L;
	private List<String> attributeColumns = new ArrayList<String>();
	private String[] columnNames = null;
	private List<T> data = new ArrayList<T>();
	
	public GenericTableModel(List<String> attributeColumns,String[] columnNames) {
		this.attributeColumns = attributeColumns;
		this.columnNames = columnNames;
	}
	
	public int getRowCount(){
		return data.size();
	}
	
	public int getColumnCount(){
		return columnNames.length;
	}
	
	public Object getValueAt(int row, int column){
//		if(!data.isEmpty()){
			T t = (T) data.get(row);
			String attName =  attributeColumns.get(column);
			attName = Character.toUpperCase(attName.charAt(0)) + attName.substring(1);		
			String methodName = "get" + attName;
			try {
				Method method = t.getClass().getMethod(methodName);
				return method.invoke(t);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}


		return null;
	}
	
	public T getData(int row) {
		return (T) data.get(row);
	}
	
	public String getColumnName(int c){
		return columnNames[c];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column){
			return String.class;//TODO getValueAt(0, column).getClass();
	}
	
	public void setData(List<T> list) {
		data = list;
		fireTableDataChanged();
	}
	
	public void limpiarLista(){
		setData(new ArrayList<T>());
	}

}
