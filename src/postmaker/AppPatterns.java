package postmaker;

public enum AppPatterns {
	SPLIT_IMAGES ("(?=\\[url=)"),
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
