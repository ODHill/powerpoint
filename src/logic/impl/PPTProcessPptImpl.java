package logic.impl;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

import logic.interfaces.PPTProcess;

public class PPTProcessPptImpl implements PPTProcess {

	@Override
	public String getProcessPowerpoint(File file) throws Exception {
		FileInputStream fs = new FileInputStream(file);
		SlideShow ppt = new SlideShow(new HSLFSlideShow(fs));
		StringBuilder sb = new StringBuilder();
		
		  Slide[] slide = ppt.getSlides();
		  for (int i = 0; i < slide.length; i++){
		    TextRun[] sh = slide[i].getTextRuns();
		    for (int j = 0; j < sh.length; j++){
		    	sb.append(sh[j].getText());
		    }
		  }   
		  
		  return sb.toString();
	}


}
