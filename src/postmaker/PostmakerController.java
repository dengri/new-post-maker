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




/**
 * Servlet implementation class PostmakerController
 */
@WebServlet("/controller")
public class PostmakerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(PostmakerController.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String covers = request.getParameter("covers");
		String screenshots = request.getParameter("screenshots");
		String mediainfo = request.getParameter("mediainfo");
		String titles = request.getParameter("titles");
		String urls = request.getParameter("urls");
		
		PostData postData = new PostData();
		postData.setCovers(covers);
		postData.setScreenshots(screenshots);
		postData.setMediainfo(mediainfo);
		postData.setTitles(titles);
		postData.setUrls(urls);
		
		if (logger.isTraceEnabled()) {
			logger.trace("Input covers: \n" + postData.getCovers() + "\n");
			logger.trace("Input screenshots: \n" + postData.getScreenshots() + "\n");
			logger.trace("Input mediainfo: \n" + postData.getMediainfo() + "\n");
			logger.trace("Input titles: \n" + postData.getTitles() + "\n");
			logger.trace("Input urls: \n" + postData.getUrls() + "\n");
		}
		
		PostMaker postMaker = new PostMaker(postData);
		postMaker.makePosts();

		request.getRequestDispatcher("/output.jsp").forward(request, response);
		
	}

}
