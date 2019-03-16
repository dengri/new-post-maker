package postmaker;

import static postmaker.util.AppPatterns.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import postmaker.dto.PostData;
import postmaker.util.AppPatterns;
import postmaker.util.DashExpander;

public class PostMaker {
	private static final Logger logger = LogManager.getLogger(PostMaker.class);

	private String covers;
	private String screenshots;
	private String mediainfo;
	private String titles;
	private String urls;
	private String tags;
	private String coverPichost;
	private String screenshotPichost;
	private String delete;

	List<PostItem> coversArranged;
	List<PostItem> screenshotsArranged;
	List<PostItem> mediainfoArranged;
	List<PostItem> titlesArranged;
	List<PostItem> videosArranged;

	List<Post> posts;

	public PostMaker(PostData postData) {
		covers = postData.getCovers();
		screenshots = postData.getScreenshots();
		mediainfo = postData.getMediainfo();
		titles = postData.getTitles();
		urls = postData.getUrls();
		tags = postData.getTags();
		posts = new ArrayList<Post>();
		coverPichost = postData.getCoverPichost();
		screenshotPichost = postData.getScreenshotPichost();
		delete = postData.getDelete();
	}


	public List<Post> getPosts() {
		return posts;
	}




	/*	public static void main(String[] args) {

		String unsplitted = "[URL=http://pimpandhost.com/image/85327345][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xv/3.30.0001-webcam-sex_s_l.jpg[/IMG][/URL] [URL=http://pimpandhost.com/image/85327350][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xA/3.30.0002-webcam-sex_s_l.jpg[/IMG][/URL] [URL=http://pimpandhost.com/image/85327351][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xB/3.30.0003-webcam-sex_s_l.jpg[/IMG][/URL] [URL=http://pimpandhost.com/image/85327347][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xx/3.30.0004-webcam-sex_s_l.jpg[/IMG][/URL] [URL=http://pimpandhost.com/image/85327344][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xu/3.30.0005-webcam-sex_s_l.jpg[/IMG][/URL] [URL=http://pimpandhost.com/image/85327348][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xy/3.30.0007-webcam-sex_s_l.jpg[/IMG][/URL] [URL=http://pimpandhost.com/image/85327342][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xs/3.30.0008-webcam-sex_s_l.jpg[/IMG][/URL] [URL=http://pimpandhost.com/image/85327341][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xr/3.30.0009-webcam-sex_s_l.jpg[/IMG][/URL] [URL=http://pimpandhost.com/image/85327338][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xo/3.30.0010-webcam-sex_s_l.jpg[/IMG][/URL] [URL=http://pimpandhost.com/image/85327340][IMG]http://ist5-1.filesor.com/pimpandhost.com/1/8/3/9/183904/5/M/1/x/5M1xq/3.30.0011-webcam-sex_s_l.jpg[/IMG][/URL] ";

		PostMaker postMaker = new PostMaker(new PostData());
		postMaker.splitItems(unsplitted, AppPatterns.SPLIT_COVERS_PIMPANDHOST);
	}
	 */

