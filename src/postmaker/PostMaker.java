package postmaker;

import java.util.ArrayList;
import java.util.Collections;
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

	private boolean isArchive(String file) {
		return file.contains("rar");
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
		} else if (splitPattern == SPLIT_TITLES) {
			for (String item : splitted) {
				items.add(new Title(item));
			}
		} else if (splitPattern == SPLIT_URLS) {
			for (String item : splitted) {
				items.add(new Video(item));
			}
		}

		return items;
	}


	public void makePosts() {
		List<PostItem> coversSplitted = splitItems(covers, SPLIT_IMAGES);
		List<PostItem> screenshotsSplitted = splitItems(screenshots, SPLIT_IMAGES);
		List<PostItem> mediainfoSplitted = splitItems(mediainfo, SPLIT_MEDIAINFO);
		List<PostItem> titlesSplitted = splitItems(titles, SPLIT_TITLES);
		List<PostItem> urlsSplitted = splitItems(urls, SPLIT_URLS);
		List<PostItem> videosSplitted = joinArchives(urlsSplitted);
		
		sortPostItems(coversSplitted);
		sortPostItems(screenshotsSplitted);
		sortPostItems(mediainfoSplitted);
		sortPostItems(titlesSplitted);
		sortPostItems(videosSplitted);

		
/*		logPostItems(coversSplitted);
		logPostItems(screenshotsSplitted);
		logPostItems(mediainfoSplitted);
		logPostItems(titlesSplitted);
		logPostItems(videosSplitted);
*/		
		
		List<PostItem> coversArranged = arrangeItems(coversSplitted);
		List<PostItem> screenshotsArranged = arrangeItems(screenshotsSplitted);
		List<PostItem> mediainfoArranged = arrangeItems(mediainfoSplitted);
		List<PostItem> titlesArranged = arrangeItems(titlesSplitted);
		List<PostItem> videosArranged = arrangeItems(videosSplitted);
		
		logPostItems(coversArranged);
		logPostItems(screenshotsArranged);
		logPostItems(mediainfoArranged);
		logPostItems(titlesArranged);
		logPostItems(videosArranged);
	}
	
	private void sortPostItems(List<PostItem> items) {
		Collections.sort(items);
	}


	private List<PostItem> joinArchives(List<PostItem> items) {
		List<PostItem> newItems = new ArrayList<>();
		int i = 0;
		String value;
		while (i < items.size()) {
			value = items.get(i).getValue();
			while (i + 1 < items.size() && items.get(i).getNumber() == items.get(i + 1).getNumber()) {
				value = value + "\n" + items.get(i + 1).getValue();
				i++;
			}
			newItems.add(new Video(value));
			i++;
		}
		return newItems;
	}


	private List<PostItem> arrangeItems(List<PostItem> postItems) {
		if (postItems.get(0).getNumber() > 1) {
			postItems.add(0, new PostItem(1));
		}

		for (int i = 0; i < postItems.size() - 1; i++) {
			int delta = postItems.get(i + 1).getNumber() - postItems.get(i).getNumber();
			if (delta > 1) {
				int ordNumber = postItems.get(i).getNumber();
				for (int j = 1; j < delta; j++) {
					ordNumber = ordNumber + 1;
					postItems.add(i + j, new PostItem(ordNumber));
				}
				i = i + delta - 1;
			}
		}
		return postItems;
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
