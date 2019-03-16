package postmaker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Video extends PostItem {
	
	private static final Logger logger = LogManager.getLogger(Video.class);

	public Video(String value) {
		super(value);
	}
	
	@Override
	public int setNumber(String value) {
		String v1 = value.split("-")[0];
		logger.trace(v1);
		String v2 = v1.split("]")[1];
		logger.trace(v2);
		String v3 = v2.split("\\.")[2];
		logger.trace(v3);
		String v4 = v3.replaceAll("^0+", "");
		logger.trace(v4);
		return Integer.valueOf(value.split("-")[0]
				.split("]")[1]
						.split("\\.")[2]
								.replaceAll("^0+", ""));
	}

}
