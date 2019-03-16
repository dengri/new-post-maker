package postmaker;

public class Image extends PostItem {

	public Image(String value) {
		super(value);
	}

	@Override
	public int setNumber(String value) {
		String numberPart = value.split("-")[2]
				.replaceAll("^0+", "");
		return Integer.valueOf(numberPart);
	}
	
	public Image append(Image other) {
		this.value += other.value;
		return this;
	}
}
