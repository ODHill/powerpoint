package logic.impl;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;

import logic.interfaces.PPTProcess;

public class PPTProcessPptxImpl implements PPTProcess {

	@Override
	public String getProcessPowerpoint(File file) throws Exception {
		FileInputStream fs = new FileInputStream(file);
		OPCPackage pptx = OPCPackage.open(fs);

		XSLFPowerPointExtractor xw = new XSLFPowerPointExtractor(pptx);
		return xw.getText();
	}



}
