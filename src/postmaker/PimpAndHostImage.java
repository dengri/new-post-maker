package postmaker;

public class PimpAndHostImage extends Image {

	public PimpAndHostImage(String value) {
		super(value);
	}
	
	@Override
	public int setNumber(String value) {
		String numberPart = value.split("-")[1].split("\\.")[5];
		System.out.println(numberPart);
		return Integer.valueOf(numberPart);
	}

}
