package test;

import java.io.IOException;
import java.util.List;

import logic.impl.PPTFinderImpl;
import logic.impl.ShowPPTImpl;
import logic.interfaces.PPTFinder;
import logic.interfaces.ShowPPT;
import model.Powerpoint;

public class Test {

	private static String BUSQUEDA = "dios";
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		PPTFinderImpl pptFinder = new PPTFinderImpl();
		List<Powerpoint> ppts = pptFinder.searchFileByWords(BUSQUEDA);		
//		try {
//			
//				//System.out.println(ppt.getSongLyrics().replaceAll("(\\s)\\1{2,}", "\n"));
//				//showPPT.showPPT(ppt.getFile().getAbsolutePath());
//				showPPTS(ppts);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
	}
	
	private static void showPPTS(List<Powerpoint> ppts) throws IOException, InterruptedException {
		ShowPPT showPPT = new ShowPPTImpl();
		int cont = 1;
		for (Powerpoint powerpoint : ppts) {
			showPPT.showPPT(powerpoint.getFile().getAbsolutePath());				
			cont++;
		}
		
		System.out.println("Total: " + cont);
	}
}
