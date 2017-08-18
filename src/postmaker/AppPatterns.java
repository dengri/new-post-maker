package postmaker;

public enum AppPatterns {
	SPLIT_IMAGES ("(?=\\[url=)"),
	SPLIT_MEDIAINFO ("(?<=px)");

	private String pattern;

	private AppPatterns(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
