package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.xmlbeans.XmlException;


public final class Utils {
	
	public static final String COMMAND = "rundll32 url.dll,FileProtocolHandler ";
	
	public static final String PPT_EXTENSION = ".ppt";
	
	public static final String PPTX_EXTENSION = ".pptx";
	
	public static final String PROPERTIES_NAME = "directory.properties";//"config" + File.separatorChar + 

	private Utils() {
		
	}
	
	
	public static String getPowerpointText(File file) throws XmlException, OpenXML4JException, IOException{
		
		if (file.getName().endsWith(Utils.PPTX_EXTENSION))
			return getTextPPTX(file);
		else 	
			return getTextPPT(file);

	}
	
	
	public static boolean containWords(String text, String wordsToFind) {
		String pptText = StringUtils.stripAccents(text);
		String pptTextToFind = StringUtils.stripAccents(wordsToFind);
		return pptText.toLowerCase().contains(pptTextToFind.toLowerCase());
	}
	
	private static String getTextPPT(File file) throws IOException {
		FileInputStream fs = new FileInputStream(file);
		SlideShow ppt = new SlideShow(new HSLFSlideShow(fs));
		StringBuilder sb = new StringBuilder();
		  //get slides 
		  Slide[] slide = ppt.getSlides();
		  for (int i = 0; i < slide.length; i++){
		    TextRun[] sh = slide[i].getTextRuns();
		    for (int j = 0; j < sh.length; j++){
		    	sb.append(sh[j].getText());
		    }
		  }   
		  
		  return sb.toString();
	}
	
	private static String getTextPPTX(File file) throws IOException, XmlException, OpenXML4JException {
		FileInputStream fs = new FileInputStream(file);
		OPCPackage pptx = OPCPackage.open(fs);

		XSLFPowerPointExtractor xw = new XSLFPowerPointExtractor(pptx);
		return xw.getText();
	}
}
