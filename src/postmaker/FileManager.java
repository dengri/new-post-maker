package postmaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileManager {
	private static final Logger logger = LogManager.getLogger(FileManager.class);
	private File coversPath ;
	private File screenshotsPath;
	private File mediainfoPath;
	private File titlesPath;
	

	public FileManager(){
		coversPath = new File("/newfo/fj/upl/inf/covers.txt") ;
		screenshotsPath = new File("/newfo/fj/upl/inf/screenshots_large.txt") ;
		mediainfoPath = new File("/newfo/fj/upl/inf/filesinfo.txt") ;
		titlesPath = new File("/newfo/fj/upl/inf/titles.txt") ;
	}
	
	public String getCovers() {
		return readFileToString(coversPath);
	}
	
	public String getScreenshots() {
		return readFileToString(screenshotsPath);
	}
	
	public String getMediainfo() {
		return readFileToString(mediainfoPath);
	}
	
	public String getTitles() {
		return readFileToString(titlesPath);
	}

	private String readFileToString(File path) {
		StringBuilder contents = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (logger.isTraceEnabled()) {
					logger.trace("Reading line from: " + path.getAbsolutePath() + "\n" + line + "\n");
				}
				contents.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return contents.toString();
	}

	public static void main(String[] args) {
		FileManager manager = new FileManager();
		System.out.println(manager.getCovers());
		System.out.println(manager.getScreenshots());
		System.out.println(manager.getMediainfo());
		System.out.println(manager.getTitles());
	}
}
