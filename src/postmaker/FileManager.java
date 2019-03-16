package postmaker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import postmaker.dto.Posts;

public class FileManager {
	private static final Logger logger = LogManager.getLogger(FileManager.class);
	private File coversPath ;
	private File screenshotsPath;
	private File mediainfoPath;
	private File titlesPath;
	private File postsPath;


	public FileManager() {
		coversPath = new File("/fo4/fj/upl/inf/covers.txt") ;
		screenshotsPath = new File("/fo4/fj/upl/inf/screenshots_large.txt") ;
		mediainfoPath = new File("/fo4/fj/upl/inf/filesinfo.txt") ;
		titlesPath = new File("/fo4/fj/upl/inf/titles.txt") ;
		postsPath = new File("/fo4/fj/posts/");
	}


	public void savePosts(Posts posts, String genre) {
		LocalDateTime ldt = LocalDateTime.now();
		StringBuilder postsFolder = new StringBuilder();
		postsFolder.append(postsPath.getAbsolutePath())
		.append("/")
		.append(genre)
		.append("/")
		.append(ldt.getYear())
		.append("-")
		.append(ldt.getMonth())
		.append("-")
		.append(ldt.getDayOfMonth())
		.append("-")
		.append(ldt.getHour());

		if((new File(postsFolder.toString())).mkdirs()) {
			for (Post post: posts.getPosts()) {
				savePost(post, genre, postsFolder.toString());
			}
		} else {
			throw new RuntimeException("Unable to create folder to save posts to: " + postsFolder.toString());
		}
	}

	private void savePost(Post post, String genre, String targetFolder) {
		LocalDateTime ldt = LocalDateTime.now();
		StringBuilder postPath = new StringBuilder();

		postPath.append(targetFolder.toString())
		.append("/")
		.append(ldt.getYear())
		.append("-")
		.append(ldt.getMonth())
		.append("-")
		.append(ldt.getDayOfMonth())
		.append("-")
		.append(String.format("%4s_", post.getNumber()).replace(" ", "0"))
		.append(genre)
		.append(".txt");

		try(PrintWriter out = new PrintWriter(postPath.toString())) {
			logger.trace("Saving post: " + postPath.toString());
			logger.trace(post.getBBCode());
			out.write(post.getBBCode());
		} catch (IOException e) {
			logger.error("Cannot open file for writing: " + postPath.toString(), e);
		}
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
				contents.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return contents.toString();
	}
}
