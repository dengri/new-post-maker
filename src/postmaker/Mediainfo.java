package postmaker;

import static postmaker.AppPatterns.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mediainfo extends PostItem {
	private static final Logger logger = LogManager.getLogger(Mediainfo.class);

	public Mediainfo(String value) {
		super(value);
		setNumber(value);
	}
	
	@Override
	protected String setValue(String value) {
		return value.split("@@@")[1]
				.replaceAll("###", System.lineSeparator());
	}

	@Override
	public int setNumber(String value) {
		return Integer.parseInt(value.split("-")[0]
				.split("\\.")[2]
						.replaceAll("^0+", ""));
	}

}
