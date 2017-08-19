package postmaker;
public class Video extends PostItem {

	public Video(String value) {
		super(value);
	}
	
	@Override
	public int setNumber(String value) {
		return Integer.valueOf(value.split("-")[0]
				.split("]")[1]
						.split("\\.")[2]
								.replaceAll("^0+", ""));
	}

}
