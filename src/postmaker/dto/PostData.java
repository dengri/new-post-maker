package postmaker.dto;

public class PostData {
	private final String NEW_LINE = "<br>";
	private String covers;
	private String screenshots;
	private String mediainfo;
	private String titles;
	private String urls;
	private String rawOutput;

	public PostData() {
	}

	public String getRawOutput() {
		StringBuilder raw = new StringBuilder();
		raw.append("Input covers:")
		.append(NEW_LINE)
		.append(getCovers())
		.append(NEW_LINE)
		.append("Input screenshots: ")
		.append(NEW_LINE)
		.append(getScreenshots())
		.append(NEW_LINE)
		.append("Input mediainfo: ")
		.append(NEW_LINE)
		.append(getMediainfo())
		.append(NEW_LINE)
		.append("Input titles: ")
		.append(NEW_LINE)
		.append(getTitles())
		.append(NEW_LINE)
		.append("Input urls: ")
		.append(NEW_LINE)
		.append(getUrls());
		return raw.toString();
	}

	public String getCovers() {
		return covers;
	}

	public void setCovers(String covers) {
		this.covers = covers;
	}

	public String getScreenshots() {
		return screenshots;
	}

	public void setScreenshots(String screenshots) {
		this.screenshots = screenshots;
	}

	public String getMediainfo() {
		return mediainfo;
	}

	public void setMediainfo(String mediainfo) {
		this.mediainfo = mediainfo;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	
}
