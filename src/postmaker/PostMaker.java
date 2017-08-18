package postmaker;

import java.util.ArrayList;
import java.util.List;
import static postmaker.AppPatterns.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import postmaker.dto.PostData;

public class PostMaker {
	private static final Logger logger = LogManager.getLogger(PostMaker.class);

	private String covers;
	private String screenshots;
	private String mediainfo;
	private String titles;
	private String urls;
	private List<Image> preparedCovers;
	
	public PostMaker(PostData postData) {
		covers = postData.getCovers();
		screenshots = postData.getScreenshots();
		mediainfo = postData.getMediainfo();
		titles = postData.getTitles();
		urls = postData.getUrls();
	}
	
	
	private List<PostItem> splitItems(String unsplitted, AppPatterns splitPattern) {
		List<PostItem> items = new ArrayList<PostItem>();
		String[] splitted = unsplitted.split(splitPattern.getPattern());
		if (splitPattern == SPLIT_IMAGES) {
			for (String item : splitted) {
				items.add(new Image(item));
			}
		} else if (splitPattern == SPLIT_MEDIAINFO) {
			for (String item : splitted) {
				items.add(new Mediainfo(item));
			}
		}
		return items;
	}
	

	public void makePosts() {
		List<PostItem> coversSplitted = splitItems(covers, SPLIT_IMAGES);
		logPostItems(coversSplitted);
		List<PostItem> screenshotsSplitted = splitItems(screenshots, SPLIT_IMAGES);
		logPostItems(screenshotsSplitted);
		List<PostItem> mediainfoSplitted = splitItems(mediainfo, SPLIT_MEDIAINFO);
		logPostItems(mediainfoSplitted);
	}


	private void logPostItems(List<PostItem> items) {
		if (logger != null && logger.isTraceEnabled()) {
			for (PostItem item : items) {
				logger.trace(item.getNumber());
				logger.trace(item.getValue());
			}
		}
	}
}
