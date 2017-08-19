package postmaker;

public class Mediainfo extends PostItem {

	public Mediainfo(String value) {
		super(value);
	}
	
	@Override
	public void setValue(String value) {
		this.value = value.split("@@@")[1]
				.replaceAll("###", System.lineSeparator());
	}

	@Override
	public int setNumber(String value) {
		return Integer.parseInt(value.split("-")[0]
				.split("\\.")[2]
						.replaceAll("^0+", ""));
	}

}
