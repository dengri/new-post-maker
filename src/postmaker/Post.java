package postmaker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import postmaker.util.BBCodeParser;

public class Post {
	
	private static final Logger logger = LogManager.getLogger(PostmakerController.class);
	
	private int number;
	private final int MAX_TITLE_LENGTH = 60;
	private final String EOL = System.lineSeparator();
	private final String SUBJECT = "<subject>%s</subject>";
	private final String MESSAGE = "[b]Download this video[/b]";
	private String title = "";
	private String cover = "";
	private String screenshot = "";
	private String mediainfo;
	private String video;
	private String postBBCode;
	private String tags;

	public Post(int number, String title, String cover, String screenshot, String mediainfo, String video, String tags) {
		this.number = number;
		this.title = title;
				//(title.length() < MAX_TITLE_LENGTH) ? title : title.substring(0, 60);
		this.cover = cover;
		this.screenshot = screenshot;
		this.mediainfo = mediainfo;
		this.video = video;
		this.tags = tags;
		//this.postBBCode = createPostBBCode();
	}
	
	public Post(int number) {
		this.number = number;
	}

	public Post(int number, String tags) {
		this.number = number;
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Post [number=" + number + "\n, title=" + title + "\n, cover=" + cover + "\n, screenshot=" + screenshot
				+ "\n, mediainfo=" + mediainfo + "\n, video=" + video + "\n, tags=" + tags + "]";
	}
	

	public String getVideo() {
		return this.video;
	}
	

	public int getNumber() {
		return number;
	}
	
	private String createPostBBCode() {
		StringBuilder post = new StringBuilder();
		post.append(String.format(SUBJECT, title))
			.append(EOL)
			.append(getCover())
			.append(EOL)
			.append(EOL)
			.append(tags)
			.append(EOL)
			.append(title)
			.append(EOL)
			.append(EOL)
			.append(MESSAGE)
			.append(EOL)
			.append(video)
			.append(EOL)
			.append(EOL)
			.append(mediainfo)
			.append(EOL)
			.append(EOL)
			.append(getScreenshot());
		
		//logger.trace("BBCode for the post:\n" + post.toString());
		
		return post.toString();
	}
	
	public String getBBCode() {
		return createPostBBCode();
	}
	
	public String getHtml() {
		logger.trace(createPostBBCode());
		return BBCodeParser.bbcode(createPostBBCode());
	}

	public String getCover() {
		return cover == null ? "" : cover;
	}

	public String getScreenshot() {
		return screenshot == null ? "" : screenshot;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	public void setMediainfo(String mediainfo) {
		this.mediainfo = mediainfo;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Object getMediaInfo() {
		return mediainfo;
	}
}
