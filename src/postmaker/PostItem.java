package postmaker;

public class PostItem implements Comparable{
	protected int number;
	protected String value;

	public PostItem(String value) {
		setValue(value);
		this.number = setNumber(value);
	}
	
	public PostItem (int number) {
		this.number = number;
	}
	
	public int setNumber(String value) {
		return Integer.parseInt(value);
	}

	public int getNumber() {
		return number;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(Object o) {
		if (o == null || !(o instanceof PostItem)) {
			return -1;
		}
		return number - ((PostItem)o).getNumber();
	}
}
