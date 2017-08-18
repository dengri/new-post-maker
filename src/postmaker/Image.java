package postmaker;

public class Image extends PostItem {

	public Image(String value) {
		super(value);
	}

	@Override
	public int setNumber(String value) {
		String numberPartSplitPattern = "-";
		String zerosReplacePattern = "^0+";
		String numberPart = value.split(numberPartSplitPattern)[2]
				.replaceAll(zerosReplacePattern, "");
		return Integer.valueOf(numberPart);
	}
}
