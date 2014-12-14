package logic.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import util.Utils;

import model.Powerpoint;



public class PPTFinderTask implements Callable<List<Powerpoint>>{

	private File[] files;
	private String content;
	
	public PPTFinderTask(File[] files, String content) {
		this.files = files;
		this.content = content;
	}

	@Override
	public List<Powerpoint> call() throws Exception {
		List<Powerpoint> filesMatched = new ArrayList<Powerpoint>();
		System.out.println("Arranca :" + System.currentTimeMillis());
		for (File file : files) {
			try {
				if (file.isFile()) { 
					String text = Utils.getPowerpointText(file);
					if (Utils.containWords(text, content)) {
						filesMatched.add(new Powerpoint(file, text));
					}
				}
			} catch (Exception e) {
				System.out.println("El Archivo " +  file.getName() + " no se ha podido abrir");
			}
		}
		System.out.println("Termina :" + System.currentTimeMillis());
		return filesMatched;
	}
	
}
