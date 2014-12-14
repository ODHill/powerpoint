package logic.impl;

import java.io.IOException;

import logic.interfaces.ShowPPT;
import util.Utils;

public class ShowPPTImpl implements ShowPPT {

	public void showPPT(String path) throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec(Utils.COMMAND + path);
		p.waitFor();
	}

}
