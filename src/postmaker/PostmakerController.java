package postmaker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import postmaker.dto.PostData;
import postmaker.dto.Posts;

/**
 * Servlet implementation class PostmakerController
 */
@WebServlet("/controller")
public class PostmakerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(PostmakerController.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileManager fileManager = new FileManager();
		String covers = request.getParameter("covers").trim();
		String screenshots = request.getParameter("screenshots").trim();
		String mediainfo = request.getParameter("mediainfo").trim();
		String titles = request.getParameter("titles").trim();
		String urls = request.getParameter("urls").trim();
		String delete = request.getParameter("delete").trim();
		String tags = request.getParameter("tags").trim();
		String genre = request.getParameter("genre");
		String write = request.getParameter("write");
		String coverPichost = request.getParameter("cover-pichost");
		String screenshotPichost = request.getParameter("screenshot-pichost");
		String filehost = request.getParameter("filehost");
		
		if (genre == null) {
			request.getRequestDispatcher("/input.jsp").forward(request, response);
		}
		
		PostData postData = new PostData();
		postData.setCovers(covers);
		postData.setScreenshots(screenshots);
		postData.setMediainfo(mediainfo);
		postData.setTitles(titles);
		postData.setUrls(urls);
		postData.setDelete(delete);
		postData.setTags(tags);
		postData.setCoverPichost(coverPichost);
		postData.setScreenshotPichost(screenshotPichost);
		postData.setFilehost(filehost);
		
		if (logger.isTraceEnabled()) {
//			logger.trace("Input covers: \n" + postData.getCovers() + "\n");
//			logger.trace("Input screenshots: \n" + postData.getScreenshots() + "\n");
//			logger.trace("Input mediainfo: \n" + postData.getMediainfo() + "\n");
//			logger.trace("Input titles: \n" + postData.getTitles() + "\n");
//			logger.trace("Input urls: \n" + postData.getUrls() + "\n");
//			logger.trace("Pichost: " + postData.getPichost() + "\n");
		}
		
		/* Prepare posts */
		PostMaker postMaker = new PostMaker(postData);
		postMaker.makePosts();
		postMaker.deletePostsWithNoScreenshots();
		postMaker.deleteUnvalidPosts();
		

		/* Initialize bean object */
		Posts posts = new Posts();
		posts.setPosts(postMaker.getPosts());

		/* Output posts */
		if (write != null) {
			fileManager.savePosts(posts, genre);
		}
		
		
		for(Post post: posts.getPosts()) {
			logger.trace(post);
		}
		
		request.setAttribute("posts", posts);
		request.getRequestDispatcher("/output.jsp").forward(request, response);
	}

}
