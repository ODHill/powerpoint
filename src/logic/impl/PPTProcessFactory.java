package logic.impl;

import java.io.File;

import logic.interfaces.PPTProcess;

import util.Utils;

public class PPTProcessFactory {
	
	private PPTProcess pptProcess;
	private File file;
	
	public PPTProcessFactory(File file){
		this.file = file;
		if (file.getName().endsWith(Utils.PPTX_EXTENSION)) {
			 
		} else {						
		}			
	}
	
//	public PPTProcessFactory getInstance() {
//		if (instance == null) {
//			instance = new PPTProcessFactory(); 
//		}
//		return instance;
//	}
	
	public String getPPTProcess(File file) throws Exception{
		
		if (file.getName().endsWith(Utils.PPTX_EXTENSION))
			return  new PPTProcessPptxImpl().getProcessPowerpoint(file);
		else 	
			return new PPTProcessPptImpl().getProcessPowerpoint(file);
	}

}