	private List<PostItem> splitItems(String unsplitted, AppPatterns splitPattern) {
		List<PostItem> items = new ArrayList<PostItem>();
		String[] splitted = unsplitted.split(splitPattern.getPattern());

		if (splitPattern == SPLIT_COVERS_PIMPANDHOST) {
			for (String item : splitted) {
				PimpAndHostImage image = new PimpAndHostImage(item);
				items.add(image);
			}
		} else if (splitPattern == SPLIT_SCREENSHOTS_PIMPANDHOST) {
			for (String item : splitted) {
				PimpAndHostImage image = new PimpAndHostImage(item);
				items.add(image);
			}
		} else if (splitPattern == SPLIT_SCREENSHOTS_PIXHOST) {
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
				//logger.trace(item);
				items.add(new Video(item));
			}
		} else if (splitPattern == SPLIT_COVERS_PIXHOST) {
			int i = 0;
			while (i < splitted.length - 1) {
				Image image1 = new Image (splitted[i]);
				int j = i + 1;
				Image image2 = new Image (splitted[j]);
				while (j < splitted.length && image1.getNumber() == image2.getNumber()) {
					image1.append(image2);
					j++;
					if (j < splitted.length) {
						image2 = new Image (splitted[j]);
					}
				}
				items.add(image1);
				i = j;
			}
		}
		return items;
	}

	public void makePosts() {

		preparePostData();

		for(PostItem video: videosArranged) {
			posts.add(new Post(video.getNumber(), tags));
		}

		int i = 0;
		for(PostItem item: titlesArranged) {
			if(i >= posts.size()) break;
			posts.get(i).setTitle(item.getValue());
			i++;
		}

		i = 0;
		for(PostItem item: coversArranged) {
			if(i >= posts.size()) break;
			posts.get(i).setCover(item.getValue());
			i++;
		}

		i = 0;
		for(PostItem item: screenshotsArranged) {
			if(i >= posts.size()) break;
			posts.get(i).setScreenshot(item.getValue());
			i++;
		}

		i = 0;
		for(PostItem item: mediainfoArranged) {
			if(i >= posts.size()) break;
			posts.get(i).setMediainfo(item.getValue());
			i++;
		}

		i = 0;
		for(PostItem item: videosArranged) {
			if(i >= posts.size()) break;
			posts.get(i).setVideo(item.getValue());
			i++;
		}

//		for(Post post: posts) {
//			logger.trace(post);
//		}

		/*		for (int i = 0; i < videosArranged.size(); i++) {
			if (videosArranged.get(i).getValue() == null 
					|| videosArranged.get(i).getValue().equals("") 
					|| (i < mediainfoArranged.size() && mediainfoArranged.get(i).getValue() == null)) {
				continue;
			}

			int  number = videosArranged.get(i).getNumber();

			posts.add(new Post( number,
					(i < titlesArranged.size()) ? titlesArranged.get(i).getValue() : "",
							(i < coversArranged.size()) ? coversArranged.get(i).getValue() : "", 
									(i < screenshotsArranged.size()) ? screenshotsArranged.get(i).getValue() : "",
											mediainfoArranged.get(i).getValue(),
											videosArranged.get(i).getValue(),
											tags));
		}
		 */	}


	public void preparePostData() {
		List<PostItem> coversSplitted = null;
		List<PostItem> screenshotsSplitted = null;

		if(coverPichost.equals("cover-pimpandhost")) {
			coversSplitted = splitItems(covers, SPLIT_COVERS_PIMPANDHOST);
			for(PostItem cover: coversSplitted) {
				logger.trace(cover.getValue());
			}
		} else {
			coversSplitted = splitItems(covers, SPLIT_COVERS_PIXHOST);
		}

		if(screenshotPichost.equals("screenshot-pimpandhost")) {
			screenshotsSplitted = splitItems(screenshots, SPLIT_SCREENSHOTS_PIMPANDHOST);
		} else {
			screenshotsSplitted = splitItems(screenshots, SPLIT_SCREENSHOTS_PIXHOST);
		}

		List<PostItem> mediainfoSplitted = splitItems(mediainfo, SPLIT_MEDIAINFO);
		List<PostItem> titlesSplitted = splitItems(titles, SPLIT_TITLES);

		List<PostItem> urlsSplitted = splitItems(urls, SPLIT_URLS);

		List<PostItem> videosSplitted = joinArchives(urlsSplitted);

		sortPostItems(coversSplitted);
		sortPostItems(screenshotsSplitted);
		sortPostItems(mediainfoSplitted);
		sortPostItems(titlesSplitted);
		sortPostItems(videosSplitted);

		logger.trace("===>>>>> " + coversSplitted);

		coversArranged = arrangeItems(coversSplitted);
		screenshotsArranged = arrangeItems(screenshotsSplitted);
		mediainfoArranged = arrangeItems(mediainfoSplitted);
		titlesArranged = arrangeItems(titlesSplitted);
		videosArranged = arrangeItems(videosSplitted);

		/*		logPostItems(coversArranged);
		logPostItems(screenshotsArranged);
		logPostItems(mediainfoArranged);
		logPostItems(titlesArranged);
		logPostItems(videosArranged);
		 */			
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


	public void logPostItems(List<PostItem> items) {
		if (logger != null && logger.isTraceEnabled()) {
			for (PostItem item : items) {
				logger.trace(item.getNumber());
				logger.trace(item.getValue());
			}
		}
	}


	public void deletePostsWithNoScreenshots() {
		Iterator<Post> it = posts.iterator();
		while(it.hasNext()) {
			Post post = it.next();
			logger.trace("Checking post: \n" + post);
			if(post.getCover().equals("") && post.getScreenshot().equals("") 
					|| post.getMediaInfo() == null
					|| post.getVideo() == null) {
				logger.trace("Found post with no screenshots: " + post);
				it.remove();
			}
		}
	}


	public void deleteUnvalidPosts() {

		if (delete.equals("")) {
			return;
		}

		String[] numbers = delete.split(" ");
		List<String> expandedNumbers = new ArrayList<>();

		//Expand interval
		for(String number: numbers) {
			if(number.contains("-")) {
				List<String> expandedInterval = DashExpander.getNumbers(number);
				expandedNumbers.addAll(expandedInterval);
			} else {
				expandedNumbers.add(number);
			}
		}
		
		//remove post
		for (String number: expandedNumbers) {
			int numberInt = Integer.parseInt(number);

			Iterator<Post> it = posts.iterator();
			
			while (it.hasNext()) {
				Post post = it.next();
				if (post.getNumber() == numberInt) {
					it.remove();
				}
			}
		}
	}

}
