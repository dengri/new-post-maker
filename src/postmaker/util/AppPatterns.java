package postmaker.util;

public enum AppPatterns {
	SPLIT_COVERS_PIXHOST("(?=\\[url=)"),
	SPLIT_COVERS_PIMPANDHOST(" "),
	SPLIT_SCREENSHOTS_PIXHOST ("(?=\\[url=)"),
	SPLIT_SCREENSHOTS_PIMPANDHOST (" "),
	SPLIT_MEDIAINFO ("(?<=px)"),
	SPLIT_TITLES("@@@"),
	SPLIT_URLS("\n");

	private String pattern;

	private AppPatterns(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
