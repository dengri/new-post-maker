package postmaker;

public abstract class PostItem {
	private int number;
	private String value;

	public PostItem(String value) {
		this.value = setValue(value);
		this.number = setNumber(value);
	}
	
	public abstract int setNumber(String value); 


	public int getNumber() {
		return number;
	}

	public String getValue() {
		return value;
	}
	
	protected String setValue(String value) {
		return value;
	}
}
