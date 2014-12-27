package logic.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import logic.interfaces.PPTFinder;
import model.Powerpoint;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;

import util.Configuration;
import util.FilenameFilterPPTS;
import util.Utils;

public class PPTFinderImpl implements PPTFinder {
		
	//TODO do recursive search
	
	public Powerpoint searchFileByName(String name)  {
		File file = new File(Configuration.getInstance().getValue(POWERPOINT_PROPERTIES_KEY) + File.separatorChar + name);
		if(file.exists()) {			
			try {
				return new Powerpoint(file, Utils.getPowerpointText(file));
			} catch (Exception e) {
				System.out.println("El Archivo " +  name + " no se ha podido abrir");
			}
		}	
		return null;
	}

	@Deprecated
	public List<Powerpoint> searchFileByWords2(String words) throws IOException, XmlException, OpenXML4JException {
		List<Powerpoint> filesMatched = new ArrayList<Powerpoint>();
		File directory = new File(Configuration.getInstance().getValue(POWERPOINT_PROPERTIES_KEY));
	   	 long start = System.currentTimeMillis();
	   	 if (directory.isDirectory()) {
	   		 
	   		 File[] files = directory.listFiles(new FilenameFilterPPTS());

	   		 for (File file : files) {
	   			 if (file.isFile()) { 
	   				String text = Utils.getPowerpointText(file);
	   				if (Utils.containWords(text, words)) {
	   					filesMatched.add(new Powerpoint(file, text));
	   				}
	   			 }
	   		 }
	   	 }
	   	 System.out.println(System.currentTimeMillis() - start);
		return filesMatched;
	}
	
	public List<Powerpoint> searchFileByWords(String words) throws IOException, XmlException, OpenXML4JException, InterruptedException, ExecutionException {
		List<Powerpoint> filesMatched = new ArrayList<Powerpoint>();
		File directory = new File(Configuration.getInstance().getValue(POWERPOINT_PROPERTIES_KEY));
	   	 long start = System.currentTimeMillis();
	   	 if (directory.isDirectory()) {
	   		 
	   		 File[] files = directory.listFiles(new FilenameFilterPPTS());
	   		 
	   		 if (files.length < 10) {
	   			 for (File file : files) {
	   				 try {
	   					 if (file.isFile()) { 
	   						 String text = Utils.getPowerpointText(file);
	   						 if (Utils.containWords(text, words)) {
	   							 filesMatched.add(new Powerpoint(file, text));
	   						 }
	   					 }
	   				 } catch (Exception e) {
	   					 System.out.println("El Archivo " +  file.getName() + " no se ha podido abrir");
	   				 }
	   			 }

	   		 } else {
		   		int processors = Runtime.getRuntime().availableProcessors();
	   			ExecutorService executor = Executors.newFixedThreadPool(processors);
	   			CompletionService<List<Powerpoint>>  completitionService= new ExecutorCompletionService<List<Powerpoint>>(executor);
//	   			int medium = files.length / 2;
//	   			
//	   			File[] files1 = Arrays.copyOfRange(files, 0, medium);
//	   			File[] files2 = Arrays.copyOfRange(files, medium, files.length);
//	   			
//	   			completitionService.submit(new PPTFinderTask(files1, words));
//	   			completitionService.submit(new PPTFinderTask(files2, words));
	   			

		   		 int begin = 0;
			   	 int size = files.length/ processors;
			   	 int end = size;
	   			
			   	 for(int i = 1 ; i <= processors; i++) {
				   		completitionService.submit(new PPTFinderTask(Arrays.copyOfRange(files, begin, end), words));
				   		 begin = end;
				   		 end = begin + size;
				   		 
				   		 if (i == (processors -1))
				   			 end = files.length;   		 
				   	 }
	   			
	   			for(int index = 0; index < 2; index++) {
		   	         try {
		   	        	filesMatched.addAll(completitionService.take().get());
		   	         } catch (InterruptedException e) {		   	        	 
		   	         } catch (ExecutionException e) {		   	        	 
		   	         }
	   			}
	   			
	   			executor.shutdownNow();
	   			
		    }	   				   				   		   	 
	   		 
	   	 }
	   	 System.out.println(System.currentTimeMillis() - start);
		return filesMatched;
	}

	public List<Powerpoint> getAll() throws XmlException, OpenXML4JException, IOException {
		List<Powerpoint> powerpoints = new ArrayList<Powerpoint>();
		File directory = new File(Configuration.getInstance().getValue(POWERPOINT_PROPERTIES_KEY));
	   	 long start = System.currentTimeMillis();
	   	 if (directory.isDirectory()) {
	   		File[] files = directory.listFiles(new FilenameFilterPPTS());

	   		for (File file : files) {
	   			try {
	   				if (file.isFile()) { 
	   					powerpoints.add(new Powerpoint(file, Utils.getPowerpointText(file)));
	   				}
	   			} catch (Exception e) {
	   				System.out.println("El Archivo " +  file.getName() + " no se ha podido abrir");
	   			}
	   		}	   		 
	   	 }	 
	   	 System.out.println(System.currentTimeMillis() - start);
	   	 return powerpoints;
	}	
	

	@Override
	public List<String> getAllFileNames() throws XmlException,
			OpenXML4JException, IOException {
		List<String> powerpoints = new ArrayList<String>();
		File directory = new File(Configuration.getInstance().getValue(POWERPOINT_PROPERTIES_KEY));
	   	 
		if (directory.isDirectory()) {
			File[] files = directory.listFiles(new FilenameFilterPPTS());

			for (File file : files) {
				try {
					if (file.isFile()) { 
						powerpoints.add(file.getName());
					}
				} catch (Exception e) {
					System.out.println("El Archivo " +  file.getName() + " no se ha podido abrir");
				}
			}	   		 
		}	 
	   	 
	   	return powerpoints;
	}	 
	
	class FinderThread extends Thread {
		
		private File file;
		
		public FinderThread(){
			
		}
		
		@Override
		public void run() {
			String text;
			try {
				text = Utils.getPowerpointText(file);
	   				if (Utils.containWords(text, "")){
	   					
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

}
