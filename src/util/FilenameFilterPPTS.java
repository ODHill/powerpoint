package util;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterPPTS implements FilenameFilter{

	public boolean accept(File dir, String name) {
		 return name.endsWith(Utils.PPTX_EXTENSION) || name.endsWith(Utils.PPT_EXTENSION);//  
	}

}
