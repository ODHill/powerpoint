package model;

import java.io.File;

public class Powerpoint {
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getSongLyrics() {
		return songLyrics;
	}

	public void setSongLyrics(String songLyrics) {
		this.songLyrics = songLyrics;
	}

	private File file;
	
	private String songLyrics;
	
	public Powerpoint(File file, String songLyrics) {
		 this.file = file;
		 this.songLyrics = songLyrics;
	}	
	
	public String getFileName() {
		return getFile().getName();
	}
	
	public String toString() {
		return getFileName();
	}

}
